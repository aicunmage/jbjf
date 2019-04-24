package com.jfhealthcare.modules.system.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jfhealthcare.common.utils.NameUtils;
import com.jfhealthcare.common.validator.Assert;
import com.jfhealthcare.modules.system.entity.SysOrgClassify;
import com.jfhealthcare.modules.system.entity.SysOrganization;
import com.jfhealthcare.modules.system.mapper.SysOrgClassifyMapper;
import com.jfhealthcare.modules.system.mapper.SysOrganizationMapper;
import com.jfhealthcare.modules.system.service.SysOrgClassifyService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created By lzw
 * CraeteDate 10:03 2018/9/14
 */
@Service
public class SysOrgClassifyServiceImpl implements SysOrgClassifyService {

    @Autowired
    private SysOrgClassifyMapper sysOrgClassifyMapper;
    @Autowired
    private SysOrganizationMapper sysOrganizationMapper;

    @Override
    public void save(SysOrgClassify sysOrgClassify) {
        Example ex = new Example(SysOrgClassify.class);
        ex.createCriteria().andEqualTo("name", sysOrgClassify.getName()).andEqualTo("orgCode",sysOrgClassify.getOrgCode());// 同一个类名只能添加一次
        List<SysOrgClassify> list = sysOrgClassifyMapper.selectByExample(ex);
        Assert.isNotNull(list, "分类名已存在，请重新输入！");
        SysOrganization organization = new SysOrganization();
        organization.setCode(sysOrgClassify.getOrgCode());
        sysOrgClassify.setOrgName(sysOrganizationMapper.selectOne(organization).getName());
        sysOrgClassify.setCrtUser(NameUtils.getLoginCode());
        sysOrgClassify.setCrtTime(new Date());
        sysOrgClassifyMapper.insert(sysOrgClassify);
    }

    @Override
    public Integer updateByPri(SysOrgClassify sysOrgClassify) {
        // 同一个类名只能添加一次
        Example ex = new Example(SysOrgClassify.class);
        ex.createCriteria().andEqualTo("name", sysOrgClassify.getName()).andEqualTo("orgCode",sysOrgClassify.getOrgCode()).andNotEqualTo("id", sysOrgClassify.getId());
        List<SysOrgClassify> list = sysOrgClassifyMapper.selectByExample(ex);
        Assert.isNotNull(list, "分类名已存在，请重新输入！");
        SysOrgClassify sysClassify = sysOrgClassifyMapper.selectByPrimaryKey(sysOrgClassify.getId());
        if(!sysOrgClassify.getOrgCode().equals(sysClassify.getOrgCode())){//有修改影像中心
            SysOrganization organization = new SysOrganization();
            organization.setCode(sysOrgClassify.getOrgCode());
            sysOrgClassify.setOrgName(sysOrganizationMapper.selectOne(organization).getName());
        }
        sysOrgClassify.setUpdUser(NameUtils.getLoginCode());
        sysOrgClassify.setUpdTime(new Date());
        return sysOrgClassifyMapper.updateByPrimaryKey(sysOrgClassify);
    }

    @Override
    public Integer deleteByPri(Integer classifyId) {
        return sysOrgClassifyMapper.deleteByPrimaryKey(classifyId);
    }

    @Override
    public SysOrgClassify queryByPrimary(Integer classifyId) {
        return sysOrgClassifyMapper.selectByPrimaryKey(classifyId);
    }

    @Override
    public PageInfo<Map> queryByCond(SysOrgClassify sysOrgClassify) {
        PageHelper.startPage(sysOrgClassify.getPageNum(),sysOrgClassify.getPageSize());
        Map resMap = new HashMap();
        resMap.put("namepy",sysOrgClassify.getNamepy());
        resMap.put("orgCode",sysOrgClassify.getOrgCode());
        List<Map> classifyList = sysOrgClassifyMapper.queryClassifyByCond(resMap);
        PageInfo<Map> info = new PageInfo<>(classifyList);
        return info;
    }

    @Override
    public String queryMaxIndex() {
        Example example = new Example(SysOrgClassify.class);
        example.setOrderByClause("nindex desc");
        List<SysOrgClassify> selectAll = sysOrgClassifyMapper.selectByExample(example);
        return CollectionUtils.isEmpty(selectAll)?"0":String.valueOf(Integer.valueOf(selectAll.get(0).getNindex())+1);
    }
}
