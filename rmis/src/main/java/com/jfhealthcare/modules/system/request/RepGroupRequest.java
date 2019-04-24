package com.jfhealthcare.modules.system.request;

import java.util.Date;
import java.util.List;

import org.hibernate.validator.constraints.NotBlank;

import com.jfhealthcare.common.validator.group.Edit;
import com.jfhealthcare.modules.basics.BasicPageEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class RepGroupRequest extends BasicPageEntity{
	 /**
     * 分组编号
     */
	@NotBlank(message="id不能为空!",groups={Edit.class})
	private String id;

    /**
     * 分组名称
     */
    private String name;
    
    /**
     * 拼音简码
     */
    private String namepy;

    /**
     * 五笔简码
     */
    private String namewb;

	/**
	 * 更改人
	 */
	private String updUser;

	/**
	 * 更改时间
	 */
	private Date updTime;

	/**
	 * 创建人
	 */
	private String crtUser;

	/**
	 * 创建时间
	 */
	private Date crtTime;
  
	/**
	 * 0:普通  1：会诊
	 */
	private String status;
	
	/**
	 * 排序
	 */
	private Integer nindex;
	
	/**
	 * 机构分组权限
	 */
	private List<String> orgGroupInfo;
	
	/**
	 * 影像中心ID
	 */
	private String orgId;
	
	/**
	 * 管理员列表，供查询分组用
	 */
	private List<String> adminList;
	
	/**
	 * 所属影像中心
	 */
	private String imageCenterId;
}
