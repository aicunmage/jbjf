package com.jfhealthcare.modules.system.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "sys_oper_org_rel")
public class SysOperOrgRel {
    @Id
    @Column(name = "relation_id")
    private Integer relationId;

    @Column(name = "oper_group_id")
    private String operGroupId;

    @Column(name = "org_group_id")
    private Integer orgGroupId;

    @Column(name = "is_valid")
    private Integer isValid;

    @Column(name = "create_date")
    private Date createDate;

    @Column(name = "expire_date")
    private Date expireDate;

    private String extend1;

    private String extend2;

    private String extend3;

    /**
     * @return relation_id
     */
    public Integer getRelationId() {
        return relationId;
    }

    /**
     * @param relationId
     */
    public void setRelationId(Integer relationId) {
        this.relationId = relationId;
    }

    /**
     * @return oper_group_id
     */
    public String getOperGroupId() {
        return operGroupId;
    }

    /**
     * @param operGroupId
     */
    public void setOperGroupId(String operGroupId) {
        this.operGroupId = operGroupId;
    }

    /**
     * @return org_group_id
     */
    public Integer getOrgGroupId() {
        return orgGroupId;
    }

    /**
     * @param orgGroupId
     */
    public void setOrgGroupId(Integer orgGroupId) {
        this.orgGroupId = orgGroupId;
    }

    /**
     * @return is_valid
     */
    public Integer getIsValid() {
        return isValid;
    }

    /**
     * @param isValid
     */
    public void setIsValid(Integer isValid) {
        this.isValid = isValid;
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
     * @return expire_date
     */
    public Date getExpireDate() {
        return expireDate;
    }

    /**
     * @param expireDate
     */
    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
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