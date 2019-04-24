package com.jfhealthcare.modules.system.mapper;

import java.util.List;
import java.util.Map;

import com.jfhealthcare.modules.system.entity.SysRepGroup;
import com.jfhealthcare.modules.system.response.SysRepGroupResponse;
import com.jfhealthcare.tk.mybatis.util.MyMapper;

public interface SysRepGroupMapper extends MyMapper<SysRepGroup> {
	
	public List<SysRepGroupResponse> selectByGroupId(SysRepGroup sysRepGroup);
	
	public List<Map> querySysRepGroupByOperId(String operId);
	
	/**
	 * 判断用户是否属于会诊分组
	 * @param operId
	 * @return
	 */
	public Integer isReportGroupUser(String operId);
}