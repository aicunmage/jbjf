package com.jfhealthcare.modules.business.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "busin_checklist_items_his")
public class BusinChecklistItemsHis {
    /**
     * ID
     */
    @Id
    @Column(name = "ID")
    private String id;

    /**
     * 序号
     */
    @Column(name = "NUMBER_ID")
    private Integer numberId;

    /**
     * 流水号
     */
    @Column(name = "ACCESSION_NUM")
    private String accessionNum;

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
     * 检查部位EN
     */
    @Column(name = "PARTS_EN")
    private String partsEn;

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
     * 检查方法EN
     */
    @Column(name = "SUMMARY_EN")
    private String summaryEn;

    /**
     * 部位左右标识CODE
     */
    @Column(name = "BODY_ALIGN_CODE")
    private String bodyAlignCode;

    /**
     * 部位左右标识
     */
    @Column(name = "BODY_ALIGN")
    private String bodyAlign;

    /**
     * 金额
     */
    @Column(name = "COSTS")
    private Long costs;

    /**
     * 检查项目Code
     */
    @Column(name = "EXAM_ITEM_CODE")
    private String examItemCode;

    /**
     * 检查项目
     */
    @Column(name = "EXAM_ITEM")
    private String examItem;

    /**
     * 检查项目EN
     */
    @Column(name = "EXAM_ITEM_EN")
    private String examItemEn;

    /**
     * 部位分组Code
     */
    @Column(name = "PARTSGROUP_CODE")
    private String partsgroupCode;

    /**
     * 部位分组
     */
    @Column(name = "PARTSGROUP")
    private String partsgroup;

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

    @Column(name = "transfer_date")
    private Date transferDate;

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
     * 获取序号
     *
     * @return NUMBER_ID - 序号
     */
    public Integer getNumberId() {
        return numberId;
    }

    /**
     * 设置序号
     *
     * @param numberId 序号
     */
    public void setNumberId(Integer numberId) {
        this.numberId = numberId;
    }

    /**
     * 获取流水号
     *
     * @return ACCESSION_NUM - 流水号
     */
    public String getAccessionNum() {
        return accessionNum;
    }

    /**
     * 设置流水号
     *
     * @param accessionNum 流水号
     */
    public void setAccessionNum(String accessionNum) {
        this.accessionNum = accessionNum;
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
     * 获取检查部位EN
     *
     * @return PARTS_EN - 检查部位EN
     */
    public String getPartsEn() {
        return partsEn;
    }

    /**
     * 设置检查部位EN
     *
     * @param partsEn 检查部位EN
     */
    public void setPartsEn(String partsEn) {
        this.partsEn = partsEn;
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
     * 获取检查方法EN
     *
     * @return SUMMARY_EN - 检查方法EN
     */
    public String getSummaryEn() {
        return summaryEn;
    }

    /**
     * 设置检查方法EN
     *
     * @param summaryEn 检查方法EN
     */
    public void setSummaryEn(String summaryEn) {
        this.summaryEn = summaryEn;
    }

    /**
     * 获取部位左右标识CODE
     *
     * @return BODY_ALIGN_CODE - 部位左右标识CODE
     */
    public String getBodyAlignCode() {
        return bodyAlignCode;
    }

    /**
     * 设置部位左右标识CODE
     *
     * @param bodyAlignCode 部位左右标识CODE
     */
    public void setBodyAlignCode(String bodyAlignCode) {
        this.bodyAlignCode = bodyAlignCode;
    }

    /**
     * 获取部位左右标识
     *
     * @return BODY_ALIGN - 部位左右标识
     */
    public String getBodyAlign() {
        return bodyAlign;
    }

    /**
     * 设置部位左右标识
     *
     * @param bodyAlign 部位左右标识
     */
    public void setBodyAlign(String bodyAlign) {
        this.bodyAlign = bodyAlign;
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
     * 获取检查项目Code
     *
     * @return EXAM_ITEM_CODE - 检查项目Code
     */
    public String getExamItemCode() {
        return examItemCode;
    }

    /**
     * 设置检查项目Code
     *
     * @param examItemCode 检查项目Code
     */
    public void setExamItemCode(String examItemCode) {
        this.examItemCode = examItemCode;
    }

    /**
     * 获取检查项目
     *
     * @return EXAM_ITEM - 检查项目
     */
    public String getExamItem() {
        return examItem;
    }

    /**
     * 设置检查项目
     *
     * @param examItem 检查项目
     */
    public void setExamItem(String examItem) {
        this.examItem = examItem;
    }

    /**
     * 获取检查项目EN
     *
     * @return EXAM_ITEM_EN - 检查项目EN
     */
    public String getExamItemEn() {
        return examItemEn;
    }

    /**
     * 设置检查项目EN
     *
     * @param examItemEn 检查项目EN
     */
    public void setExamItemEn(String examItemEn) {
        this.examItemEn = examItemEn;
    }

    /**
     * 获取部位分组Code
     *
     * @return PARTSGROUP_CODE - 部位分组Code
     */
    public String getPartsgroupCode() {
        return partsgroupCode;
    }

    /**
     * 设置部位分组Code
     *
     * @param partsgroupCode 部位分组Code
     */
    public void setPartsgroupCode(String partsgroupCode) {
        this.partsgroupCode = partsgroupCode;
    }

    /**
     * 获取部位分组
     *
     * @return PARTSGROUP - 部位分组
     */
    public String getPartsgroup() {
        return partsgroup;
    }

    /**
     * 设置部位分组
     *
     * @param partsgroup 部位分组
     */
    public void setPartsgroup(String partsgroup) {
        this.partsgroup = partsgroup;
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
}