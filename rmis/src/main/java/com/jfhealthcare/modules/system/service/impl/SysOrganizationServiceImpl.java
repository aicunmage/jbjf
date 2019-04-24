package com.jfhealthcare.modules.system.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.jfhealthcare.modules.system.entity.*;
import com.jfhealthcare.modules.system.mapper.*;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jfhealthcare.common.utils.NameUtils;
import com.jfhealthcare.common.utils.TransferUtils;
import com.jfhealthcare.common.validator.Assert;
import com.jfhealthcare.modules.system.request.SysOrganizationRequest;
import com.jfhealthcare.modules.system.response.OrganizationResponse;
import com.jfhealthcare.modules.system.service.SysOrganizationService;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

@Service
public class SysOrganizationServiceImpl implements SysOrganizationService {

	@Autowired
	private SysOrganizationMapper sysOrganizationMapper;
	
	@Autowired
	private SysDepartmentMapper sysDepartmentMapper;
	
	@Autowired
	private SysOperatorDtlMapper sysOperatorDtlMapper;
	
	@Autowired
	private SysDictCityMapper sysDictCityMapper;

	@Autowired
	private SysOrgClassifyMapper sysOrgClassifyMapper;
	
	@Override
	public PageInfo<OrganizationResponse> queryByCondition(SysOrganizationRequest sysOrganizationRequest) {
		Page<Object> startPage = PageHelper.startPage(sysOrganizationRequest.getPageNum(), sysOrganizationRequest.getPageSize());
		String clsId = sysOrganizationRequest.getClsId();
		if ("all".equals(clsId)) {
			sysOrganizationRequest.setClsId(null);
		}
		List<OrganizationResponse> selectByExample = sysOrganizationMapper.queryOrganizationListByParameter(sysOrganizationRequest);
		List<OrganizationResponse> recordList = selectByExample;
		if ("all".equals(clsId)) {
		} else if (StringUtils.isBlank(sysOrganizationRequest.getClsId()) && selectByExample != null) {
			// 查询非影像中心的机构,将影像中心数据删除
			for (int i = 0; i < selectByExample.size(); i++) {
				OrganizationResponse record = selectByExample.get(i);
				if (StringUtils.isNotBlank(record.getClsId()) && "0".equals(record.getClsId())) {
					recordList.remove(i);
					i --;
				}
			}
		}
		PageInfo<OrganizationResponse> pageInfo = new PageInfo<OrganizationResponse>(recordList);
		return pageInfo;
	}
	
	@Override
	public List<OrganizationResponse> queryAllSysOrganization() {
		List<OrganizationResponse> selectAll = sysOrganizationMapper.queryOrganizationList();
		return selectAll;
	}
	
	@Override
	public OrganizationResponse querySingleSysOrganization(String id) {
		SysOrganizationRequest sysOrganizationRequest = new SysOrganizationRequest();
		sysOrganizationRequest.setId(id);
		List<OrganizationResponse> queryOrganizationListByParameter = sysOrganizationMapper
				.queryOrganizationListByParameter(sysOrganizationRequest);
		if (queryOrganizationListByParameter != null && queryOrganizationListByParameter.size() > 0) {
			return queryOrganizationListByParameter.get(0);
		}
		return null;
	}

	@Override
	public String insertSysOrganization(SysOrganizationRequest sysOrganizationRequest) {
		SysOrganization sysOrganization=new SysOrganization();
		TransferUtils.copyPropertiesIgnoreNull(sysOrganizationRequest, sysOrganization);
		sysOrganization.setStatus(true);
		sysOrganization.setCrtTime(new Date());
		sysOrganization.setCrtUser(NameUtils.getLoginCode());
		sysOrganization.setIsdelete(false);
		//给省份编号赋值
		Example example = new Example(SysDictCity.class);
		example.createCriteria().andEqualTo("id",sysOrganizationRequest.getCityNo());
		List<SysDictCity> selectByExample = sysDictCityMapper.selectByExample(example);
		if(selectByExample!=null&&selectByExample.size()>0){
			sysOrganizationRequest.setProvinceNo(selectByExample.get(0).getUppcode());
		}
		sysOrganizationMapper.insertSelective(sysOrganization);
		//sysOrganizationMapper.insertByRecord(sysOrganization);
		return sysOrganization.getId();
	}

	@Override
	public void updateSysOrganization(SysOrganizationRequest sysOrganizationRequest) {
		SysOrganization sysOrganization=new SysOrganization();
		TransferUtils.copyPropertiesIgnoreNull(sysOrganizationRequest, sysOrganization);
		sysOrganization.setUpdUser(NameUtils.getLoginCode());
		sysOrganization.setUpdTime(new Date());
		sysOrganizationMapper.updateByPrimaryKey(sysOrganization);
	}

	@Override
	@Transactional
	public void deleteSysOrganization(String id) {
		SysDepartment sysDepartment=new SysDepartment();
		sysDepartment.setOrgId(id);
		List<SysDepartment> sysDepartments = sysDepartmentMapper.select(sysDepartment);
		for (SysDepartment sd : sysDepartments) {
			SysOperatorDtl sysOperatorDtl=new SysOperatorDtl();
			sysOperatorDtl.setDepId(sd.getId());
			List<SysOperatorDtl> sysOperatorDtls = sysOperatorDtlMapper.select(sysOperatorDtl);
			if(!CollectionUtils.isEmpty(sysOperatorDtls)){
				for (SysOperatorDtl sdl : sysOperatorDtls) {
					sdl.setDepId(null);
					sysOperatorDtlMapper.updateByPrimaryKey(sdl);
				}
			}
			sysDepartmentMapper.deleteByPrimaryKey(sd.getId());
		}
		SysOrganization sysOrganization = sysOrganizationMapper.selectByPrimaryKey(id);
		Assert.isNull(sysOrganization, "没有对应机构，无法删除！");
		sysOrganization.setIsdelete(true);
		sysOrganizationMapper.updateByPrimaryKey(sysOrganization);
	}

	@Override
	public List<SysOrganization> queryBySysOrg(SysOrganization sysorg) {
		return sysOrganizationMapper.select(sysorg);
	}

	@Override
	public List<SysOrgClassify> getClassifyByOrgCode(String code) {
		SysOrgClassify sysOrgClassify = new SysOrgClassify();
		sysOrgClassify.setOrgCode(code);
		return sysOrgClassifyMapper.select(sysOrgClassify);
	}
}
