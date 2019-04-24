package com.jfhealthcare.modules.business.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "rep_image_his")
public class RepImageHis {
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
     * 编号
     */
    @Column(name = "NUMBER")
    private Integer number;

    /**
     * 图像下标Code
     */
    @Column(name = "IMG_LABEL_CODE")
    private String imgLabelCode;

    /**
     * 图像下标
     */
    @Column(name = "IMG_LABEL")
    private String imgLabel;

    /**
     * 逻辑删除
     */
    @Column(name = "ISDELETE")
    private Boolean isdelete;

    @Column(name = "transfer_date")
    private Date transferDate;

    /**
     * 图像路径
     */
    @Column(name = "IMG_PAGE")
    private String imgPage;

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
     * 获取报告UID
     *
     * @return REP_UID - 报告UID
     */
    public String getRepUid() {
        return repUid;
    }

    /**
     * 设置报告UID
     *
     * @param repUid 报告UID
     */
    public void setRepUid(String repUid) {
        this.repUid = repUid;
    }

    /**
     * 获取编号
     *
     * @return NUMBER - 编号
     */
    public Integer getNumber() {
        return number;
    }

    /**
     * 设置编号
     *
     * @param number 编号
     */
    public void setNumber(Integer number) {
        this.number = number;
    }

    /**
     * 获取图像下标Code
     *
     * @return IMG_LABEL_CODE - 图像下标Code
     */
    public String getImgLabelCode() {
        return imgLabelCode;
    }

    /**
     * 设置图像下标Code
     *
     * @param imgLabelCode 图像下标Code
     */
    public void setImgLabelCode(String imgLabelCode) {
        this.imgLabelCode = imgLabelCode;
    }

    /**
     * 获取图像下标
     *
     * @return IMG_LABEL - 图像下标
     */
    public String getImgLabel() {
        return imgLabel;
    }

    /**
     * 设置图像下标
     *
     * @param imgLabel 图像下标
     */
    public void setImgLabel(String imgLabel) {
        this.imgLabel = imgLabel;
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
     * 获取图像路径
     *
     * @return IMG_PAGE - 图像路径
     */
    public String getImgPage() {
        return imgPage;
    }

    /**
     * 设置图像路径
     *
     * @param imgPage 图像路径
     */
    public void setImgPage(String imgPage) {
        this.imgPage = imgPage;
    }
}