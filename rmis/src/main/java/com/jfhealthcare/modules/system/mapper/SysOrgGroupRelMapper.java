package com.jfhealthcare.modules.system.mapper;

import com.jfhealthcare.modules.system.entity.SysOrgGroupRel;
import com.jfhealthcare.tk.mybatis.util.MyMapper;

import java.util.List;
import java.util.Map;

public interface SysOrgGroupRelMapper extends MyMapper<SysOrgGroupRel> {

	List<String> getHospitalCode(String loginCode);

	List<Map> getHospitalInfoByGroupId(Integer groupId);

	List<Map> getHospitalInfoByClassifyId(Integer classifyId);
}