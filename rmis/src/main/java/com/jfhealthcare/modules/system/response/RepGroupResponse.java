package com.jfhealthcare.modules.system.response;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import org.springframework.data.annotation.Id;

import lombok.Data;

@Data
public class RepGroupResponse implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 分组编号
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "select uuid()")
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
	 * 0:普通 1：会诊
	 */
	private String status;

	/**
	 * 排序
	 */
	private Integer nindex;
	
	/**
	 * 机构分组权限
	 */
	private List<Object> orgGroupInfo;
	
	/**
	 * 所属影像中心ID
	 */
	private String imageCenterId;
}
