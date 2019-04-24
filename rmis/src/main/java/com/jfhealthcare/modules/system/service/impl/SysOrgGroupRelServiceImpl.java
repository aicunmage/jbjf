package com.jfhealthcare.modules.system.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jfhealthcare.modules.system.entity.SysOrgGroupRel;
import com.jfhealthcare.modules.system.mapper.SysOrgGroupRelMapper;
import com.jfhealthcare.modules.system.service.SysOrgGroupRelService;

import tk.mybatis.mapper.entity.Example;

@Service
public class SysOrgGroupRelServiceImpl implements SysOrgGroupRelService {

    @Autowired
    private SysOrgGroupRelMapper mapper;

    @Override
    public List <SysOrgGroupRel> queryByHospId(String hospitalId) {

        Example example = new Example(SysOrgGroupRel.class);
        example.createCriteria().andEqualTo("hospitalId", hospitalId);
        example.createCriteria().andEqualTo("isValid", 1);
        return mapper.selectByExample(example);
    }

    @Override
    public int save(SysOrgGroupRel sysOrgGroupRel) {
        return mapper.insert(sysOrgGroupRel);
    }

    @Override
    public int updateByPrimary(SysOrgGroupRel sysOrgGroupRel) {
        return mapper.updateByPrimaryKey(sysOrgGroupRel);
    }

    @Override
    public int deleteGroupRel(SysOrgGroupRel sysOrgGroupRel) {
        Example example = new Example(SysOrgGroupRel.class);
        example.createCriteria().andEqualTo("hospitalId", sysOrgGroupRel.getHospitalId());
        example.createCriteria().andEqualTo("isValid", 1);
        return mapper.updateByExampleSelective(sysOrgGroupRel, example);
    }

    @Override
    public List <Map> getHospitalInfoByGroupId(Integer groupId) {
        return mapper.getHospitalInfoByGroupId(groupId);
    }

    @Override
    public List<Map> getHospitalInfoByClassifyId(Integer classifyId) {
        return mapper.getHospitalInfoByClassifyId(classifyId);
    }
}
