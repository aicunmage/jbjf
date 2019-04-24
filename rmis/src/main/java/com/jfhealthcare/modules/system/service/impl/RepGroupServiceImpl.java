package com.jfhealthcare.modules.system.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jfhealthcare.common.validator.Assert;
import com.jfhealthcare.modules.system.entity.RepGroup;
import com.jfhealthcare.modules.system.entity.SysOperOrgRel;
import com.jfhealthcare.modules.system.entity.SysRepGroup;
import com.jfhealthcare.modules.system.mapper.RepGroupMapper;
import com.jfhealthcare.modules.system.mapper.SysOperOrgRelMapper;
import com.jfhealthcare.modules.system.mapper.SysOperatorMapper;
import com.jfhealthcare.modules.system.mapper.SysRepGroupMapper;
import com.jfhealthcare.modules.system.request.RepGroupRequest;
import com.jfhealthcare.modules.system.request.SysRepGroupRequest;
import com.jfhealthcare.modules.system.response.RepGroupResponse;
import com.jfhealthcare.modules.system.response.SysRepGroupResponse;
import com.jfhealthcare.modules.system.service.RepGroupService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

import java.util.*;

@Slf4j
@Service
public class RepGroupServiceImpl implements RepGroupService {
    @Autowired
    RepGroupMapper repGroupMapper;
    @Autowired
    SysRepGroupMapper sysRepGroupMapper;
    @Autowired
    SysOperOrgRelMapper sysOperOrgRelMapper;
    @Autowired
    SysOperatorMapper sysOperatorMapper;

    @Override
    public PageInfo <RepGroupResponse> queryRepGroup(RepGroupRequest repGroupRequest) {
        List <RepGroupResponse> returnList = new ArrayList <RepGroupResponse>();
        Example example = new Example(RepGroup.class);
        Criteria createCriteria = example.createCriteria();
        if (StringUtils.isNotBlank(repGroupRequest.getName())) {
            createCriteria.andLike("name", "%" + repGroupRequest.getName() + "%");
        }
        if (StringUtils.isNotBlank(repGroupRequest.getNamepy())) {
            createCriteria.andCondition("(namepy like \"%" + repGroupRequest.getNamepy() + "%\"" + "or namewb like \"%"
                    + repGroupRequest.getNamepy() + "%\")");
        }
        //普通管理员或超级管理员选定影像中心用
        if (StringUtils.isNotBlank(repGroupRequest.getImageCenterId())) {
            createCriteria.andEqualTo("imageCenterId", repGroupRequest.getImageCenterId());
        }

        example.setOrderByClause("status asc,CAST(nindex AS DECIMAL) asc");
        PageHelper.startPage(repGroupRequest.getPageNum(), repGroupRequest.getPageSize());
        List <RepGroup> selectByExample = repGroupMapper.selectByExample(example);
        PageInfo <RepGroup> tempPageInfo = new PageInfo <RepGroup>(selectByExample);
        //获取机构分组信息
        for (RepGroup repGroup : selectByExample) {
            RepGroupResponse response = doQueryGroupRel(repGroup.getId(), 1);
            returnList.add(response);
        }

        PageInfo <RepGroupResponse> pageInfo = new PageInfo <RepGroupResponse>(returnList);
        pageInfo.setTotal(tempPageInfo.getTotal());
        return pageInfo;
    }

    @Override
    public List <RepGroup> queryRepGroupAll() {
        Example example = new Example(RepGroup.class);
        example.setOrderByClause("status asc,CAST(nindex AS DECIMAL) asc");
        List <RepGroup> selectAll = repGroupMapper.selectByExample(example);
        return selectAll;
    }

    @Override
    public String queryMaxNindex() {
        Example example = new Example(RepGroup.class);
        example.setOrderByClause("CAST(NINDEX AS DECIMAL) desc");
        List <RepGroup> selectAll = repGroupMapper.selectByExample(example);
        return CollectionUtils.isEmpty(selectAll) ? "0" : String.valueOf(Integer.valueOf(selectAll.get(0).getNindex()) + 1);
    }

    @Override
    public List <SysRepGroupResponse> queryLoginCodeByGroupId(String id) {
        SysRepGroup sysRepGroup = new SysRepGroup();
        sysRepGroup.setGroupId(id);
        List <SysRepGroupResponse> srgs = sysRepGroupMapper.selectByGroupId(sysRepGroup);
        return srgs;
    }

    @Override
    @Transactional
    public void delectRepGroup(String groupId) {
        Example example = new Example(SysRepGroup.class);
        example.createCriteria().andEqualTo("groupId", groupId);
        sysRepGroupMapper.deleteByExample(example);
        repGroupMapper.deleteByPrimaryKey(groupId);

        //将关联表置为失效状态
        doDeleteOperOrgRel(groupId);
        Example ex = new Example(SysOperOrgRel.class);
        ex.createCriteria().andEqualTo("operGroupId", groupId);
        ex.createCriteria().andEqualTo("isValid", 1);
        SysOperOrgRel record = new SysOperOrgRel();
        record.setIsValid(0);
        record.setExpireDate(new Date());
        sysOperOrgRelMapper.updateByExampleSelective(record, ex);
    }

    @Override
    public void saveRepGroup(RepGroupRequest reGroupReq) {
        RepGroup re = new RepGroup();
        BeanUtils.copyProperties(reGroupReq, re);
        Example ex = new Example(RepGroup.class);
        ex.createCriteria().andEqualTo("name", re.getName()).andEqualTo("status", re.getStatus()).andEqualTo("imageCenterId", re.getImageCenterId());
        List <RepGroup> repGroups = repGroupMapper.selectByExample(ex);
        Assert.isNotNull(repGroups, "分组名已存在，请重新输入！");
        repGroupMapper.insertSelective(re);

        //根据名字把数据查出来，需要用ID
        RepGroup repGroup = repGroupMapper.selectByExample(ex).get(0);
        //增加用户分组和机构分组的关联
        doInsertOperOrgRel(repGroup.getId(), reGroupReq.getOrgGroupInfo());
    }

    @Override
    public void updateRepGroup(RepGroupRequest reGroupReq) {
        RepGroup re = new RepGroup();
        BeanUtils.copyProperties(reGroupReq, re);
        Example ex = new Example(RepGroup.class);
        ex.createCriteria().andEqualTo("name", re.getName()).andEqualTo("status", re.getStatus()).andNotEqualTo("id", re.getId()).andEqualTo("imageCenterId", re.getImageCenterId());
        List <RepGroup> repGroups = repGroupMapper.selectByExample(ex);
        Assert.isNotNull(repGroups, "分组名已存在，请重新输入！");
        repGroupMapper.updateByPrimaryKey(re);

        //先删除分组关联信息
        doDeleteOperOrgRel(reGroupReq.getId());
        //增加用户分组和机构分组的关联
        doInsertOperOrgRel(reGroupReq.getId(), reGroupReq.getOrgGroupInfo());
    }

    @Override
    @Transactional
    public void updateSysRepGroup(SysRepGroupRequest sysRepGroupRequest) {

        String logincodes = sysRepGroupRequest.getLogincodes();
        String[] lcs = StringUtils.split(logincodes, ",");
        List <String> asList = new ArrayList <String>(Arrays.asList(lcs));

        SysRepGroup srg = new SysRepGroup();
        srg.setGroupId(sysRepGroupRequest.getGroupId());
        List <SysRepGroup> select = sysRepGroupMapper.select(srg);
        List <String> orginRepGroup = new ArrayList <String>();
        for (SysRepGroup sysRepGroup : select) {
            orginRepGroup.add(sysRepGroup.getLogincode());
        }
        asList.removeAll(orginRepGroup);
        for (String logincode : asList) {
            SysRepGroup sysRepGroup = new SysRepGroup();
            sysRepGroup.setGroupId(sysRepGroupRequest.getGroupId());
            sysRepGroup.setLogincode(logincode);
            sysRepGroupMapper.insertSelective(sysRepGroup);
        }
    }

    @Override
    public RepGroupResponse queryRepGroupByGroupId(String groupId) {
        RepGroup repGroup = repGroupMapper.selectByPrimaryKey(groupId);
        //获取分组关系
        return doQueryGroupRel(groupId, 0);
    }

    @Override
    public void deleteRepGroup(String groupId, String logincodes) {
        String[] lcs = StringUtils.split(logincodes, ",");
        for (String logincode : lcs) {
            SysRepGroup sysRepGroup = new SysRepGroup();
            sysRepGroup.setGroupId(groupId);
            sysRepGroup.setLogincode(logincode);
            sysRepGroupMapper.delete(sysRepGroup);
        }
    }

    /**
     * 新增用户分组和机构分组的关联
     *
     * @param reGroupReq
     */
    private void doInsertOperOrgRel(String operGroupId, List <String> orgGroupIdList) {
        for (String orgGroupId : orgGroupIdList) {
            SysOperOrgRel record = new SysOperOrgRel();
            record.setOperGroupId(operGroupId);
            record.setOrgGroupId(Integer.parseInt(orgGroupId));
            record.setIsValid(1);
            sysOperOrgRelMapper.insertSelective(record);
        }
    }

    /**
     * 删除用户分组和机构分组的关联，逻辑删除
     *
     * @param reGroupReq
     */
    private void doDeleteOperOrgRel(String groupId) {
        Example ex = new Example(SysOperOrgRel.class);
        ex.createCriteria().andEqualTo("operGroupId", groupId);
        ex.createCriteria().andEqualTo("isValid", 1);
        SysOperOrgRel record = new SysOperOrgRel();
        record.setIsValid(0);
        record.setExpireDate(new Date());
        sysOperOrgRelMapper.updateByExampleSelective(record, ex);
    }

    /**
     * 根据用户分组ID查询机构分组ID
     *
     * @param groupId
     * @param retFlag 0--返回ID，1--返回名称
     * @return
     */
    private RepGroupResponse doQueryGroupRel(String groupId, int retFlag) {
        RepGroup repGroup = repGroupMapper.selectByPrimaryKey(groupId);
        //获取分组关系
        RepGroupResponse response = new RepGroupResponse();
        List <Map> groupInfoList = sysOperOrgRelMapper.queryOrgGroupInfo(repGroup.getId());
        BeanUtils.copyProperties(repGroup, response);
        //前端只需要id，去掉groupName
        List <Object> groupIdList = new ArrayList <Object>();
        for (Map map : groupInfoList) {
            log.info("返回分组信息为：" + map);
            if (retFlag == 0) {
                groupIdList.add(Integer.parseInt(String.valueOf(map.get("group_id"))));
            } else {
                groupIdList.add(String.valueOf(map.get("group_name")));
            }
        }
        response.setOrgGroupInfo(groupIdList);
        return response;
    }
}
