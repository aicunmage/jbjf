package com.jfhealthcare.modules.system.mapper;

import java.util.List;
import java.util.Map;

import com.jfhealthcare.modules.system.entity.SysOperOrgRel;
import com.jfhealthcare.tk.mybatis.util.MyMapper;

public interface SysOperOrgRelMapper extends MyMapper<SysOperOrgRel> {
	
	public List<Map> queryOrgGroupInfo(String operGroupId);
}