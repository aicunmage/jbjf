package com.jfhealthcare.modules.system.mapper;

import com.jfhealthcare.modules.system.entity.SysOrgClassify;
import com.jfhealthcare.tk.mybatis.util.MyMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface SysOrgClassifyMapper extends MyMapper<SysOrgClassify> {

    public List<Map> queryClassifyByCond(Map resMap);
}