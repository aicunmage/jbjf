package com.jfhealthcare.modules.system.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jfhealthcare.modules.basics.BasicPageEntity;
import lombok.Data;

import java.util.Date;
import javax.persistence.*;

@Table(name = "sys_org_classify")
public class SysOrgClassify extends BasicPageEntity {
    /**
     * 主键
     */
    @Id
    @Column(name = "ID")
    private Integer id;

    /**
     * 分类名称
     */
    private String name;

    /**
     * 分类属性
     */
    private String attribute;

    /**
     * 排序权重
     */
    private Integer weight;

    /**
     * 分类颜色
     */
    private String colour;

    /**
     * 排序(显示)
     */
    private String nindex;

    /**
     * 所属影像中心code
     */
    @Column(name = "org_code")
    private String orgCode;

    /**
     * 所属影像中心name
     */
    @Column(name = "org_name")
    private String orgName;

    /**
     * 拼音简码
     */
    private String namepy;

    /**
     * 五笔简码
     */
    private String namewb;

    /**
     * 修改人
     */
    @Column(name = "upd_user")
    private String updUser;

    /**
     * 修改时间
     */
    @Column(name = "upd_time")
    private Date updTime;

    /**
     * 创建人
     */
    @Column(name = "crt_user")
    private String crtUser;

    /**
     * 创建时间
     */
    @Column(name = "crt_time")
    private Date crtTime;

    /**
     * 备注
     */
    private String remark;

    /**
     * 预留1
     */
    private String extend1;

    /**
     * 预留2
     */
    private String extend2;

    /**
     * 预留3
     */
    private String extend3;

    /**
     * 获取主键
     *
     * @return ID - 主键
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置主键
     *
     * @param id 主键
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取分类名称
     *
     * @return name - 分类名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置分类名称
     *
     * @param name 分类名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取分类属性
     *
     * @return attribute - 分类属性
     */
    public String getAttribute() {
        return attribute;
    }

    /**
     * 设置分类属性
     *
     * @param attribute 分类属性
     */
    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }

    /**
     * 获取排序权重
     *
     * @return weight - 排序权重
     */
    public Integer getWeight() {
        return weight;
    }

    /**
     * 设置排序权重
     *
     * @param weight 排序权重
     */
    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    /**
     * 获取分类颜色
     *
     * @return colour - 分类颜色
     */
    public String getColour() {
        return colour;
    }

    /**
     * 设置分类颜色
     *
     * @param colour 分类颜色
     */
    public void setColour(String colour) {
        this.colour = colour;
    }

    /**
     * 获取排序(显示)
     *
     * @return nindex - 排序(显示)
     */
    public String getNindex() {
        return nindex;
    }

    /**
     * 设置排序(显示)
     *
     * @param nindex 排序(显示)
     */
    public void setNindex(String nindex) {
        this.nindex = nindex;
    }

    /**
     * 获取所属影像中心code
     *
     * @return org_code - 所属影像中心code
     */
    public String getOrgCode() {
        return orgCode;
    }

    /**
     * 设置所属影像中心code
     *
     * @param orgCode 所属影像中心code
     */
    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    /**
     * 获取所属影像中心name
     *
     * @return org_name - 所属影像中心name
     */
    public String getOrgName() {
        return orgName;
    }

    /**
     * 设置所属影像中心name
     *
     * @param orgName 所属影像中心name
     */
    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    /**
     * 获取拼音简码
     *
     * @return namepy - 拼音简码
     */
    public String getNamepy() {
        return namepy;
    }

    /**
     * 设置拼音简码
     *
     * @param namepy 拼音简码
     */
    public void setNamepy(String namepy) {
        this.namepy = namepy;
    }

    /**
     * 获取五笔简码
     *
     * @return namewb - 五笔简码
     */
    public String getNamewb() {
        return namewb;
    }

    /**
     * 设置五笔简码
     *
     * @param namewb 五笔简码
     */
    public void setNamewb(String namewb) {
        this.namewb = namewb;
    }

    /**
     * 获取修改人
     *
     * @return upd_user - 修改人
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
     * @return upd_time - 修改时间
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
     * @return crt_user - 创建人
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
     * @return crt_time - 创建时间
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
     * 获取备注
     *
     * @return remark - 备注
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
     * 获取预留1
     *
     * @return extend1 - 预留1
     */
    public String getExtend1() {
        return extend1;
    }

    /**
     * 设置预留1
     *
     * @param extend1 预留1
     */
    public void setExtend1(String extend1) {
        this.extend1 = extend1;
    }

    /**
     * 获取预留2
     *
     * @return extend2 - 预留2
     */
    public String getExtend2() {
        return extend2;
    }

    /**
     * 设置预留2
     *
     * @param extend2 预留2
     */
    public void setExtend2(String extend2) {
        this.extend2 = extend2;
    }

    /**
     * 获取预留3
     *
     * @return extend3 - 预留3
     */
    public String getExtend3() {
        return extend3;
    }

    /**
     * 设置预留3
     *
     * @param extend3 预留3
     */
    public void setExtend3(String extend3) {
        this.extend3 = extend3;
    }
}