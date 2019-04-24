package com.jfhealthcare.modules.system.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jfhealthcare.modules.system.mapper.SysOperOrgRelMapper;
import com.jfhealthcare.modules.system.service.SysOperOrgRelService;

@Service

public class SysOperOrgRelServiceImpl implements SysOperOrgRelService {
	@Autowired
	private SysOperOrgRelMapper sysOperOrgRelMapper;

	@Override
	public List<Map> queryByOperId(String operGroupId) {
		return sysOperOrgRelMapper.queryOrgGroupInfo(operGroupId);
	}

}
