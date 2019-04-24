package com.jfhealthcare.modules.system.service;

import java.util.List;
import java.util.Map;

import com.jfhealthcare.modules.system.entity.SysOrgGroupRel;

public interface SysOrgGroupRelService {

	public List<SysOrgGroupRel> queryByHospId(String hospitalId);
	
	public int save(SysOrgGroupRel sysOrgGroupRel);
	
	public int updateByPrimary(SysOrgGroupRel sysOrgGroupRel);
	
	public int deleteGroupRel(SysOrgGroupRel sysOrgGroupRel);

	public List<Map> getHospitalInfoByGroupId(Integer groupId);

	public List<Map> getHospitalInfoByClassifyId(Integer classifyId);
}
