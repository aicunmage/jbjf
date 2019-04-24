package com.jfhealthcare.demo;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.alibaba.fastjson.JSON;
import com.jfhealthcare.common.enums.CheckStatusEnum;
import com.jfhealthcare.common.utils.NameUtils;
import com.jfhealthcare.common.validator.Assert;
import com.jfhealthcare.modules.apply.entity.ApplyWorklist;
import com.jfhealthcare.modules.apply.mapper.ApplyWorklistMapper;
import com.jfhealthcare.modules.basics.TCdate;
import com.jfhealthcare.modules.basics.TCresult;
import com.jfhealthcare.modules.business.entity.BusinChecklistIndex;
import com.jfhealthcare.modules.business.entity.BusinPatient;
import com.jfhealthcare.modules.business.entity.RepImage;
import com.jfhealthcare.modules.business.entity.ViewWorklist;
import com.jfhealthcare.modules.business.mapper.BusinChecklistIndexMapper;
import com.jfhealthcare.modules.business.mapper.BusinPatientMapper;
import com.jfhealthcare.modules.business.mapper.RepImageMapper;
import com.jfhealthcare.modules.business.mapper.ViewWorklistMapper;
import com.jfhealthcare.modules.business.response.ViewWorklistResponse;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpUtil;
import lombok.extern.slf4j.Slf4j;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class DateTest {
	
	@Autowired
	private ViewWorklistMapper viewWorklistMapper;
	
	@Autowired
	private BusinPatientMapper businPatientMapper;
	
	@Autowired
	private ApplyWorklistMapper applyWorklistMapper;
	
	@Autowired
	private RepImageMapper repImageMapper;
	
	@Value("${dcm.dicom.url}")
	private String dcmDicomUrl;
	
	@Value("${tc.password}")
	private String tcPassword;
	
	@Value("${tc.username}")
	private String tcUsername;
	
	@Value("${tc.http.api}")
	private String tcHttpApi;
	
	@Autowired
	private BusinChecklistIndexMapper businChecklistIndexMapper;
	
	
	@Test
	public void ac() {
		String accessionNum="20180601104324-731711";
		String statusCode="3434";
		BusinChecklistIndex bc=new BusinChecklistIndex();
		bc.setAccessionNum(accessionNum);
		List<BusinChecklistIndex> bCLIs = businChecklistIndexMapper.select(bc);
		Assert.isListOnlyOne(bCLIs, "检查报告异常，请联系开发人员！");
		BusinChecklistIndex bCLI = bCLIs.get(0);
		
		Example example = new Example(BusinChecklistIndex.class);
		Criteria createCriteria = example.createCriteria();
		createCriteria.andEqualTo("accessionNum", accessionNum);
		boolean isClockUpdate=false;//是否进入锁更新  false 不进入
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
			System.out.println("++++++++++++++++++++++++++++++++");
			System.out.println("++++++++++++++++++++++++++++++++");
			System.out.println("获取的值"+num);
			System.out.println("++++++++++++++++++++++++++++++++");
			System.out.println("++++++++++++++++++++++++++++++++");
			if(num==0) {
				System.out.println("进入return");
				return;//不可操作   返回
			}
		}
		System.out.println("没有通过return结束");
		
		
	}
	
	
	
	@Test
	public void v() {
		ViewWorklist viewWorklist=new ViewWorklist();
		viewWorklist.setCheckAccessionNum("20180607083935-496652");
		List nums=new ArrayList();
		nums.add("20180611102556-736881");
		nums.add("20180611102910-284551");
		nums.add("20180611103519-139421");
		nums.add("20180611103008-410161");
		nums.add("20180611103221-168241");
		
		nums.add("20180611103328-196571");
		nums.add("20180611104139-359331");
		nums.add("20180611102705-142021");
		nums.add("20180609131540-927811");
		nums.add("20180611130736-549491");
		
		nums.add("20180611125908-799781");
		nums.add("20180611125109-947761");
		nums.add("20180611104610-499741");
		nums.add("20180611104346-174481");
		nums.add("20180611105034-830751");
		
		nums.add("20180611104828-006261");
		nums.add("20180611105239-537881");
		nums.add("20180611105338-256111");
		nums.add("20180611105140-638761");
		nums.add("20180611105635-182631");
		Example ex=new Example(ViewWorklist.class);
		ex.createCriteria().andIn("checkAccessionNum", nums);
		List<ViewWorklist> v = viewWorklistMapper.selectByExample(ex);
		System.out.println("==================开始啦====================");
		System.out.println("==================开始啦====================");
		System.out.println("==================开始啦====================");
		System.out.println("==================开始啦====================");
		for (ViewWorklist vw : v) {
			postTc(vw);
		}
		System.out.println("==================结束啦====================");
		System.out.println("==================结束啦====================");
		System.out.println("==================结束啦====================");
		System.out.println("==================结束啦====================");
    }
	
	
	
	public void postTc(ViewWorklist viewWorklist) {
		BusinPatient buspat = businPatientMapper.selectByPrimaryKey(viewWorklist.getCheckNum());
		ApplyWorklist applyWorklist = applyWorklistMapper.selectByPrimaryKey(viewWorklist.getCheckNum());
		
		TCresult tcresult=new TCresult();
		tcresult.setId(viewWorklist.getCheckId());
		tcresult.setAccessionNum(viewWorklist.getCheckAccessionNum());
		tcresult.setStudyUid(applyWorklist.getStudyUid());
		tcresult.setApplyDoc(viewWorklist.getCheckApplyDoc());
		tcresult.setApplyOrg(viewWorklist.getCheckApplyHosp());
		tcresult.setPtnId(viewWorklist.getReprcdPatOnlyId());
		tcresult.setCheckNum(viewWorklist.getCheckNum());
		tcresult.setCheckTime(DateUtil.format(viewWorklist.getCheckApplyTime(), "yyyy-MM-dd HH:mm:ss"));
		tcresult.setPtnName(viewWorklist.getPatName());
		tcresult.setSex(viewWorklist.getPatSex());
		tcresult.setPtnAge(viewWorklist.getReprcdPatAge());
		tcresult.setPtnAgeUnit(viewWorklist.getReprcdAgeUnit());
		tcresult.setImageId(null);
		tcresult.setBodyPart(viewWorklist.getCheckParts());
		tcresult.setFinding(viewWorklist.getReprcdFinding1());
		tcresult.setImpression(viewWorklist.getReprcdImpression1());
		tcresult.setHp(viewWorklist.getReprcdHp());
		
		List<String> filepaths=new ArrayList<String>();
		RepImage repImage=new RepImage();
		repImage.setRepUid(viewWorklist.getReprcdRepUid());
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
		log.info(StrUtil.format("流水号：{}，第三方抵用返回结果：{}", viewWorklist.getCheckAccessionNum(),result));
	}

}
