package com.jfhealthcare.modules.apply.request;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.persistence.*;

import com.jfhealthcare.modules.basics.BasicPageEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class ApplyWorklistRequest extends BasicPageEntity{
    /**
     * id
     */
    private String id;
    
    /**
     * 检查单号
     */
    private String checkNum;

    /**
     * 患者ID
     */
    private String ptnId;

    /**
     * 患者姓名
     */
    private String ptnName;

    /**
     * 急诊
     */
    private Boolean checkJzFlag;
    
    /**
     * 选中时间范围
     */
    private Integer checkDate;
    
    /**
     * 检查开始时间
     */
    private Date studyStartTime;
    
    /**
     * 检查结束时间
     */
    private Date studyEndTime;

    /**
     * 患者性别
     */
    private String sex;
    
    private String sexCode;

    /**
     * 检查部位
     */
    private String bodyPart;
    
    /**
     * 病情描述
     */
    private String describeBq;

    /**
     * 患者来源  1：门诊 2：住院
     */
    private String checkPtnSource;
    
    /**
     * 既往史
     */
    private String checkPastIllness;

    /**
     * 上传机构
     */
    private String applyOrg;
    
    private String ptnAge;
    
    private String ptnAgeUnit;
    
    private String ptnAgeUnitCode;
    
    /**
     * 检查项目
     */
    private String exam;
    
    /**
     * 检查方法以及code
     */
    private String summary;
     /**
      * instances，接收的图像数组
      */
    private List<String> instanceUids;
    
    /**
     * 患者ID 原dcm
     */
    private String ptnIdDcm;
    
    /**
     * 检查流水号 原dcm
     */
    private String accessionNumberDcm;

    /**
     * 部位相关信息
     */
    private List<Map> bodyPartInfos;

    private List<Map> bodyAligns;

    private List<Map> summaries;
}