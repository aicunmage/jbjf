package com.jfhealthcare.modules.system.service;

import com.github.pagehelper.PageInfo;
import com.jfhealthcare.modules.system.entity.SysOrgClassify;
import java.util.List;
import java.util.Map;

/**
 * Created By lzw
 * CraeteDate 10:00 2018/9/14
 */
public interface SysOrgClassifyService {

    public void save(SysOrgClassify sysOrgClassify);

    public Integer updateByPri(SysOrgClassify sysOrgClassify);

    public Integer deleteByPri(Integer classifyId);

    public SysOrgClassify queryByPrimary(Integer classifyId);

    public PageInfo<Map> queryByCond(SysOrgClassify sysOrgClassify);

    public String queryMaxIndex();
}
