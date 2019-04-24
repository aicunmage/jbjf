package com.jfhealthcare.modules.basics;

import java.util.List;
import lombok.Data;

/**
 * 信息发给tc
 */
@Data
public class TCresult {

	private String id;
	
	private String accessionNum;
	
	private String studyUid;
	
	private String applyDoc;
	
	private String applyOrg;
	
	private String ptnId;
	
	private String checkNum;
	
	private String checkTime;
	
	private String ptnName;
	
	private String sex;
	
	private String ptnAge;
	
	private String ptnAgeUnit;
	
	private String imageId;
	
	private String bodyPart;
	
	private String finding;
	
	private String impression;
	
	private String hp;
	
	private List<String> filePath;
	
	
}
