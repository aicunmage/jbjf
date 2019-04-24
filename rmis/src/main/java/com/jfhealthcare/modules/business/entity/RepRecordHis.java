package com.jfhealthcare.modules.business.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Table;
import java.util.Date;

@Data
@Table(name = "rep_record_his")
public class RepRecordHis {
    /**
     * ID
     */
    @Column(name = "ID")
    private String id;

    /**
     * 报告UID
     */
    @Column(name = "REP_UID")
    private String repUid;

    /**
     * 检查流水号
     */
    @Column(name = "ACCESSION_NUM")
    private String accessionNum;

    /**
     * 患者唯一标识
     */
    @Column(name = "PAT_ID")
    private String patId;

    /**
     * 患者年龄
     */
    @Column(name = "PAT_AGE")
    private String patAge;

    /**
     * 年龄单位Code
     */
    @Column(name = "AGE_UNIT_CODE")
    private String ageUnitCode;

    /**
     * 年龄单位
     */
    @Column(name = "AGE_UNIT")
    private String ageUnit;

    /**
     * 阴性阳性
     */
    @Column(name = "HP")
    private String hp;

    @Column(name = "transfer_date")
    private Date transferDate;

    /**
     * 影像所见1
     */
    @Column(name = "FINDING1")
    private String finding1;

    /**
     * 影像所见2
     */
    @Column(name = "FINDING2")
    private String finding2;

    /**
     * 影像所见3
     */
    @Column(name = "FINDING3")
    private String finding3;

    /**
     * 诊断建议1
     */
    @Column(name = "IMPRESSION1")
    private String impression1;

    /**
     * 诊断建议2
     */
    @Column(name = "IMPRESSION2")
    private String impression2;

    /**
     * 诊断建议3
     */
    @Column(name = "IMPRESSION3")
    private String impression3;

    /**
     * 报告评级
     */
    private String rate;

    /**
     * 报告意见
     */
    private String suggest;
}