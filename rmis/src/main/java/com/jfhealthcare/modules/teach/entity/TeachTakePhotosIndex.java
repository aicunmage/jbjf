package com.jfhealthcare.modules.teach.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "teach_takephotos_index")
public class TeachTakePhotosIndex {
    /**
     * 主键ID
     */
    @Id
    private Integer id;

    /**
     * 名称
     */
    private String name;

    /**
     * 上级
     */
    private Integer upper;

    /**
     * 排序
     */
    private String nindex;

    /**
     * 类型CODE
     */
    @Column(name = "type_code")
    private String typeCode;

    /**
     * 类型
     */
    private String type;

    private String namepy;

    private String namewb;

    @Column(name = "upd_user")
    private String updUser;

    @Column(name = "upd_time")
    private Date updTime;

    @Column(name = "crt_user")
    private String crtUser;

    @Column(name = "crt_time")
    private Date crtTime;

    private String remark;

    /**
     * 是否删除
     */
    private Boolean isdelete;

    /**
     * 预留
     */
    private String extend1;

    /**
     * 预留
     */
    private String extend2;

    /**
     * 预留
     */
    private String extend3;

    /**
     * 获取主键ID
     *
     * @return id - 主键ID
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置主键ID
     *
     * @param id 主键ID
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取名称
     *
     * @return name - 名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置名称
     *
     * @param name 名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取上级
     *
     * @return upper - 上级
     */
    public Integer getUpper() {
        return upper;
    }

    /**
     * 设置上级
     *
     * @param upper 上级
     */
    public void setUpper(Integer upper) {
        this.upper = upper;
    }

    /**
     * 获取排序
     *
     * @return nindex - 排序
     */
    public String getNindex() {
        return nindex;
    }

    /**
     * 设置排序
     *
     * @param nindex 排序
     */
    public void setNindex(String nindex) {
        this.nindex = nindex;
    }

    /**
     * 获取类型CODE
     *
     * @return type_code - 类型CODE
     */
    public String getTypeCode() {
        return typeCode;
    }

    /**
     * 设置类型CODE
     *
     * @param typeCode 类型CODE
     */
    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }

    /**
     * 获取类型
     *
     * @return type - 类型
     */
    public String getType() {
        return type;
    }

    /**
     * 设置类型
     *
     * @param type 类型
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return namepy
     */
    public String getNamepy() {
        return namepy;
    }

    /**
     * @param namepy
     */
    public void setNamepy(String namepy) {
        this.namepy = namepy;
    }

    /**
     * @return namewb
     */
    public String getNamewb() {
        return namewb;
    }

    /**
     * @param namewb
     */
    public void setNamewb(String namewb) {
        this.namewb = namewb;
    }

    /**
     * @return upd_user
     */
    public String getUpdUser() {
        return updUser;
    }

    /**
     * @param updUser
     */
    public void setUpdUser(String updUser) {
        this.updUser = updUser;
    }

    /**
     * @return upd_time
     */
    public Date getUpdTime() {
        return updTime;
    }

    /**
     * @param updTime
     */
    public void setUpdTime(Date updTime) {
        this.updTime = updTime;
    }

    /**
     * @return crt_user
     */
    public String getCrtUser() {
        return crtUser;
    }

    /**
     * @param crtUser
     */
    public void setCrtUser(String crtUser) {
        this.crtUser = crtUser;
    }

    /**
     * @return crt_time
     */
    public Date getCrtTime() {
        return crtTime;
    }

    /**
     * @param crtTime
     */
    public void setCrtTime(Date crtTime) {
        this.crtTime = crtTime;
    }

    /**
     * @return remark
     */
    public String getRemark() {
        return remark;
    }

    /**
     * @param remark
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * 获取是否删除
     *
     * @return isdelete - 是否删除
     */
    public Boolean getIsdelete() {
        return isdelete;
    }

    /**
     * 设置是否删除
     *
     * @param isdelete 是否删除
     */
    public void setIsdelete(Boolean isdelete) {
        this.isdelete = isdelete;
    }

    /**
     * 获取预留
     *
     * @return extend1 - 预留
     */
    public String getExtend1() {
        return extend1;
    }

    /**
     * 设置预留
     *
     * @param extend1 预留
     */
    public void setExtend1(String extend1) {
        this.extend1 = extend1;
    }

    /**
     * 获取预留
     *
     * @return extend2 - 预留
     */
    public String getExtend2() {
        return extend2;
    }

    /**
     * 设置预留
     *
     * @param extend2 预留
     */
    public void setExtend2(String extend2) {
        this.extend2 = extend2;
    }

    /**
     * 获取预留
     *
     * @return extend3 - 预留
     */
    public String getExtend3() {
        return extend3;
    }

    /**
     * 设置预留
     *
     * @param extend3 预留
     */
    public void setExtend3(String extend3) {
        this.extend3 = extend3;
    }
}