package com.jfhealthcare.modules.business.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "busin_checklist_index_his")
public class BusinChecklistIndexHis {
    /**
     * 检查流水号
     */
    @Id
    @Column(name = "ACCESSION_NUM")
    private String accessionNum;

    /**
     * ID
     */
    @Column(name = "ID")
    private String id;

    /**
     * 检查单号
     */
    @Column(name = "CHECK_NUM")
    private String checkNum;

    /**
     * 检查部位Code
     */
    @Column(name = "PARTS_CODE")
    private String partsCode;

    /**
     * 检查部位
     */
    @Column(name = "PARTS")
    private String parts;

    /**
     * 检查方法Code
     */
    @Column(name = "SUMMARY_CODE")
    private String summaryCode;

    /**
     * 检查方法
     */
    @Column(name = "SUMMARY")
    private String summary;

    /**
     * 检查项目Code
     */
    @Column(name = "EXAM_CODE")
    private String examCode;

    /**
     * 检查项目
     */
    @Column(name = "EXAM")
    private String exam;

    /**
     * 金额
     */
    @Column(name = "COSTS")
    private Long costs;

    /**
     * 当前状态Code
     */
    @Column(name = "STATUS_CODE")
    private String statusCode;

    /**
     * 当前状态
     */
    @Column(name = "STATUS")
    private String status;

    /**
     * AI状态Code
     */
    @Column(name = "STATUS_AI_CODE")
    private String statusAiCode;

    /**
     * AI状态
     */
    @Column(name = "STATUS_AI")
    private String statusAi;

    /**
     * 检查部位分组Code
     */
    @Column(name = "PARTS_GROUP_CODE")
    private String partsGroupCode;

    /**
     * 检查部位分组
     */
    @Column(name = "PARTS_GROUP")
    private String partsGroup;

    /**
     * 检查类型code
     */
    @Column(name = "TYPE_CODE")
    private String typeCode;

    /**
     * 检查类型
     */
    @Column(name = "TYPE")
    private String type;

    /**
     * 逻辑删除
     */
    @Column(name = "ISDELETE")
    private Boolean isdelete;

    /**
     * 修改人
     */
    @Column(name = "UPD_USER")
    private String updUser;

    /**
     * 修改时间
     */
    @Column(name = "UPD_TIME")
    private Date updTime;

    /**
     * 创建人
     */
    @Column(name = "CRT_USER")
    private String crtUser;

    /**
     * 创建时间
     */
    @Column(name = "CRT_TIME")
    private Date crtTime;

    /**
     * 申请时间
     */
    @Column(name = "APPLY_TIME")
    private Date applyTime;

    /**
     * 影像张数
     */
    @Column(name = "IMG_NUM")
    private Integer imgNum;

    /**
     * 图像接收时间
     */
    @Column(name = "IMG_RECEIVE_TIME")
    private Date imgReceiveTime;

    /**
     * 申请医生Code
     */
    @Column(name = "APPLY_DOC_CODE")
    private String applyDocCode;

    /**
     * 申请医生
     */
    @Column(name = "APPLY_DOC")
    private String applyDoc;

    /**
     * 申请医院Code
     */
    @Column(name = "APPLY_HOSP_CODE")
    private String applyHospCode;

    /**
     * 申请医院
     */
    @Column(name = "APPLY_HOSP")
    private String applyHosp;

    /**
     * 报告分组ID
     */
    @Column(name = "REP_GROUP_ID")
    private String repGroupId;

    /**
     * 急诊
     */
    @Column(name = "JZ_FLAG")
    private Boolean jzFlag;

    /**
     * 危急
     */
    @Column(name = "VJ_FLAG")
    private Boolean vjFlag;

    /**
     * 报告医生
     */
    @Column(name = "REPORT_DR")
    private String reportDr;

    /**
     * 报告世间
     */
    @Column(name = "REPORT_TIME")
    private Date reportTime;

    /**
     * 审核医生
     */
    @Column(name = "AUDIT_DR")
    private String auditDr;

    /**
     * 审核世间
     */
    @Column(name = "AUDIT_TIME")
    private Date auditTime;

    /**
     * 打印医生
     */
    @Column(name = "PRINT_DR")
    private String printDr;

    /**
     * 打印世间
     */
    @Column(name = "PRINT_TIME")
    private Date printTime;

    /**
     * 拒绝字典code
     */
    @Column(name = "REFUSE_CODE")
    private String refuseCode;

    /**
     * 拒绝name
     */
    @Column(name = "REFUSE_NAME")
    private String refuseName;

    /**
     * 患者来源 1：门诊   2：住院
     */
    @Column(name = "PTN_SOURCE")
    private String ptnSource;

    /**
     * dcm原检查流水号
     */
    @Column(name = "accession_number_dcm")
    private String accessionNumberDcm;

    /**
     * 0--默认，代表普通报告医生处理；1--转报告医生处理，只有报告医生分组可以处理
     */
    @Column(name = "DOC_LEVEL")
    private Integer docLevel;

    @Column(name = "transfer_date")
    private Date transferDate;

    /**
     * 是否由审核医生退回重写处理，0-否，1-是
     */
    @Column(name = "IS_REPULSE")
    private Boolean isRepulse;

    /**
     * 备注
     */
    @Column(name = "REMARK")
    private String remark;

    /**
     * 病情描述
     */
    @Column(name = "DESCRIBE_BQ")
    private String describeBq;

    /**
     * 既往史
     */
    @Column(name = "PAST_ILLNESS")
    private String pastIllness;

    /**
     * 获取检查流水号
     *
     * @return ACCESSION_NUM - 检查流水号
     */
    public String getAccessionNum() {
        return accessionNum;
    }

    /**
     * 设置检查流水号
     *
     * @param accessionNum 检查流水号
     */
    public void setAccessionNum(String accessionNum) {
        this.accessionNum = accessionNum;
    }

    /**
     * 获取ID
     *
     * @return ID - ID
     */
    public String getId() {
        return id;
    }

    /**
     * 设置ID
     *
     * @param id ID
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 获取检查单号
     *
     * @return CHECK_NUM - 检查单号
     */
    public String getCheckNum() {
        return checkNum;
    }

    /**
     * 设置检查单号
     *
     * @param checkNum 检查单号
     */
    public void setCheckNum(String checkNum) {
        this.checkNum = checkNum;
    }

    /**
     * 获取检查部位Code
     *
     * @return PARTS_CODE - 检查部位Code
     */
    public String getPartsCode() {
        return partsCode;
    }

    /**
     * 设置检查部位Code
     *
     * @param partsCode 检查部位Code
     */
    public void setPartsCode(String partsCode) {
        this.partsCode = partsCode;
    }

    /**
     * 获取检查部位
     *
     * @return PARTS - 检查部位
     */
    public String getParts() {
        return parts;
    }

    /**
     * 设置检查部位
     *
     * @param parts 检查部位
     */
    public void setParts(String parts) {
        this.parts = parts;
    }

    /**
     * 获取检查方法Code
     *
     * @return SUMMARY_CODE - 检查方法Code
     */
    public String getSummaryCode() {
        return summaryCode;
    }

    /**
     * 设置检查方法Code
     *
     * @param summaryCode 检查方法Code
     */
    public void setSummaryCode(String summaryCode) {
        this.summaryCode = summaryCode;
    }

    /**
     * 获取检查方法
     *
     * @return SUMMARY - 检查方法
     */
    public String getSummary() {
        return summary;
    }

    /**
     * 设置检查方法
     *
     * @param summary 检查方法
     */
    public void setSummary(String summary) {
        this.summary = summary;
    }

    /**
     * 获取检查项目Code
     *
     * @return EXAM_CODE - 检查项目Code
     */
    public String getExamCode() {
        return examCode;
    }

    /**
     * 设置检查项目Code
     *
     * @param examCode 检查项目Code
     */
    public void setExamCode(String examCode) {
        this.examCode = examCode;
    }

    /**
     * 获取检查项目
     *
     * @return EXAM - 检查项目
     */
    public String getExam() {
        return exam;
    }

    /**
     * 设置检查项目
     *
     * @param exam 检查项目
     */
    public void setExam(String exam) {
        this.exam = exam;
    }

    /**
     * 获取金额
     *
     * @return COSTS - 金额
     */
    public Long getCosts() {
        return costs;
    }

    /**
     * 设置金额
     *
     * @param costs 金额
     */
    public void setCosts(Long costs) {
        this.costs = costs;
    }

    /**
     * 获取当前状态Code
     *
     * @return STATUS_CODE - 当前状态Code
     */
    public String getStatusCode() {
        return statusCode;
    }

    /**
     * 设置当前状态Code
     *
     * @param statusCode 当前状态Code
     */
    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    /**
     * 获取当前状态
     *
     * @return STATUS - 当前状态
     */
    public String getStatus() {
        return status;
    }

    /**
     * 设置当前状态
     *
     * @param status 当前状态
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * 获取AI状态Code
     *
     * @return STATUS_AI_CODE - AI状态Code
     */
    public String getStatusAiCode() {
        return statusAiCode;
    }

    /**
     * 设置AI状态Code
     *
     * @param statusAiCode AI状态Code
     */
    public void setStatusAiCode(String statusAiCode) {
        this.statusAiCode = statusAiCode;
    }

    /**
     * 获取AI状态
     *
     * @return STATUS_AI - AI状态
     */
    public String getStatusAi() {
        return statusAi;
    }

    /**
     * 设置AI状态
     *
     * @param statusAi AI状态
     */
    public void setStatusAi(String statusAi) {
        this.statusAi = statusAi;
    }

    /**
     * 获取检查部位分组Code
     *
     * @return PARTS_GROUP_CODE - 检查部位分组Code
     */
    public String getPartsGroupCode() {
        return partsGroupCode;
    }

    /**
     * 设置检查部位分组Code
     *
     * @param partsGroupCode 检查部位分组Code
     */
    public void setPartsGroupCode(String partsGroupCode) {
        this.partsGroupCode = partsGroupCode;
    }

    /**
     * 获取检查部位分组
     *
     * @return PARTS_GROUP - 检查部位分组
     */
    public String getPartsGroup() {
        return partsGroup;
    }

    /**
     * 设置检查部位分组
     *
     * @param partsGroup 检查部位分组
     */
    public void setPartsGroup(String partsGroup) {
        this.partsGroup = partsGroup;
    }

    /**
     * 获取检查类型code
     *
     * @return TYPE_CODE - 检查类型code
     */
    public String getTypeCode() {
        return typeCode;
    }

    /**
     * 设置检查类型code
     *
     * @param typeCode 检查类型code
     */
    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }

    /**
     * 获取检查类型
     *
     * @return TYPE - 检查类型
     */
    public String getType() {
        return type;
    }

    /**
     * 设置检查类型
     *
     * @param type 检查类型
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * 获取逻辑删除
     *
     * @return ISDELETE - 逻辑删除
     */
    public Boolean getIsdelete() {
        return isdelete;
    }

    /**
     * 设置逻辑删除
     *
     * @param isdelete 逻辑删除
     */
    public void setIsdelete(Boolean isdelete) {
        this.isdelete = isdelete;
    }

    /**
     * 获取修改人
     *
     * @return UPD_USER - 修改人
     */
    public String getUpdUser() {
        return updUser;
    }

    /**
     * 设置修改人
     *
     * @param updUser 修改人
     */
    public void setUpdUser(String updUser) {
        this.updUser = updUser;
    }

    /**
     * 获取修改时间
     *
     * @return UPD_TIME - 修改时间
     */
    public Date getUpdTime() {
        return updTime;
    }

    /**
     * 设置修改时间
     *
     * @param updTime 修改时间
     */
    public void setUpdTime(Date updTime) {
        this.updTime = updTime;
    }

    /**
     * 获取创建人
     *
     * @return CRT_USER - 创建人
     */
    public String getCrtUser() {
        return crtUser;
    }

    /**
     * 设置创建人
     *
     * @param crtUser 创建人
     */
    public void setCrtUser(String crtUser) {
        this.crtUser = crtUser;
    }

    /**
     * 获取创建时间
     *
     * @return CRT_TIME - 创建时间
     */
    public Date getCrtTime() {
        return crtTime;
    }

    /**
     * 设置创建时间
     *
     * @param crtTime 创建时间
     */
    public void setCrtTime(Date crtTime) {
        this.crtTime = crtTime;
    }

    /**
     * 获取申请时间
     *
     * @return APPLY_TIME - 申请时间
     */
    public Date getApplyTime() {
        return applyTime;
    }

    /**
     * 设置申请时间
     *
     * @param applyTime 申请时间
     */
    public void setApplyTime(Date applyTime) {
        this.applyTime = applyTime;
    }

    /**
     * 获取影像张数
     *
     * @return IMG_NUM - 影像张数
     */
    public Integer getImgNum() {
        return imgNum;
    }

    /**
     * 设置影像张数
     *
     * @param imgNum 影像张数
     */
    public void setImgNum(Integer imgNum) {
        this.imgNum = imgNum;
    }

    /**
     * 获取图像接收时间
     *
     * @return IMG_RECEIVE_TIME - 图像接收时间
     */
    public Date getImgReceiveTime() {
        return imgReceiveTime;
    }

    /**
     * 设置图像接收时间
     *
     * @param imgReceiveTime 图像接收时间
     */
    public void setImgReceiveTime(Date imgReceiveTime) {
        this.imgReceiveTime = imgReceiveTime;
    }

    /**
     * 获取申请医生Code
     *
     * @return APPLY_DOC_CODE - 申请医生Code
     */
    public String getApplyDocCode() {
        return applyDocCode;
    }

    /**
     * 设置申请医生Code
     *
     * @param applyDocCode 申请医生Code
     */
    public void setApplyDocCode(String applyDocCode) {
        this.applyDocCode = applyDocCode;
    }

    /**
     * 获取申请医生
     *
     * @return APPLY_DOC - 申请医生
     */
    public String getApplyDoc() {
        return applyDoc;
    }

    /**
     * 设置申请医生
     *
     * @param applyDoc 申请医生
     */
    public void setApplyDoc(String applyDoc) {
        this.applyDoc = applyDoc;
    }

    /**
     * 获取申请医院Code
     *
     * @return APPLY_HOSP_CODE - 申请医院Code
     */
    public String getApplyHospCode() {
        return applyHospCode;
    }

    /**
     * 设置申请医院Code
     *
     * @param applyHospCode 申请医院Code
     */
    public void setApplyHospCode(String applyHospCode) {
        this.applyHospCode = applyHospCode;
    }

    /**
     * 获取申请医院
     *
     * @return APPLY_HOSP - 申请医院
     */
    public String getApplyHosp() {
        return applyHosp;
    }

    /**
     * 设置申请医院
     *
     * @param applyHosp 申请医院
     */
    public void setApplyHosp(String applyHosp) {
        this.applyHosp = applyHosp;
    }

    /**
     * 获取报告分组ID
     *
     * @return REP_GROUP_ID - 报告分组ID
     */
    public String getRepGroupId() {
        return repGroupId;
    }

    /**
     * 设置报告分组ID
     *
     * @param repGroupId 报告分组ID
     */
    public void setRepGroupId(String repGroupId) {
        this.repGroupId = repGroupId;
    }

    /**
     * 获取急诊
     *
     * @return JZ_FLAG - 急诊
     */
    public Boolean getJzFlag() {
        return jzFlag;
    }

    /**
     * 设置急诊
     *
     * @param jzFlag 急诊
     */
    public void setJzFlag(Boolean jzFlag) {
        this.jzFlag = jzFlag;
    }

    /**
     * 获取危急
     *
     * @return VJ_FLAG - 危急
     */
    public Boolean getVjFlag() {
        return vjFlag;
    }

    /**
     * 设置危急
     *
     * @param vjFlag 危急
     */
    public void setVjFlag(Boolean vjFlag) {
        this.vjFlag = vjFlag;
    }

    /**
     * 获取报告医生
     *
     * @return REPORT_DR - 报告医生
     */
    public String getReportDr() {
        return reportDr;
    }

    /**
     * 设置报告医生
     *
     * @param reportDr 报告医生
     */
    public void setReportDr(String reportDr) {
        this.reportDr = reportDr;
    }

    /**
     * 获取报告世间
     *
     * @return REPORT_TIME - 报告世间
     */
    public Date getReportTime() {
        return reportTime;
    }

    /**
     * 设置报告世间
     *
     * @param reportTime 报告世间
     */
    public void setReportTime(Date reportTime) {
        this.reportTime = reportTime;
    }

    /**
     * 获取审核医生
     *
     * @return AUDIT_DR - 审核医生
     */
    public String getAuditDr() {
        return auditDr;
    }

    /**
     * 设置审核医生
     *
     * @param auditDr 审核医生
     */
    public void setAuditDr(String auditDr) {
        this.auditDr = auditDr;
    }

    /**
     * 获取审核世间
     *
     * @return AUDIT_TIME - 审核世间
     */
    public Date getAuditTime() {
        return auditTime;
    }

    /**
     * 设置审核世间
     *
     * @param auditTime 审核世间
     */
    public void setAuditTime(Date auditTime) {
        this.auditTime = auditTime;
    }

    /**
     * 获取打印医生
     *
     * @return PRINT_DR - 打印医生
     */
    public String getPrintDr() {
        return printDr;
    }

    /**
     * 设置打印医生
     *
     * @param printDr 打印医生
     */
    public void setPrintDr(String printDr) {
        this.printDr = printDr;
    }

    /**
     * 获取打印世间
     *
     * @return PRINT_TIME - 打印世间
     */
    public Date getPrintTime() {
        return printTime;
    }

    /**
     * 设置打印世间
     *
     * @param printTime 打印世间
     */
    public void setPrintTime(Date printTime) {
        this.printTime = printTime;
    }

    /**
     * 获取拒绝字典code
     *
     * @return REFUSE_CODE - 拒绝字典code
     */
    public String getRefuseCode() {
        return refuseCode;
    }

    /**
     * 设置拒绝字典code
     *
     * @param refuseCode 拒绝字典code
     */
    public void setRefuseCode(String refuseCode) {
        this.refuseCode = refuseCode;
    }

    /**
     * 获取拒绝name
     *
     * @return REFUSE_NAME - 拒绝name
     */
    public String getRefuseName() {
        return refuseName;
    }

    /**
     * 设置拒绝name
     *
     * @param refuseName 拒绝name
     */
    public void setRefuseName(String refuseName) {
        this.refuseName = refuseName;
    }

    /**
     * 获取患者来源 1：门诊   2：住院
     *
     * @return PTN_SOURCE - 患者来源 1：门诊   2：住院
     */
    public String getPtnSource() {
        return ptnSource;
    }

    /**
     * 设置患者来源 1：门诊   2：住院
     *
     * @param ptnSource 患者来源 1：门诊   2：住院
     */
    public void setPtnSource(String ptnSource) {
        this.ptnSource = ptnSource;
    }

    /**
     * 获取dcm原检查流水号
     *
     * @return accession_number_dcm - dcm原检查流水号
     */
    public String getAccessionNumberDcm() {
        return accessionNumberDcm;
    }

    /**
     * 设置dcm原检查流水号
     *
     * @param accessionNumberDcm dcm原检查流水号
     */
    public void setAccessionNumberDcm(String accessionNumberDcm) {
        this.accessionNumberDcm = accessionNumberDcm;
    }

    /**
     * 获取0--默认，代表普通报告医生处理；1--转报告医生处理，只有报告医生分组可以处理
     *
     * @return DOC_LEVEL - 0--默认，代表普通报告医生处理；1--转报告医生处理，只有报告医生分组可以处理
     */
    public Integer getDocLevel() {
        return docLevel;
    }

    /**
     * 设置0--默认，代表普通报告医生处理；1--转报告医生处理，只有报告医生分组可以处理
     *
     * @param docLevel 0--默认，代表普通报告医生处理；1--转报告医生处理，只有报告医生分组可以处理
     */
    public void setDocLevel(Integer docLevel) {
        this.docLevel = docLevel;
    }

    /**
     * @return transfer_date
     */
    public Date getTransferDate() {
        return transferDate;
    }

    /**
     * @param transferDate
     */
    public void setTransferDate(Date transferDate) {
        this.transferDate = transferDate;
    }

    /**
     * 获取是否由审核医生退回重写处理，0-否，1-是
     *
     * @return IS_REPULSE - 是否由审核医生退回重写处理，0-否，1-是
     */
    public Boolean getIsRepulse() {
        return isRepulse;
    }

    /**
     * 设置是否由审核医生退回重写处理，0-否，1-是
     *
     * @param isRepulse 是否由审核医生退回重写处理，0-否，1-是
     */
    public void setIsRepulse(Boolean isRepulse) {
        this.isRepulse = isRepulse;
    }

    /**
     * 获取备注
     *
     * @return REMARK - 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置备注
     *
     * @param remark 备注
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * 获取病情描述
     *
     * @return DESCRIBE_BQ - 病情描述
     */
    public String getDescribeBq() {
        return describeBq;
    }

    /**
     * 设置病情描述
     *
     * @param describeBq 病情描述
     */
    public void setDescribeBq(String describeBq) {
        this.describeBq = describeBq;
    }

    /**
     * 获取既往史
     *
     * @return PAST_ILLNESS - 既往史
     */
    public String getPastIllness() {
        return pastIllness;
    }

    /**
     * 设置既往史
     *
     * @param pastIllness 既往史
     */
    public void setPastIllness(String pastIllness) {
        this.pastIllness = pastIllness;
    }
}