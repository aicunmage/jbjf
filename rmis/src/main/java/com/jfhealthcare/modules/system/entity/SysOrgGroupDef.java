package com.jfhealthcare.modules.system.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "sys_org_group_def")
public class SysOrgGroupDef {
    @Id
    @Column(name = "group_id")
    private Integer groupId;

    @Column(name = "group_name")
    private String groupName;

    @Column(name = "group_pro")
    private String groupPro;

    @Column(name = "create_date")
    private Date createDate;

    private String extend1;

    private String extend2;

    private String extend3;

    /**
     * @return group_id
     */
    public Integer getGroupId() {
        return groupId;
    }

    /**
     * @param groupId
     */
    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    /**
     * @return group_name
     */
    public String getGroupName() {
        return groupName;
    }

    /**
     * @param groupName
     */
    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    /**
     * @return group_pro
     */
    public String getGroupPro() {
        return groupPro;
    }

    /**
     * @param groupPro
     */
    public void setGroupPro(String groupPro) {
        this.groupPro = groupPro;
    }

    /**
     * @return create_date
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * @param createDate
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * @return extend1
     */
    public String getExtend1() {
        return extend1;
    }

    /**
     * @param extend1
     */
    public void setExtend1(String extend1) {
        this.extend1 = extend1;
    }

    /**
     * @return extend2
     */
    public String getExtend2() {
        return extend2;
    }

    /**
     * @param extend2
     */
    public void setExtend2(String extend2) {
        this.extend2 = extend2;
    }

    /**
     * @return extend3
     */
    public String getExtend3() {
        return extend3;
    }

    /**
     * @param extend3
     */
    public void setExtend3(String extend3) {
        this.extend3 = extend3;
    }
}