package com.jfhealthcare.modules.system.response;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import org.springframework.data.annotation.Id;

import lombok.Data;

@Data
public class SysRepGroupResponse {
	/**
	 * 主键
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "select uuid()")
	private String id;

	/**
	 * 分组编号
	 */
	private String groupId;

	private String logincode;
	
	private String userName;

}
