package com.jfhealthcare.modules.business.service.impl;


import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.jfhealthcare.common.entity.CommonStaticValue;
import com.jfhealthcare.common.entity.LoginUserEntity;
import com.jfhealthcare.common.entity.MyPageInfo;
import com.jfhealthcare.common.enums.AdminEnum;
import com.jfhealthcare.common.enums.CheckStatusEnum;
import com.jfhealthcare.common.exception.RmisException;
import com.jfhealthcare.common.utils.DateUtils;
import com.jfhealthcare.common.utils.LoginUtil;
import com.jfhealthcare.common.utils.NameUtils;
import com.jfhealthcare.common.utils.TransferUtils;
import com.jfhealthcare.common.validator.Assert;
import com.jfhealthcare.modules.apply.entity.ApplyWorklist;
import com.jfhealthcare.modules.apply.mapper.ApplyWorklistMapper;
import com.jfhealthcare.modules.basics.TCdate;
import com.jfhealthcare.modules.basics.TCresult;
import com.jfhealthcare.modules.business.entity.*;
import com.jfhealthcare.modules.business.mapper.*;
import com.jfhealthcare.modules.business.request.ViewWorklistRequest;
import com.jfhealthcare.modules.business.response.ViewWorklistResponse;
import com.jfhealthcare.modules.business.service.ViewWorklistService;
import com.jfhealthcare.modules.system.entity.RepGroup;
import com.jfhealthcare.modules.system.entity.SysDictDtl;
import com.jfhealthcare.modules.system.entity.SysOrgClassify;
import com.jfhealthcare.modules.system.entity.SysOrgGroupRel;
import com.jfhealthcare.modules.system.mapper.RepGroupMapper;
import com.jfhealthcare.modules.system.mapper.SysOrgClassifyMapper;
import com.jfhealthcare.modules.system.mapper.SysOrgGroupRelMapper;
import com.jfhealthcare.modules.system.mapper.SysOrganizationMapper;
import com.jfhealthcare.modules.system.service.SysDictDtlService;
import com.jfhealthcare.modules.teach.mapper.TeachTakePhotosItemsMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

import java.net.URLEncoder;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
@Service
public class ViewWorklistServiceImpl implements ViewWorklistService {

	@Autowired
	private ViewWorklistMapper viewWorklistMapper;

	@Autowired
	private ViewWorklistHisMapper viewWorklistHisMapper;
	
	@Autowired
	private BusinCheckFlowStateMapper businCheckFlowStateMapper;
	
	@Autowired
	private RepRecordMapper repRecordMapper;

	@Autowired
	private RepRecordHisMapper repRecordHisMapper;
	
	@Autowired
	private BusinChecklistIndexMapper businChecklistIndexMapper;

	@Autowired
	private RepGroupMapper repGroupMapper;
	
	@Autowired
	private RepImageMapper repImageMapper;

	@Autowired
	private RepImageHisMapper repImageHisMapper;
	
	@Autowired
	private SysDictDtlService sysDictDtlService;
	
	@Autowired
	private BusinPatientMapper businPatientMapper;
	
	@Autowired
	private ApplyWorklistMapper applyWorklistMapper;
	
	@Autowired
	private SysOrgGroupRelMapper sysOrgGroupRelMapper;

	@Autowired
	private TeachTakePhotosItemsMapper teachTakePhotosItemsMapper;

	@Autowired
	private SysOrgClassifyMapper sysOrgClassifyMapper;

	@Autowired
	private SysOrganizationMapper sysOrganizationMapper;

	@Autowired
	private BusinChecklistItemsMapper businChecklistItemsMapper;

	@Value("${dcm.webview.url}")
	private String webViewUrl;
	
	@Value("${tc.http.postptn.api}")
	private String tcHttpApi;
	
	@Value("${tc.password}")
	private String tcPassword;
	
	@Value("${tc.username}")
	private String tcUsername;
	
	@Value("${dcm.dicom.url}")
	private String dcmDicomUrl;
	
	private static ExecutorService es = Executors.newFixedThreadPool(10);
	
	@Override
	public MyPageInfo<ViewWorklistResponse> queryViewWorklist(ViewWorklistRequest vwlr, LoginUserEntity loginUserEntity) {
		if(vwlr.getCheckDate()!=null){
			if(vwlr.getCheckApplyStartTime()==null || vwlr.getCheckApplyEndTime()==null){
				List<Date> checkTime = DateUtils.getCheckTime(vwlr.getCheckDate());
				if(!ObjectUtils.isEmpty(checkTime)){
					vwlr.setCheckApplyStartTime(checkTime.get(0));
					vwlr.setCheckApplyEndTime(checkTime.get(1));
				}
			}
	}
		//add by zhengtw 增加分组逻辑
		String loginCode = loginUserEntity.getSysOperator().getLogincode();
		vwlr.setLoginCode(loginCode);

		PageHelper.startPage(vwlr.getPageNum(), vwlr.getPageSize());
		List<ViewWorklistResponse> vwls=viewWorklistMapper.queryViewWorklist(vwlr);
		MyPageInfo<ViewWorklistResponse> pageInfo = new MyPageInfo<ViewWorklistResponse>(vwls);
		if (vwls != null && vwls.size() > 0 && vwls.get(0).getIsRemindFlag() == 1) {
			if (vwls.get(0).getCheckJzFlag()) {
				// 第一条记录是急诊，响铃
				pageInfo.setIsRemind(2);
			} else if ("3".equals(vwls.get(0).getCheckPtnSource())) {
				// 第一条记录是体检，判断未处理的是否有非体检，有则响铃
				pageInfo.setIsRemind(0);
				for (ViewWorklistResponse rep : vwls) {
					if (rep.getIsRemindFlag() == 1 && !"3".equals(rep.getCheckPtnSource())) {
						pageInfo.setIsRemind(1);
						break;
					} else if (rep.getIsRemindFlag() != 1) {
						break;
					} else {
						continue;
					}
				}
			} else {
				pageInfo.setIsRemind(1);
			}
		} else {
			pageInfo.setIsRemind(0);
		}
		if ("1".equals(vwlr.getIsButton())) {
			List <Map <String, Object>> queryCountViewWorklist = viewWorklistMapper.queryCountViewWorklist(vwlr);
			pageInfo.setOtherValue(queryCountViewWorklist);
		}
		// 退回重写提醒功能
		Example ex = new Example(BusinChecklistIndex.class);
		ex.createCriteria().andEqualTo("reportDr", loginCode).andEqualTo("isRepulse", true);
		Integer isRepulse = businChecklistIndexMapper.selectCountByExample(ex) > 0 ? 1 : 0;
		pageInfo.setIsRepulse(isRepulse);
		return pageInfo;
	}
	
    @Override
	public ViewWorklistResponse queryOneViewWorklist(String checkAccessionNum) {
		ViewWorklistRequest vwlr=new ViewWorklistRequest();
		vwlr.setCheckAccessionNum(checkAccessionNum);
		List<ViewWorklistResponse> vwls=viewWorklistMapper.queryOneViewWorklist(vwlr);
		if(!CollectionUtils.isEmpty(vwls)){
			ViewWorklistResponse viewWorklistResponse = vwls.get(0);
			String reprcdRepUid = viewWorklistResponse.getReprcdRepUid();
			RepImage repImage=new RepImage();
			repImage.setRepUid(reprcdRepUid);
			List<RepImage> repImages = repImageMapper.select(repImage);
			List<Map<String, String>> uidmaps=new ArrayList<Map<String, String>>();
			for (RepImage rimg : repImages) {
				String[] splits = StringUtils.split(rimg.getImgPage(), "&");
				Map<String, String> uidmap=new HashMap<String, String>();
				for (String uids : splits) {
					String[] keyAndUid = uids.split("=");
					String uid = keyAndUid[1];
					if("studyUID".equals(keyAndUid[0])) {
						uidmap.put("StudyUid", uid);
					}else if("seriesUID".equals(keyAndUid[0])) {
						uidmap.put("SeriesUid", uid);
					}else if("objectUID".equals(keyAndUid[0])) {
						uidmap.put("ObjectUid", uid);
					}
				}
				uidmaps.add(uidmap);
			}
			try {
				String encode = URLEncoder.encode(JSON.toJSONString(uidmaps),"UTF-8");
				viewWorklistResponse.setSopUrl(webViewUrl+encode);
				log.info("web view url:{}", uidmaps);
			} catch (Exception e) {
				throw new RmisException("web view encode exception!");
			}
			//查询历史报告数量
			String[] status = new String[] {"3557","3556"};
			Example ex=new Example(ViewWorklist.class);
			ex.createCriteria().andEqualTo("checkApplyHospCode", viewWorklistResponse.getCheckApplyHospCode())
			 .andEqualTo("patName", viewWorklistResponse.getPatName()).andIn("checkStatusCode", Arrays.asList(status))
			 .andNotEqualTo("checkAccessionNum", checkAccessionNum)
			 .andBetween("checkApplyTime", DateUtils.getSomeYearsBeforeAfter(new Date(), -3), new Date());
			int historyNum = viewWorklistMapper.selectCountByExample(ex);
			if(historyNum == 0){
				//查历史表
				Example exHis=new Example(ViewWorklistHis.class);
				exHis.createCriteria().andEqualTo("checkApplyHospCode", viewWorklistResponse.getCheckApplyHospCode())
						.andEqualTo("patName", viewWorklistResponse.getPatName()).andIn("checkStatusCode", Arrays.asList(status))
						.andNotEqualTo("checkAccessionNum", checkAccessionNum)
						.andBetween("checkApplyTime", DateUtils.getSomeYearsBeforeAfter(new Date(), -3), new Date());
				historyNum = viewWorklistHisMapper.selectCountByExample(exHis);
			}
			viewWorklistResponse.setHistoryNum(historyNum);
			return viewWorklistResponse;
		}
		return new ViewWorklistResponse();
	}
    
    @Override
	public String queryWebviewerUrlByAccessionNum(String checkAccessionNum) {
    	RepRecord repRecord=new RepRecord();
    	repRecord.setAccessionNum(checkAccessionNum);
    	List<RepRecord> repRecords = repRecordMapper.select(repRecord);
    	if(repRecords == null || repRecords.size() <= 0){
    		//查历史表
			RepRecord repRecordT=new RepRecord();
			repRecords = new ArrayList <RepRecord>();
			RepRecordHis repRecordHis=new RepRecordHis();
			repRecordHis.setAccessionNum(checkAccessionNum);
			List<RepRecordHis> repRecordHisList = repRecordHisMapper.select(repRecordHis);
			Assert.isListOnlyOne(repRecordHisList, "流水号对应的报告记录不存在或者存在多个！");
			repRecordT.setRepUid(repRecordHisList.get(0).getRepUid());
			repRecords.add(repRecordT);
		}
    	RepImage repImage=new RepImage();
		repImage.setRepUid(repRecords.get(0).getRepUid());
		List<RepImage> repImages = repImageMapper.select(repImage);
		if(repImages == null || repImages.size() <= 0){
			//查历史表
			repImages = new ArrayList <RepImage>();
			RepImageHis repImageHis=new RepImageHis();
			repImageHis.setRepUid(repRecords.get(0).getRepUid());
			List<RepImageHis> repImageHisList = repImageHisMapper.select(repImageHis);
			for(RepImageHis repImageHisT : repImageHisList){
				RepImage repImageT=new RepImage();
				TransferUtils.copyProperties(repImageHisT, repImageT);
				repImages.add(repImageT);
			}
		}
		List<Map<String, String>> uidmaps=new ArrayList<Map<String, String>>();
		for (RepImage rimg : repImages) {
			String[] splits = StringUtils.split(rimg.getImgPage(), "&");
			Map<String, String> uidmap=new HashMap<String, String>();
			for (String uids : splits) {
				String[] keyAndUid = uids.split("=");
				String uid = keyAndUid[1];
				if("studyUID".equals(keyAndUid[0])) {
					uidmap.put("StudyUid", uid);
				}else if("seriesUID".equals(keyAndUid[0])) {
					uidmap.put("SeriesUid", uid);
				}else if("objectUID".equals(keyAndUid[0])) {
					uidmap.put("ObjectUid", uid);
				}
			}
			uidmaps.add(uidmap);
		}
		try {
			String encode = URLEncoder.encode(JSON.toJSONString(uidmaps),"UTF-8");
			return webViewUrl+encode;
		} catch (Exception e) {
			throw new RmisException("web view encode exception!");
		}
	}
	
	
	@Override
	public Map<String, String> queryBtnsByCheckAccessionNum(String checkAccessionNum, LoginUserEntity user) {
		ViewWorklistRequest vwlr=new ViewWorklistRequest();
		vwlr.setCheckAccessionNum(checkAccessionNum);
		List<ViewWorklistResponse> vwls=viewWorklistMapper.queryViewWorklist(vwlr);
//		Map<String, String> allBtnsMap = btnPropertiesConfig.getBtnsMap();
		Map<String, String> allBtnsMap = CommonStaticValue.getBtnsMap();
		
		Map<String, String> btnsMap=getBtns(null);
		if(!CollectionUtils.isEmpty(vwls)) {
			//1.确定按钮状态   
			ViewWorklistResponse viewWorklistResponse = vwls.get(0);
			String checkStatusCode = viewWorklistResponse.getCheckStatusCode();//检查状态
			String adminCode = user.getSysOperator().getAdminCode();//审核权限
			String loginCode = user.getSysOperator().getLogincode();//登录账号
			String btn = allBtnsMap.get(adminCode+checkStatusCode);
			if(StringUtils.isNoneBlank(btn)) {
				//返回结果不为空  ：  匹配中有其存在的按钮  直接返回
				btnsMap = getBtns(btn);
			}else {
				//返回结果为空  ：   匹配中没有存在的按钮
				/**
				 * 报告中： 自己的可打开 可操作   其他权限通待报告     其他人的不可打开
				 * 审核中： 自己的可打开 可操作   其他权限通待审核     其他人的不可打开
				 * 暂存中： 状态取消   按钮保留，功能保留为保存
				 */
				//报告中
				if(CheckStatusEnum.REPORTING.getStatusCode().equals(checkStatusCode)) {
					//自己的
					if(loginCode.equals(viewWorklistResponse.getCheckReportDr())) {
						String reportingBtn = allBtnsMap.get(adminCode+CheckStatusEnum.PENDING_REPORT.getStatusCode());
						btnsMap = getBtns(reportingBtn);
					}else {
					//不是自己的
						btnsMap.put("isOpen", "0");
					}
				//审核中
				}else if(CheckStatusEnum.REVIEWING.getStatusCode().equals(checkStatusCode)) {
					//自己的
					if(loginCode.equals(viewWorklistResponse.getCheckAuditDr())) {
						String reviewingBtn = allBtnsMap.get(adminCode+CheckStatusEnum.PENDING_ONE_REVIEW.getStatusCode());
						btnsMap = getBtns(reviewingBtn);
					}else {
					//不是自己的
						btnsMap.put("isOpen", "0");
					}
				//已作废
				}else if(CheckStatusEnum.COMPLETE_ABANDONED.getStatusCode().equals(checkStatusCode)) {
					btnsMap.put("isOpen", "0");
				//待会报
				}else if(CheckStatusEnum.PENGING_HUIZHENG_REPORT.getStatusCode().equals(checkStatusCode)) {
					if("1".equals(viewWorklistResponse.getGroupProperties())) {
						//用户在会诊组，否则只有查看权限
						String huibaoBtn = allBtnsMap.get(adminCode+CheckStatusEnum.PENDING_REPORT.getStatusCode());
						if(StringUtils.isNoneBlank(huibaoBtn)) {
							btnsMap = getBtns(huibaoBtn);
						}
					}
				//待会审
				}else if(CheckStatusEnum.PENGING_HUIZHENG_REVIEW.getStatusCode().equals(checkStatusCode)) {
					if("1".equals(viewWorklistResponse.getGroupProperties())) {
						//用户在会诊组，否则只有查看权限
						String huishenBtn = allBtnsMap.get(adminCode+CheckStatusEnum.PENDING_ONE_REVIEW.getStatusCode());
						if(StringUtils.isNoneBlank(huishenBtn)) {
							btnsMap = getBtns(huishenBtn);
						}
					}
				}
			}
		}
		return btnsMap;
	}
	
	private Map<String, String> getBtns(String newBtns) {
		Map<String, String> btnsMap=new HashMap<String, String>() ;
//		String btnString = btnPropertiesConfig.getBtnsMap().get("00000000");
		String btnString = CommonStaticValue.getBtnsMap().get("00000000");
		String[] allBtns = StringUtils.split(btnString,",");
		for (String allbtn : allBtns) {
			/**
			 * 所有按钮初始时为不可用状态，除了 退出和预览
			 */
			if(StringUtils.equalsAny(allbtn, "isOpen","tc","yl")) {
				btnsMap.put(allbtn, "1");
			}else {
				btnsMap.put(allbtn, "0");
			}
		}
		/**
		 * 给有权限的按钮复制为可使用状态
		 */
		if(StringUtils.isNotEmpty(newBtns)) {
			String[] split = StringUtils.split(newBtns, ",");
			for (String btn : split) {
				btnsMap.put(btn, "1");
			}
		}
		return btnsMap;
	}
	
	
	@Override
	public List<ViewWorklistResponse> queryHistoryReport(ViewWorklistRequest vr) {
		List<ViewWorklistResponse> vws=viewWorklistMapper.queryHistoryReport(vr);
		return vws;
	}
	
	@Override
	@Transactional
	public void updateCheckListIndex(ViewWorklistRequest vmlr,LoginUserEntity loginUserEntity) {
		BusinChecklistIndex bc=new BusinChecklistIndex();
		bc.setAccessionNum(vmlr.getCheckAccessionNum());
		List<BusinChecklistIndex> bCLIs = businChecklistIndexMapper.select(bc);
		Assert.isListOnlyOne(bCLIs, "检查报告异常，请联系开发人员！");
		BusinChecklistIndex bCLI = bCLIs.get(0);
		
		RepRecord repRecord=new RepRecord();
		repRecord.setAccessionNum(vmlr.getCheckAccessionNum());
		List<RepRecord> repRecords = repRecordMapper.select(repRecord);
		Assert.isListOnlyOne(repRecords, "诊断报告异常，请联系开发人员！");
		RepRecord rrd = repRecords.get(0);
		
		//2018 03 26 重构更新功能    以按钮传值为准
		//isOpen,wctj,shxf,tc,jj,fq,zc,yl,zhz,zf,thcx: 打开 退回 拒绝 预览 暂存 转会诊 完成提交 完成下发 作废 退回重写
		String statusCode = bCLI.getStatusCode();//当前状态值   打开报告没做更改时  的状态   
		String status = bCLI.getStatus();//当前状态
		String checkBut = vmlr.getCheckBut();//按钮传值
		Assert.isBlank(checkBut, "按钮状态不能为空！");
		boolean isClockUpdate=false;//是否进入锁更新  false 不进入
//		boolean isCompleteReview=false;//是否完成报告
		if(StringUtils.equals("isOpen", checkBut)) {
			Example example = new Example(BusinChecklistIndex.class);
			Criteria createCriteria = example.createCriteria();
			createCriteria.andEqualTo("accessionNum", vmlr.getCheckAccessionNum());
			
			//第一次打开   待报告     待会报
			if(StringUtils.equalsAny(statusCode,CheckStatusEnum.PENDING_REPORT.getStatusCode(),CheckStatusEnum.PENGING_HUIZHENG_REPORT.getStatusCode())  ) {
				createCriteria.andNotEqualTo("statusCode", CheckStatusEnum.REPORTING.getStatusCode());
				
				bCLI.setStatus(CheckStatusEnum.REPORTING.getStatus());
				bCLI.setStatusCode(CheckStatusEnum.REPORTING.getStatusCode());
				bCLI.setReportDr(NameUtils.getLoginCode());
				bCLI.setReportTime(new Date());
				isClockUpdate=true;
			}else if(StringUtils.equalsAny(statusCode,CheckStatusEnum.PENDING_ONE_REVIEW.getStatusCode(),CheckStatusEnum.PENGING_HUIZHENG_REVIEW.getStatusCode(),
					  CheckStatusEnum.PENDING_TWO_REVIEW.getStatusCode(),CheckStatusEnum.PENDING_THREE_REVIEW.getStatusCode())) {
				//第一次打开   待审核 待二审 待三审    待会审
				createCriteria.andNotEqualTo("statusCode", CheckStatusEnum.REVIEWING.getStatusCode());
				
				bCLI.setStatus(CheckStatusEnum.REVIEWING.getStatus());
				bCLI.setStatusCode(CheckStatusEnum.REVIEWING.getStatusCode());
				bCLI.setAuditDr(NameUtils.getLoginCode());
				bCLI.setAuditTime(new Date());
				isClockUpdate=true;
			}
			
			//不是第一次打开 进行操作的   不做乐观锁更新
			if(isClockUpdate) {
				int num = businChecklistIndexMapper.updateByExampleSelective(bCLI, example);//乐观锁
				if(num==0) {
					return;//不可操作   返回
				}
			}
			
		}else if(StringUtils.equals(checkBut, "wctj")) {
			//完成提交   报告   一审  二审    wctj wctj wctj
			if(StringUtils.equalsAny(statusCode,CheckStatusEnum.PENDING_REPORT.getStatusCode(),
					CheckStatusEnum.REPORTING.getStatusCode(),CheckStatusEnum.PENGING_HUIZHENG_REPORT.getStatusCode())) {
				bCLI.setStatus(CheckStatusEnum.PENDING_ONE_REVIEW.getStatus());
				bCLI.setStatusCode(CheckStatusEnum.PENDING_ONE_REVIEW.getStatusCode());
				bCLI.setReportTime(new Date());
				bCLI.setReportDr(NameUtils.getLoginCode());
			}else if(StringUtils.equals(CheckStatusEnum.PENDING_ONE_REVIEW.getStatusCode(), statusCode)) {
				bCLI.setStatus(CheckStatusEnum.PENDING_TWO_REVIEW.getStatus());
				bCLI.setStatusCode(CheckStatusEnum.PENDING_TWO_REVIEW.getStatusCode());
				bCLI.setAuditTime(new Date());
				bCLI.setAuditDr(NameUtils.getLoginCode());
			}else if(StringUtils.equals(CheckStatusEnum.PENDING_TWO_REVIEW.getStatusCode(), statusCode)) {
				bCLI.setStatus(CheckStatusEnum.PENDING_THREE_REVIEW.getStatus());
				bCLI.setStatusCode(CheckStatusEnum.PENDING_THREE_REVIEW.getStatusCode());
				bCLI.setAuditTime(new Date());
				bCLI.setAuditDr(NameUtils.getLoginCode());
			}else if(StringUtils.equalsAny(statusCode,CheckStatusEnum.REVIEWING.getStatusCode(),CheckStatusEnum.PENGING_HUIZHENG_REVIEW.getStatusCode())) {
				String adminCode = loginUserEntity.getSysOperator().getAdminCode();
				if(StringUtils.equals(AdminEnum.ONE_REVIEW.getAdminCode(), adminCode)) {
					bCLI.setStatus(CheckStatusEnum.PENDING_TWO_REVIEW.getStatus());
					bCLI.setStatusCode(CheckStatusEnum.PENDING_TWO_REVIEW.getStatusCode());
				}else if(StringUtils.equals(AdminEnum.TWO_REVIEW.getAdminCode(), adminCode)) {
					bCLI.setStatus(CheckStatusEnum.PENDING_THREE_REVIEW.getStatus());
					bCLI.setStatusCode(CheckStatusEnum.PENDING_THREE_REVIEW.getStatusCode());
				}
				bCLI.setAuditTime(new Date());
				bCLI.setAuditDr(NameUtils.getLoginCode());
			}
			rrd.setFinding1(vmlr.getReprcdFinding1());
			rrd.setImpression1(vmlr.getReprcdImpression1());
			rrd.setHp(vmlr.getReprcdHp());
			bCLI.setIsRepulse(false);// 将退回标志置为0
		}else if(StringUtils.equals(checkBut, "shxf")) {
			//审核下发
			bCLI.setStatus(CheckStatusEnum.COMPLETE_REVIEW.getStatus());
			bCLI.setStatusCode(CheckStatusEnum.COMPLETE_REVIEW.getStatusCode());
			if(StringUtils.isEmpty(bCLI.getReportDr())) {
				bCLI.setReportTime(new Date());
				bCLI.setReportDr(NameUtils.getLoginCode());
			}
			bCLI.setAuditTime(new Date());
			bCLI.setAuditDr(NameUtils.getLoginCode());
			rrd.setFinding1(vmlr.getReprcdFinding1());
			rrd.setImpression1(vmlr.getReprcdImpression1());
			rrd.setHp(vmlr.getReprcdHp());
			rrd.setRate(vmlr.getRate()); //报告评级
			rrd.setSuggest(vmlr.getSuggest()); //报告意见
			bCLI.setIsRepulse(false);// 将退回标志置为0
		}else if(StringUtils.equalsAny(checkBut, "tc","zc")) {
			//退出  暂存
			rrd.setFinding1(vmlr.getReprcdFinding1());
			rrd.setImpression1(vmlr.getReprcdImpression1());
			rrd.setHp(vmlr.getReprcdHp());
		}else if(StringUtils.equals(checkBut, "jj")) {
			//已拒绝
			bCLI.setStatus(CheckStatusEnum.COMPLETE_REFUSE.getStatus());
			bCLI.setStatusCode(CheckStatusEnum.COMPLETE_REFUSE.getStatusCode());
			SysDictDtl sysDictDtl = sysDictDtlService.queryDictDtlById(vmlr.getCheckRefuseCode());
			bCLI.setRefuseCode(vmlr.getCheckRefuseCode());
			bCLI.setRefuseName(sysDictDtl.getOthervalue());
			if(StringUtils.isNotBlank(vmlr.getCheckRefuseName())) {
				bCLI.setRefuseName(vmlr.getCheckRefuseName());
			}
			if(vmlr.getCheckPhotoIds() != null){
				if(vmlr.getCheckPhotoIds().size() > 0){
					//如果是已拒绝，并且选择投照示教
					List<String> photoIds = new ArrayList<>();
					for (int i = 0; i < vmlr.getCheckPhotoIds().size(); i++){
						String parentId = vmlr.getCheckPhotoIds().get(i).getParentId();
						String sonId = vmlr.getCheckPhotoIds().get(i).getSonId();
						if(StringUtils.isBlank(sonId)){
							photoIds.add(parentId);
						}else {
							photoIds.add(sonId);
						}
					}
					//根据id查询图片的路径
					List<String> pathList = teachTakePhotosItemsMapper.queryPathByIndexId(photoIds);
					//将list转为“，”分割的string保存在remark中
					String path = StringUtils.join(pathList.toArray(),",");
					bCLI.setRemark(path);
					log.info("保存投照示教成功！path："+path);
				}
			}
			bCLI.setIsRepulse(false);// 将退回标志置为0
		}else if(StringUtils.equals(checkBut, "zf")) {
			//作废
			bCLI.setStatus(CheckStatusEnum.COMPLETE_ABANDONED.getStatus());
			bCLI.setStatusCode(CheckStatusEnum.COMPLETE_ABANDONED.getStatusCode());
			bCLI.setIsRepulse(false);// 将退回标志置为0
		}else if(StringUtils.equals(checkBut, "zhz")) {
			//转会诊
			//报告转会诊
			if(StringUtils.equals(statusCode,CheckStatusEnum.REPORTING.getStatusCode())) {
				bCLI.setReportDr(null);
				bCLI.setReportTime(null);
				rrd.setFinding1(vmlr.getReprcdFinding1());
				rrd.setImpression1(vmlr.getReprcdImpression1());
				rrd.setHp(vmlr.getReprcdHp());
				bCLI.setStatus(CheckStatusEnum.PENGING_HUIZHENG_REPORT.getStatus());
				bCLI.setStatusCode(CheckStatusEnum.PENGING_HUIZHENG_REPORT.getStatusCode());
				RepGroup repGroup=new RepGroup();
				repGroup.setStatus("1");
				//会诊分组---没有会诊则不修改
				List<RepGroup> repGroups = repGroupMapper.select(repGroup);
				if(!ObjectUtils.isEmpty(repGroups)){
					RepGroup rg = repGroups.get(0);
					bCLI.setRepGroupId(rg.getId());
				}
				// 修改会诊标识
				bCLI.setDocLevel(1);
				bCLI.setIsRepulse(false);// 将退回标志置为0
			//审核转会诊
			}else if(StringUtils.equals(statusCode,CheckStatusEnum.REVIEWING.getStatusCode())) {
				bCLI.setAuditDr(null);
				bCLI.setAuditTime(null);
				bCLI.setStatus(CheckStatusEnum.PENGING_HUIZHENG_REVIEW.getStatus());
				bCLI.setStatusCode(CheckStatusEnum.PENGING_HUIZHENG_REVIEW.getStatusCode());
				
				RepGroup repGroup=new RepGroup();
				repGroup.setStatus("1");
				//会诊分组---没有会诊则不修改
				List<RepGroup> repGroups = repGroupMapper.select(repGroup);
				if(!ObjectUtils.isEmpty(repGroups)){
					RepGroup rg = repGroups.get(0);
					bCLI.setRepGroupId(rg.getId());
				}
				// 修改会诊标识
				bCLI.setDocLevel(1);
				rrd.setFinding1(vmlr.getReprcdFinding1());
				rrd.setImpression1(vmlr.getReprcdImpression1());
				rrd.setHp(vmlr.getReprcdHp());
				bCLI.setIsRepulse(false);// 将退回标志置为0
			}
		}else if(StringUtils.equals(checkBut, "fq")) {
			//放弃 报告中放弃
			if(StringUtils.equals(statusCode,CheckStatusEnum.REPORTING.getStatusCode())) {
				bCLI.setStatus(CheckStatusEnum.PENDING_REPORT.getStatus());
				bCLI.setStatusCode(CheckStatusEnum.PENDING_REPORT.getStatusCode());
				bCLI.setReportDr(null);
				bCLI.setReportTime(null);
				rrd.setFinding1(null);
				rrd.setImpression1(null);
				rrd.setHp(null);
				bCLI.setIsRepulse(false);// 将退回标志置为0
			}else if(StringUtils.equals(statusCode,CheckStatusEnum.REVIEWING.getStatusCode())) {
				//放弃 审核中放弃	
				List<BusinCheckFlowState> flows=businCheckFlowStateMapper.selectFlowRorIndex(bc.getAccessionNum());
				Assert.isNull(flows, "报告流记录异常！");
				BusinCheckFlowState businCheckFlowState = flows.get(0);
				bCLI.setStatus(businCheckFlowState.getStatus());
				bCLI.setStatusCode(businCheckFlowState.getStatusCode());
				bCLI.setAuditDr(businCheckFlowState.getOperationUser());
				bCLI.setAuditTime(businCheckFlowState.getOperationTime());
                if(StringUtils.equalsAny(businCheckFlowState.getRemark(), "ai转审核->待一审","报告中->待一审","审核中->待一审")) {
                	bCLI.setStatus(CheckStatusEnum.PENDING_ONE_REVIEW.getStatus());
    				bCLI.setStatusCode(CheckStatusEnum.PENDING_ONE_REVIEW.getStatusCode());
                	bCLI.setAuditDr(null);
    				bCLI.setAuditTime(null);
				}else if(StringUtils.endsWith(businCheckFlowState.getRemark(), "转会审")) {
					bCLI.setAuditDr(null);
    				bCLI.setAuditTime(null);
				}
				rrd.setFinding1(businCheckFlowState.getFinding());
				rrd.setImpression1(businCheckFlowState.getImpression());
				rrd.setHp(businCheckFlowState.getHp());
			}
		}else if(StringUtils.equals(checkBut, "thcx")) {
			//退回重写
			Example ex =new Example(BusinCheckFlowState.class);
			ex.createCriteria().andEqualTo("accessionNum", bc.getAccessionNum()).andEqualTo("remark", "报告中->待一审");
			ex.setOrderByClause("OPERATION_TIME desc");
			List<BusinCheckFlowState> flows = businCheckFlowStateMapper.selectByExample(ex);
			Assert.isNull(flows, "报告流记录异常！");
			BusinCheckFlowState businCheckFlowState = flows.get(0);
			bCLI.setStatus(CheckStatusEnum.REPORTING.getStatus());
			bCLI.setStatusCode(CheckStatusEnum.REPORTING.getStatusCode());
			bCLI.setAuditDr(null);
			bCLI.setAuditTime(null);
			rrd.setFinding1(businCheckFlowState.getFinding());
			rrd.setImpression1(businCheckFlowState.getImpression());
			rrd.setHp(businCheckFlowState.getHp());
			// 修改会诊标识，转为非会诊
			bCLI.setDocLevel(0);
			bCLI.setIsRepulse(true);// 将退回标志置为1
		}
		if(!isClockUpdate) { //默认false   当进入锁更新时不进入
			businChecklistIndexMapper.updateByPrimaryKey(bCLI);
		}
		repRecordMapper.updateByPrimaryKey(rrd);
		updateReportFlow(statusCode,bCLI,rrd);
		//已完成的报告 给医睦科技推送消息  只允许需要的机构推送
//		SysDictDtl sysdict = sysDictDtlService.quertDictDtlByCodeAndName("OrgReport", bCLI.getApplyHospCode());
//		if(isCompleteReview && ObjectUtil.isNotNull(sysdict)) {
//			getPostToTC(bCLI,rrd);
//		}
		log.info(loginUserEntity.getSysOperator().getLogincode()+"：报告(accessnum:"+bCLI.getAccessionNum()+")处理后状态为："+bCLI.getStatus());
	}
	
	//报告下发后  调用医睦科技的接口
	private void getPostToTC(BusinChecklistIndex bCLI, RepRecord rrd) {
		es.submit(new Runnable() {
			@Override
			public void run() {
				BusinPatient buspat = businPatientMapper.selectByPrimaryKey(bCLI.getCheckNum());
				ApplyWorklist applyWorklist = applyWorklistMapper.selectByPrimaryKey(bCLI.getCheckNum());
				
				TCresult tcresult=new TCresult();
				tcresult.setId(bCLI.getId());
				tcresult.setAccessionNum(bCLI.getAccessionNum());
				tcresult.setStudyUid(applyWorklist.getStudyUid());
				tcresult.setApplyDoc(bCLI.getApplyDoc());
				tcresult.setApplyOrg(bCLI.getApplyHosp());
				tcresult.setPtnId(rrd.getPatId());
				tcresult.setCheckNum(bCLI.getCheckNum());
				tcresult.setCheckTime(DateUtil.format(bCLI.getApplyTime(), "yyyy-MM-dd HH:mm:ss"));
				tcresult.setPtnName(buspat.getName());
				tcresult.setSex(buspat.getSex());
				tcresult.setPtnAge(rrd.getPatAge());
				tcresult.setPtnAgeUnit(rrd.getAgeUnit());
				tcresult.setImageId(null);
				tcresult.setBodyPart(bCLI.getParts());
				tcresult.setFinding(rrd.getFinding1());
				tcresult.setImpression(rrd.getImpression1());
				tcresult.setHp(rrd.getHp());
				
				List<String> filepaths=new ArrayList<String>();
				RepImage repImage=new RepImage();
				repImage.setRepUid(rrd.getRepUid());
				List<RepImage> repImages = repImageMapper.select(repImage);
				for (RepImage rep : repImages) {
					filepaths.add(dcmDicomUrl+rep.getImgPage());
				}
				tcresult.setFilePath(filepaths);
				
				TCdate tcdate=new TCdate();
				tcdate.setUserName(tcUsername);
				tcdate.setPassword(tcPassword);
				tcdate.setResult(tcresult);
				HashMap<String, Object> paramMap = new HashMap<>();
				paramMap.put("content", JSON.toJSONString(tcdate));
				String result= HttpUtil.post(tcHttpApi, paramMap);
				log.info(StrUtil.format("流水号：{}，第三方抵用返回结果：{}", bCLI.getAccessionNum(),result));
			}
		});
	}

	//改前的原始状态    改后的index以及报告记录
	private void updateReportFlow(String statusCode, BusinChecklistIndex bCLI, RepRecord rrd) {
		Example ex=new Example(BusinCheckFlowState.class);
		ex.createCriteria().andEqualTo("accessionNum", bCLI.getAccessionNum());
		int count = businCheckFlowStateMapper.selectCountByExample(ex);
		
		BusinCheckFlowState bcfs=new BusinCheckFlowState();
		bcfs.setAccessionNum(bCLI.getAccessionNum());
		bcfs.setNumber(count+1);
		bcfs.setStatus(bCLI.getStatus());
		bcfs.setStatusCode(bCLI.getStatusCode());
		bcfs.setOperationUser(NameUtils.getLoginCode());
		bcfs.setOperationTime(new Date());
		bcfs.setFinding(rrd.getFinding1());
		bcfs.setImpression(rrd.getImpression1());
		bcfs.setHp(rrd.getHp());
		if(count==0) { //报告初创   记录ai的操作
			//初次记录 ：  正常经过ai 到待报告  待审核    更新后到  报告中  审核中
			bcfs.setRemark(CommonStaticValue.getFlowByKey(bCLI.getStatusAiCode()));
			businCheckFlowStateMapper.insertSelective(bcfs);
			log.info("报告流：accessnum:"+bCLI.getAccessionNum()+",remark:"+bcfs.getRemark());
			//初次记录 ： ai 出结果后直接分配的
			if(StringUtils.equalsAny(statusCode, CheckStatusEnum.REPORTING.getStatusCode(), CheckStatusEnum.REVIEWING.getStatusCode())){
				bcfs.setId(null);
				bcfs.setNumber(bcfs.getNumber()+1);
				bcfs.setRemark(CommonStaticValue.getFlowByKey(statusCode));
				businCheckFlowStateMapper.insertSelective(bcfs);
				log.info("报告流：accessnum:"+bCLI.getAccessionNum()+",remark:"+bcfs.getRemark());
			}
			bcfs.setNumber(bcfs.getNumber()+1);
		}
		bcfs.setId(null);
		bcfs.setRemark(CommonStaticValue.getFlowByKey(statusCode+"_"+bCLI.getStatusCode()));
		businCheckFlowStateMapper.insertSelective(bcfs);
		log.info("报告流：accessnum:"+bCLI.getAccessionNum()+",remark:"+bcfs.getRemark());
	}


	@Override
	public List<RepImage> queryRepImageByRepUid(String repUid) {
		Example example = new Example(RepImage.class);
		Criteria createCriteria = example.createCriteria();
		createCriteria.andEqualTo("repUid", repUid);
		createCriteria.andEqualTo("isdelete", CommonStaticValue.IS_NOT_DELETE);
		example.setOrderByClause("number asc");
		List<RepImage> repImages = repImageMapper.selectByExample(example);
		return repImages;
	}

	@Override
	public void deleteRepImageByRepImageId(String repImageId) {
		RepImage repImage =new RepImage();
		repImage.setId(repImageId);
		repImage.setIsdelete(CommonStaticValue.IS_DELETE);
		repImageMapper.updateByPrimaryKeySelective(repImage);
	}

	@Override
	public int queryViewWorklistIsRemind(LoginUserEntity loginUserEntity) {
		List<String> l=new ArrayList<String>();
		l.add(CheckStatusEnum.COMPLETE_PRINT.getStatusCode());
		l.add(CheckStatusEnum.COMPLETE_REFUSE.getStatusCode());
		l.add(CheckStatusEnum.COMPLETE_REVIEW.getStatusCode());
		l.add(CheckStatusEnum.COMPLETE_ABANDONED.getStatusCode());
		
		// 获取用户角色，是否是超级管理员或者管理员
		String userRole = LoginUtil.getRole(loginUserEntity);
		List<String> hospIdList = null;
		if ("supermanager".equals(userRole)) {
			// 不需要增加机构id条件
		} else if ("manager".equals(userRole)) {
			// 增加所属影像中心的机构ID 获取所属影像中心下的所有机构ID
			Example ex = new Example(SysOrgGroupRel.class);
			Criteria criteria = ex.createCriteria();
			criteria.andEqualTo("isValid", 1);
			criteria.andEqualTo("imageId", loginUserEntity.getSysOperatorDtl().getOrgId());
			List<SysOrgGroupRel> relList = sysOrgGroupRelMapper.selectByExample(ex);
			hospIdList = new ArrayList<String>();
			for (SysOrgGroupRel sysOrgGroupRel : relList) {
				hospIdList.add(sysOrgGroupRel.getHospitalId());
			}
		} else {
			hospIdList = sysOrgGroupRelMapper.getHospitalCode(loginUserEntity.getSysOperator().getLogincode());
		}

		Example ex=new Example(BusinChecklistIndex.class);
		Criteria cr = ex.createCriteria();
		cr.andNotIn("statusCode", l).andEqualTo("jzFlag", Boolean.TRUE);
		if(hospIdList != null && hospIdList.size() > 0) {
			cr.andIn("applyHospCode", hospIdList);
		}
		int jzCount = businChecklistIndexMapper.selectCountByExample(ex);
		if(jzCount>0) {
			return 2; //有急诊  返回2
		}
		
		Example ex2=new Example(BusinChecklistIndex.class);
		Criteria cr2 = ex2.createCriteria();
		cr2.andNotIn("statusCode", l).andNotEqualTo("ptnSource", "3");//排除患者来源是体检的   所有需要操作的
		if(hospIdList != null && hospIdList.size() > 0) {
			cr2.andIn("applyHospCode", hospIdList);
		}
		int num = businChecklistIndexMapper.selectCountByExample(ex2);
		return num>0?1:0;
	}


	@Override
	public List<SysOrgClassify> queryClassifyListByUser(LoginUserEntity loginUserEntity) {
		String loginRole = LoginUtil.getRole(loginUserEntity);
		List<SysOrgClassify> list = new ArrayList<>();
		if("supermanager".equals(loginRole)){//如果是超级管理员，展示所有机构分类
			list = sysOrgClassifyMapper.selectAll();
		}else {//否则展示该用户所在影像中心的所有机构分类
			//获取该用户所属影像中心code
			String orgCode = sysOrganizationMapper.queryOrgCodeByLoginCode(loginUserEntity.getSysOperator().getLogincode());
			SysOrgClassify classify = new SysOrgClassify();
			classify.setOrgCode(orgCode);
			list = sysOrgClassifyMapper.select(classify);
		}
		return list;
	}
}
