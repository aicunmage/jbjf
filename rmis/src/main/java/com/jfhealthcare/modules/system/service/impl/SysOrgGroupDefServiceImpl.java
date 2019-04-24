package com.jfhealthcare.modules.system.service.impl;

import com.jfhealthcare.common.validator.Assert;
import com.jfhealthcare.modules.system.entity.SysOrgGroupDef;
import com.jfhealthcare.modules.system.mapper.SysOrgGroupDefMapper;
import com.jfhealthcare.modules.system.service.SysOrgGroupDefService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

import java.util.List;

@Service
public class SysOrgGroupDefServiceImpl implements SysOrgGroupDefService {

	@Autowired
	private SysOrgGroupDefMapper sysOrgGroupDefMapper;

	@Override
	public List<SysOrgGroupDef> queryOrgGroupAll() {
		Example example = new Example(SysOrgGroupDef.class);
		example.setOrderByClause("CAST(extend1 AS DECIMAL) asc, group_id asc");
		return sysOrgGroupDefMapper.selectByExample(example);
	}

	@Override
	public SysOrgGroupDef queryByPrimary(int groupId) {
		return sysOrgGroupDefMapper.selectByPrimaryKey(groupId);
	}

	@Override
	public void save(SysOrgGroupDef sysOrgGroupDef) {
		Example ex = new Example(SysOrgGroupDef.class);
		ex.createCriteria().andEqualTo("groupName", sysOrgGroupDef.getGroupName());// 同一个组名只能添加一次
		List<SysOrgGroupDef> list = sysOrgGroupDefMapper.selectByExample(ex);
		Assert.isNotNull(list, "分组名已存在，请重新输入！");
		sysOrgGroupDefMapper.insert(sysOrgGroupDef);
	}

	@Override
	public int updateByPri(SysOrgGroupDef sysOrgGroupDef) {
		// 同一个组名只能添加一次
		Example ex = new Example(SysOrgGroupDef.class);
		ex.createCriteria().andEqualTo("groupName", sysOrgGroupDef.getGroupName()).andNotEqualTo("groupId", sysOrgGroupDef.getGroupId());
		List<SysOrgGroupDef> list = sysOrgGroupDefMapper.selectByExample(ex);
		Assert.isNotNull(list, "分组名已存在，请重新输入！");
		return sysOrgGroupDefMapper.updateByPrimaryKey(sysOrgGroupDef);
	}

	@Override
	public int deleteByPri(int groupId) {
		return sysOrgGroupDefMapper.deleteByPrimaryKey(groupId);
	}

	@Override
	public List<SysOrgGroupDef> queryByCond(SysOrgGroupDef sysOrgGroupDef) {
		Example example = new Example(SysOrgGroupDef.class);
		Criteria createCriteria = example.createCriteria();
		if (StringUtils.isNotBlank(sysOrgGroupDef.getExtend2())) {
			createCriteria.andCondition("(extend2 like \"%" + sysOrgGroupDef.getExtend2() + "%\"" + "or extend3 like \"%" + sysOrgGroupDef.getExtend2() + "%\")");
		}
		if(StringUtils.isNotBlank(sysOrgGroupDef.getGroupName())){
			createCriteria.andCondition("group_name like \"%" + sysOrgGroupDef.getGroupName() + "%\"");
		}
		example.setOrderByClause("CAST(extend1 AS DECIMAL) asc, group_id asc");
		return sysOrgGroupDefMapper.selectByExample(example);
	}

	@Override
	public String queryMaxIndex() {
		Example example = new Example(SysOrgGroupDef.class);
		example.setOrderByClause("CAST(EXTEND1 AS DECIMAL) desc");
		List<SysOrgGroupDef> selectAll = sysOrgGroupDefMapper.selectByExample(example);
		return CollectionUtils.isEmpty(selectAll)?"0":String.valueOf(Integer.valueOf(selectAll.get(0).getExtend1())+1);
	}
}
