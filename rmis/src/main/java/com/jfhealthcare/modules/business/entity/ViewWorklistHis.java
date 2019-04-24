package com.jfhealthcare.modules.business.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Table;
import java.util.Date;

@Data
@Table(name = "view_worklist_his")
public class ViewWorklistHis {
    /**
     * 患者唯一标识
     */
    @Column(name = "PAT_ONLY_ID")
    private String patOnlyId;

    /**
     * ID
     */
    @Column(name = "PAT_ID")
    private String patId;

    /**
     * 患者姓名
     */
    @Column(name = "PAT_NAME")
    private String patName;

    /**
     * 患者姓名_拼音
     */
    @Column(name = "PAT_NAME_PY")
    private String patNamePy;

    /**
     * 性别Code
     */
    @Column(name = "PAT_SEX_CODE")
    private String patSexCode;

    /**
     * 性别
     */
    @Column(name = "PAT_SEX")
    private String patSex;

    /**
     * 出生日期
     */
    @Column(name = "PAT_BITTH")
    private Date patBitth;

    /**
     * 身份证号
     */
    @Column(name = "PAT_ID_SH")
    private String patIdSh;

    /**
     * 社保号
     */
    @Column(name = "PAT_SOCIALSECURITY")
    private String patSocialsecurity;

    /**
     * 民族Code
     */
    @Column(name = "PAT_NATION_CODE")
    private String patNationCode;

    /**
     * 民族
     */
    @Column(name = "PAT_NATION")
    private String patNation;

    /**
     * 血型Code
     */
    @Column(name = "PAT_BLOODTYPE_CODE")
    private String patBloodtypeCode;

    /**
     * 血型
     */
    @Column(name = "PAT_BLOODTYPE")
    private String patBloodtype;

    /**
     * 联系电话
     */
    @Column(name = "PAT_PHON")
    private String patPhon;

    /**
     * 家庭住址
     */
    @Column(name = "PAT_ADDRESS")
    private String patAddress;

    /**
     * Email
     */
    @Column(name = "PAT_Email")
    private String patEmail;

    /**
     * 患者身份Code
     */
    @Column(name = "PAT_IDENTITY_CODE")
    private String patIdentityCode;

    /**
     * 患者身份
     */
    @Column(name = "PAT_IDENTITY")
    private String patIdentity;

    /**
     * ID
     */
    @Column(name = "PATINFO_ID")
    private String patinfoId;

    /**
     * 患者唯一标识
     */
    @Column(name = "PATINFO_ONLY_ID")
    private String patinfoOnlyId;

    /**
     * 检查单号
     */
    @Column(name = "PATINFO_CHECKLIST_ID")
    private String patinfoChecklistId;

    /**
     * 就诊卡号
     */
    @Column(name = "PATINFO_CARD_NO")
    private String patinfoCardNo;

    /**
     * 住院号
     */
    @Column(name = "PATINFO_IHP_NO")
    private String patinfoIhpNo;

    /**
     * 住院次数
     */
    @Column(name = "PATINFO_VISID")
    private Integer patinfoVisid;

    /**
     * dcm原检查流水号
     */
    @Column(name = "CHECK_ACCESSION_NUM_DCM")
    private String checkAccessionNumDcm;

    /**
     * ID
     */
    @Column(name = "CHECK_ID")
    private String checkId;

    /**
     * 检查单号
     */
    @Column(name = "CHECK_NUM")
    private String checkNum;

    /**
     * 检查流水号
     */
    @Column(name = "CHECK_ACCESSION_NUM")
    private String checkAccessionNum;

    /**
     * 检查部位Code
     */
    @Column(name = "CHECK_PARTS_CODE")
    private String checkPartsCode;

    /**
     * 检查部位
     */
    @Column(name = "CHECK_PARTS")
    private String checkParts;

    /**
     * 检查方法Code
     */
    @Column(name = "CHECK_SUMMARY_CODE")
    private String checkSummaryCode;

    /**
     * 检查方法
     */
    @Column(name = "CHECK_SUMMARY")
    private String checkSummary;

    /**
     * 检查项目Code
     */
    @Column(name = "CHECK_EXAM_CODE")
    private String checkExamCode;

    /**
     * 检查项目
     */
    @Column(name = "CHECK_EXAM")
    private String checkExam;

    /**
     * 金额
     */
    @Column(name = "CHECK_COSTS")
    private Long checkCosts;

    /**
     * 当前状态Code
     */
    @Column(name = "CHECK_STATUS_CODE")
    private String checkStatusCode;

    /**
     * 当前状态
     */
    @Column(name = "CHECK_STATUS")
    private String checkStatus;

    /**
     * AI状态Code
     */
    @Column(name = "CHECK_STATUS_AI_CODE")
    private String checkStatusAiCode;

    /**
     * AI状态
     */
    @Column(name = "CHECK_STATUS_AI")
    private String checkStatusAi;

    /**
     * 检查类型code
     */
    @Column(name = "CHECK_TYPE_CODE")
    private String checkTypeCode;

    /**
     * 检查类型
     */
    @Column(name = "CHECK_TYPE")
    private String checkType;

    /**
     * 检查部位分组Code
     */
    @Column(name = "CHECK_PARTS_GROUP_CODE")
    private String checkPartsGroupCode;

    /**
     * 检查部位分组
     */
    @Column(name = "CHECK_PARTS_GROUP")
    private String checkPartsGroup;

    /**
     * 逻辑删除
     */
    @Column(name = "CHECK_ISDELETE")
    private Boolean checkIsdelete;

    /**
     * 修改人
     */
    @Column(name = "CHECK_UPD_USER")
    private String checkUpdUser;

    /**
     * 修改时间
     */
    @Column(name = "CHECK_UPD_TIME")
    private Date checkUpdTime;

    /**
     * 创建人
     */
    @Column(name = "CHECK_CRT_USER")
    private String checkCrtUser;

    /**
     * 创建时间
     */
    @Column(name = "CHECK_CRT_TIME")
    private Date checkCrtTime;

    /**
     * 备注
     */
    @Column(name = "CHECK_REMARK")
    private String checkRemark;

    /**
     * 申请时间
     */
    @Column(name = "CHECK_APPLY_TIME")
    private Date checkApplyTime;

    /**
     * 影像张数
     */
    @Column(name = "CHECK_IMG_NUM")
    private Integer checkImgNum;

    /**
     * 图像接收时间
     */
    @Column(name = "CHECK_IMG_RECEIVE_TIME")
    private Date checkImgReceiveTime;

    /**
     * 申请医生Code
     */
    @Column(name = "CHECK_APPLY_DOC_CODE")
    private String checkApplyDocCode;

    /**
     * 申请医生
     */
    @Column(name = "CHECK_APPLY_DOC")
    private String checkApplyDoc;

    /**
     * 申请医院Code
     */
    @Column(name = "CHECK_APPLY_HOSP_CODE")
    private String checkApplyHospCode;

    /**
     * 申请医院
     */
    @Column(name = "CHECK_APPLY_HOSP")
    private String checkApplyHosp;

    /**
     * 报告分组ID
     */
    @Column(name = "CHECK_REP_GROUP_ID")
    private String checkRepGroupId;

    /**
     * 急诊
     */
    @Column(name = "CHECK_JZ_FLAG")
    private Boolean checkJzFlag;

    /**
     * 危急
     */
    @Column(name = "CHECK_VJ_FLAG")
    private Boolean checkVjFlag;

    /**
     * 报告医生
     */
    @Column(name = "CHECK_REPORT_DR")
    private String checkReportDr;

    /**
     * 报告世间
     */
    @Column(name = "CHECK_REPORT_TIME")
    private Date checkReportTime;

    /**
     * 审核医生
     */
    @Column(name = "CHECK_AUDIT_DR")
    private String checkAuditDr;

    /**
     * 审核世间
     */
    @Column(name = "CHECK_AUDIT_TIME")
    private Date checkAuditTime;

    /**
     * 打印医生
     */
    @Column(name = "CHECK_PRINT_DR")
    private String checkPrintDr;

    /**
     * 打印世间
     */
    @Column(name = "CHECK_PRINT_TIME")
    private Date checkPrintTime;

    /**
     * 拒绝字典code
     */
    @Column(name = "CHECK_REFUSE_CODE")
    private String checkRefuseCode;

    /**
     * 拒绝name
     */
    @Column(name = "CHECK_REFUSE_NAME")
    private String checkRefuseName;

    /**
     * ID
     */
    @Column(name = "REPRCD_ID")
    private String reprcdId;

    /**
     * 检查流水号
     */
    @Column(name = "REPRCD_ACCESSION_NUM")
    private String reprcdAccessionNum;

    /**
     * 患者唯一标识
     */
    @Column(name = "REPRCD_PAT_ONLY_ID")
    private String reprcdPatOnlyId;

    /**
     * 报告UID
     */
    @Column(name = "REPRCD_REP_UID")
    private String reprcdRepUid;

    /**
     * 患者年龄
     */
    @Column(name = "REPRCD_PAT_AGE")
    private String reprcdPatAge;

    /**
     * 年龄单位Code
     */
    @Column(name = "REPRCD_AGE_UNIT_CODE")
    private String reprcdAgeUnitCode;

    /**
     * 年龄单位
     */
    @Column(name = "REPRCD_AGE_UNIT")
    private String reprcdAgeUnit;

    /**
     * 阴性阳性
     */
    @Column(name = "REPRCD_HP")
    private String reprcdHp;

    /**
     * 患者来源 1：门诊   2：住院
     */
    @Column(name = "CHECK_PTN_SOURCE")
    private String checkPtnSource;

    /**
     * 0--默认，代表普通报告医生处理；1--转报告医生处理，只有报告医生分组可以处理
     */
    @Column(name = "DOC_LEVEL")
    private Integer docLevel;

    /**
     * dcm原ptnid
     */
    @Column(name = "PAT_ID_DCM")
    private String patIdDcm;

    /**
     * 病情描述
     */
    @Column(name = "CHECK_DESCRIBE_BQ")
    private String checkDescribeBq;

    /**
     * 影像所见1
     */
    @Column(name = "REPRCD_FINDING1")
    private String reprcdFinding1;

    /**
     * 影像所见2
     */
    @Column(name = "REPRCD_FINDING2")
    private String reprcdFinding2;

    /**
     * 影像所见3
     */
    @Column(name = "REPRCD_FINDING3")
    private String reprcdFinding3;

    /**
     * 诊断建议1
     */
    @Column(name = "REPRCD_IMPRESSION1")
    private String reprcdImpression1;

    /**
     * 诊断建议2
     */
    @Column(name = "REPRCD_IMPRESSION2")
    private String reprcdImpression2;

    /**
     * 诊断建议3
     */
    @Column(name = "REPRCD_IMPRESSION3")
    private String reprcdImpression3;

    /**
     * 既往史
     */
    @Column(name = "CHECK_PAST_ILLNESS")
    private String checkPastIllness;

    /**
     * 报告评级
     */
    @Column(name = "RATE")
    private String rate;

    /**
     * 报告建议
     */
    @Column(name = "SUGGEST")
    private String suggest;

}