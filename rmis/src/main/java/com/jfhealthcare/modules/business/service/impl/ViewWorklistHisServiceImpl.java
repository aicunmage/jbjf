package com.jfhealthcare.modules.business.service.impl;


import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.jfhealthcare.common.entity.CommonStaticValue;
import com.jfhealthcare.common.entity.LoginUserEntity;
import com.jfhealthcare.common.entity.MyPageInfo;
import com.jfhealthcare.common.enums.CheckStatusEnum;
import com.jfhealthcare.common.exception.RmisException;
import com.jfhealthcare.common.utils.DateUtils;
import com.jfhealthcare.common.utils.TransferUtils;
import com.jfhealthcare.common.validator.Assert;
import com.jfhealthcare.modules.business.entity.*;
import com.jfhealthcare.modules.business.mapper.*;
import com.jfhealthcare.modules.business.request.ViewWorklistRequest;
import com.jfhealthcare.modules.business.response.ViewWorklistResponse;
import com.jfhealthcare.modules.business.service.ViewWorklistHisService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

import java.net.URLEncoder;
import java.util.*;

@Slf4j
@Service
public class ViewWorklistHisServiceImpl implements ViewWorklistHisService {


	@Autowired
	private ViewWorklistMapper viewWorklistMapper;
	@Autowired
	private ViewWorklistHisMapper viewWorklistHisMapper;

	@Autowired
	private RepRecordHisMapper repRecordHisMapper;

	@Autowired
	private RepRecordMapper repRecordMapper;

	@Autowired
	private RepImageHisMapper repImageHisMapper;

	@Autowired
	private RepImageMapper repImageMapper;

	@Autowired
	private BusinPatientMapper businPatientMapper;

	@Autowired
	private BusinChecklistItemsHisMapper businChecklistItemsHisMapper;

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
		List<ViewWorklistResponse> vwls=viewWorklistHisMapper.queryViewWorklist(vwlr);
		MyPageInfo<ViewWorklistResponse> pageInfo = new MyPageInfo<ViewWorklistResponse>(vwls);
		if("1".equals(vwlr.getIsButton())) {
			List<Map<String, Object>> queryCountViewWorklist = viewWorklistHisMapper.queryCountViewWorklist(vwlr);
			pageInfo.setOtherValue(queryCountViewWorklist);
		}
		return pageInfo;
	}
	
    @Override
	public ViewWorklistResponse queryOneViewWorklist(String checkAccessionNum) {
		ViewWorklistRequest vwlr=new ViewWorklistRequest();
		vwlr.setCheckAccessionNum(checkAccessionNum);
		List<ViewWorklistResponse> vwls=viewWorklistHisMapper.queryOneViewWorklist(vwlr);
		if(!CollectionUtils.isEmpty(vwls)){
			ViewWorklistResponse viewWorklistResponse = vwls.get(0);
			String reprcdRepUid = viewWorklistResponse.getReprcdRepUid();
			RepImageHis repImageHis=new RepImageHis();
			repImageHis.setRepUid(reprcdRepUid);
			List<RepImageHis> repImages = repImageHisMapper.select(repImageHis);
			List<Map<String, String>> uidmaps=new ArrayList<Map<String, String>>();
			for (RepImageHis rimg : repImages) {
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

			Example exHis=new Example(ViewWorklistHis.class);
			exHis.createCriteria().andEqualTo("checkApplyHospCode", viewWorklistResponse.getCheckApplyHospCode())
					.andEqualTo("patName", viewWorklistResponse.getPatName()).andIn("checkStatusCode", Arrays.asList(status))
					.andNotEqualTo("checkAccessionNum", checkAccessionNum)
					.andBetween("checkApplyTime", DateUtils.getSomeYearsBeforeAfter(new Date(), -3), new Date());
			int historyNum = viewWorklistMapper.selectCountByExample(ex) + viewWorklistHisMapper.selectCountByExample(exHis);
			viewWorklistResponse.setHistoryNum(historyNum);
			return viewWorklistResponse;
		}
		return new ViewWorklistResponse();
	}
    
    @Override
	public String queryWebviewerUrlByAccessionNum(String checkAccessionNum) {
    	RepRecordHis repRecordHis=new RepRecordHis();
		repRecordHis.setAccessionNum(checkAccessionNum);
    	List<RepRecordHis> repRecords = repRecordHisMapper.select(repRecordHis);
		if(repRecords == null || repRecords.size() <= 0){
			//查工作表
			RepRecordHis repRecordHisT=new RepRecordHis();
			repRecords = new ArrayList <RepRecordHis>();
			RepRecord repRecord=new RepRecord();
			repRecord.setAccessionNum(checkAccessionNum);
			List<RepRecord> repRecordList = repRecordMapper.select(repRecord);
			Assert.isListOnlyOne(repRecordList, "流水号对应的报告记录不存在或者存在多个！");
			repRecordHisT.setRepUid(repRecordList.get(0).getRepUid());
			repRecords.add(repRecordHisT);
		}
    	RepImageHis repImageHis=new RepImageHis();
		repImageHis.setRepUid(repRecords.get(0).getRepUid());
		List<RepImageHis> repImages = repImageHisMapper.select(repImageHis);
		if(repImages == null || repImages.size() <= 0){
			//查工作表
			repImages = new ArrayList <RepImageHis>();
			RepImage repImage=new RepImage();
			repImage.setRepUid(repRecords.get(0).getRepUid());
			List<RepImage> repImageList = repImageMapper.select(repImage);
			for(RepImage repImageT : repImageList){
				RepImageHis repImageHisT=new RepImageHis();
				TransferUtils.copyProperties(repImageT, repImageHisT);
				repImages.add(repImageHisT);
			}
		}
		List<Map<String, String>> uidmaps=new ArrayList<Map<String, String>>();
		for (RepImageHis rimg : repImages) {
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
		List<ViewWorklistResponse> vwls=viewWorklistHisMapper.queryViewWorklist(vwlr);
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
	public List<RepImageHis> queryRepImageByRepUid(String repUid) {
		Example example = new Example(RepImageHis.class);
		Criteria createCriteria = example.createCriteria();
		createCriteria.andEqualTo("repUid", repUid);
		createCriteria.andEqualTo("isdelete", CommonStaticValue.IS_NOT_DELETE);
		example.setOrderByClause("number asc");
		List<RepImageHis> repImages = repImageHisMapper.selectByExample(example);
		return repImages;
	}

	@Override
	public void deleteRepImageByRepImageId(String repImageId) {
		RepImageHis repImageHis =new RepImageHis();
		repImageHis.setId(repImageId);
		repImageHis.setIsdelete(CommonStaticValue.IS_DELETE);
		repImageHisMapper.updateByPrimaryKeySelective(repImageHis);
	}
}
