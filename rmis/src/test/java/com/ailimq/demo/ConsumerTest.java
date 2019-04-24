package com.ailimq.demo;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSON;
import com.jfhealthcare.common.utils.HttpClientUtils;
import com.jfhealthcare.modules.basics.TCdate;
import com.jfhealthcare.modules.basics.TCresult;
import com.jfhealthcare.modules.business.entity.RepImage;

import cn.hutool.core.date.DateUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ConsumerTest {
	/**
	 * @param args
	 */
	
	public static void main(String[] args) {
		TCresult tcresult=new TCresult();
		tcresult.setId("c1c83024-692c-11e8-823e-0242ac110002");
		tcresult.setAccessionNum("20180601103312-770701");
		tcresult.setStudyUid("1.2.156.147522.44.410947.2807.20180601083214");
		tcresult.setApplyDoc("测试01");
		tcresult.setApplyOrg("测试机构分组一/机构一");
		tcresult.setPtnId("20180601103312-77070");
		tcresult.setCheckNum("20180601103312-77070");
		tcresult.setCheckTime(DateUtil.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
		tcresult.setPtnName("陈桂花");
		tcresult.setSex("女");
		tcresult.setPtnAge("66");
		tcresult.setPtnAgeUnit("岁");
		tcresult.setImageId(null);
		tcresult.setBodyPart("SHOULDER");
		tcresult.setFinding("<p>胸廓两侧对称，气管居中。肺纹理未见明显（稍）增多、增粗，肺野未见明显实变影。肺门影未见增大、增浓。纵隔不宽，心影大小、形态正常。双侧膈面光滑，双肋膈角锐利。</p>");
		tcresult.setImpression("<p>心、肺、膈未见明显异常征象。</p>");
		tcresult.setHp("0");
		
		List<String> filepaths=new ArrayList<String>();
		filepaths.add("http://101.132.45.197:8090/v1/picl/aets/piclarc/wado?requestType=WADO&contentType=application/dicom&transferSyntax=*&studyUID=1.2.156.147522.44.410947.2807.20180601083214&seriesUID=1.2.156.147522.44.410947.2807.1.20180601083445&objectUID=1.2.156.147522.44.410947.2807.1.1.20180601083445");
		filepaths.add("http://101.132.45.197:8090/v1/picl/aets/piclarc/wado?requestType=WADO&contentType=application/dicom&transferSyntax=*&studyUID=1.2.156.147522.44.410947.2807.20180601083214&seriesUID=1.2.156.147522.44.410947.2807.1.20180601083445&objectUID=1.2.156.147522.44.410947.2807.1.1.20180601083445");
		tcresult.setFilePath(filepaths);
		
		TCdate tcdate=new TCdate();
		tcdate.setUserName("jfai");
		tcdate.setPassword("jfai123456");
		tcdate.setResult(tcresult);
		HashMap<String, Object> paramMap = new HashMap<>();
		paramMap.put("content", JSON.toJSONString(tcdate));
		String result= HttpUtil.post("http://test.immo.cn/gdtb/postPatientResult", paramMap);
		System.out.println(result);
	}
	
	private static String getPidByPidDcm(String patientID) {
		if(StringUtils.isNotEmpty(patientID)) {
			String[] split = patientID.split("-");
			for (String string : split) {
				System.out.println(string);
			}
			System.out.println(split.length);
			if(split.length>=3) {
				System.out.println(split[1]);
				return split[1];
			}
		}
		return null;
	}
	
//	public static void main(String[] args) {
//		String ptnAge=null;
//		String ptnAgeUnit=null;
//		String ptnAgeUnitCode=null;
//		String birthDay="19431225";
//		boolean validDate = DateUtils.isValidDate(birthDay, DateUtils.patternB);
//		if(validDate) {
//		  Date myday = DateUtils.stringToDate(birthDay, DateUtils.patternB);
//		  Calendar cal = Calendar.getInstance();  
//		  if(cal.after(myday)) {
//			   int yearNow = cal.get(Calendar.YEAR);  
//		       int monthNow = cal.get(Calendar.MONTH);  
//		       int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH);  
//		       cal.setTime(myday);  
//		  
//		       int yearBirth = cal.get(Calendar.YEAR);  
//		       int monthBirth = cal.get(Calendar.MONTH);  
//		       int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);  
//			  
//		       int temYear=yearNow-yearBirth;
//		       int temMonth=monthNow-monthBirth;
//		       int temDay=dayOfMonthNow-dayOfMonthBirth;
//			  
//		       if (temMonth<=0) {  
//		            if (temMonth==0) {  
//		                if (temDay<0) temYear--;  
//		            }else{  
//		            	temYear--;  
//		            }  
//		        }
//		       
//		       if(temYear>0) {
//		    	    ptnAge=String.valueOf(temYear);
//		   		    ptnAgeUnit="岁";
//		   		    ptnAgeUnitCode="3402";
//		       }else if(temMonth>0){
//		    	    ptnAge=String.valueOf(temMonth);
//		   		    ptnAgeUnit="月";
//		   		    ptnAgeUnitCode="3427";
//		       }else if(temDay>0) {
//		    	   if(temDay>7) {
//		    		   ptnAge=String.valueOf(temDay/7);
//			   		   ptnAgeUnit="周";
//			   		   ptnAgeUnitCode="6009";
//		    	   }else {
//		    		   ptnAge=String.valueOf(temDay);
//			   		   ptnAgeUnit="天";
//			   		   ptnAgeUnitCode="3428";
//		    	   }
//		       }
//		  }
//		
//		}
//    }

//	public static void main(String[] args) {
//		 String birthDay="19431225";
//		 Date myday = DateUtils.stringToDate(birthDay, DateUtils.patternB);
//		 boolean after = new Date().after(myday);
//		 if(after) {
//			 System.out.println("myday " + myday + " is before current date.");
//		 }
//	      // create calendar objects.
//	      Calendar cal = Calendar.getInstance();
//	      Calendar future = Calendar.getInstance();
//
//	      // print the current date
//	      System.out.println("Current date: " + cal.getTime());
//
//	      // change year in future calendar
//	      future.set(Calendar.YEAR, 2015);
//	      System.out.println("Year is " + future.get(Calendar.YEAR));
//
//	      // check if calendar date is after current date
//	      Date time = future.getTime();
//	      if (future.after(cal)) {
//	         System.out.println("Date " + time + " is after current date.");
//	      }else {
//	    	  System.out.println("Date " + time + " is before current date."); 
//	      }
//
//	   }
}
