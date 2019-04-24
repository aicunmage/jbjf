package com.jfhealthcare.modules.system.service;

import com.jfhealthcare.modules.system.entity.SysOrgGroupDef;

import java.util.List;

public interface SysOrgGroupDefService {

	public List<SysOrgGroupDef> queryOrgGroupAll();
	
	public void save(SysOrgGroupDef sysOrgGroupDef);
	
	public int updateByPri(SysOrgGroupDef sysOrgGroupDef);
	
	public int deleteByPri(int groupId);
	
	public List<SysOrgGroupDef> queryByCond(SysOrgGroupDef sysOrgGroupDef);
	
	public SysOrgGroupDef queryByPrimary(int groupId);
	
	public String queryMaxIndex();
}
