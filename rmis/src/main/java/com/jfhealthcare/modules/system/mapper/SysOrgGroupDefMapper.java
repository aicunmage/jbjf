package com.jfhealthcare.modules.system.mapper;

import java.util.List;

import com.jfhealthcare.modules.system.entity.SysOrgGroupDef;
import com.jfhealthcare.tk.mybatis.util.MyMapper;

public interface SysOrgGroupDefMapper extends MyMapper<SysOrgGroupDef> {
	
	public List<SysOrgGroupDef> queryByCond(String condtion);
}