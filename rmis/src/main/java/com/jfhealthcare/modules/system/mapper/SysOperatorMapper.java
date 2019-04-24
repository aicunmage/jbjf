package com.jfhealthcare.modules.system.mapper;

import com.jfhealthcare.modules.system.entity.SysOperator;
import com.jfhealthcare.modules.system.request.SysOperatorQueryRequest;
import com.jfhealthcare.modules.system.response.OperatorResponse;
import com.jfhealthcare.tk.mybatis.util.MyMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface SysOperatorMapper extends MyMapper<SysOperator> {
	List<OperatorResponse> queryOperatorList(); 
	List<OperatorResponse> queryOperatorListByParameter(SysOperatorQueryRequest sysOqr);
	List<OperatorResponse> queryOperatorAllInfoByLogincode(Map<String, Object> pmap); 
	List<String> queryAdminListByOrgId(String orgId);
	List<Map> queryLoginInfoById(@Param("idList") List idList);
}