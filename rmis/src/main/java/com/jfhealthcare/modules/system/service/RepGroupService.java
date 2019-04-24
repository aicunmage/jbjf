package com.jfhealthcare.modules.system.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.jfhealthcare.modules.system.entity.RepGroup;
import com.jfhealthcare.modules.system.request.RepGroupRequest;
import com.jfhealthcare.modules.system.request.SysRepGroupRequest;
import com.jfhealthcare.modules.system.response.RepGroupResponse;
import com.jfhealthcare.modules.system.response.SysRepGroupResponse;

public interface RepGroupService {

	public PageInfo<RepGroupResponse> queryRepGroup(RepGroupRequest repGroupRequest) ;

	public List<SysRepGroupResponse> queryLoginCodeByGroupId(String id);

	public void delectRepGroup(String groupId);

	public void saveRepGroup(RepGroupRequest re);

	public void updateRepGroup(RepGroupRequest reGroupReq);

	public void updateSysRepGroup(SysRepGroupRequest sysRepGroupRequest);

	public RepGroupResponse queryRepGroupByGroupId(String groupId);

	public void deleteRepGroup(String groupId,String logincodes);

	public List<RepGroup> queryRepGroupAll();

	public String queryMaxNindex();


}
