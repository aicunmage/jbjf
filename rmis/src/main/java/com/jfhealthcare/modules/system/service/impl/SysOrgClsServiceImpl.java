package com.jfhealthcare.modules.system.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jfhealthcare.modules.system.entity.SysOrgCls;
import com.jfhealthcare.modules.system.mapper.SysOrgClsMapper;
import com.jfhealthcare.modules.system.service.SysOrgClsService;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;


@Service
public class SysOrgClsServiceImpl implements SysOrgClsService {

	@Autowired
	private SysOrgClsMapper sysOrgClsMapper;
	
	
	@Override
	public List<SysOrgCls> querySysOrgClsAll() {
		
		//机构分类不再需要包含影像中心
		Example ex = new Example(SysOrgCls.class);
		Criteria cr = ex.createCriteria();
		cr.andNotEqualTo("id", "0");
		ex.setOrderByClause("id asc");
		//List<SysOrgCls> sysOrgClss = sysOrgClsMapper.selectAll();
		return sysOrgClsMapper.selectByExample(ex);
	}
}
