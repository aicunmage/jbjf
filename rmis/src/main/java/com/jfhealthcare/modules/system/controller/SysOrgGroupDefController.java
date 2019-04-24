package com.jfhealthcare.modules.system.controller;

import cn.hutool.core.bean.BeanUtil;
import com.jfhealthcare.common.base.BaseResponse;
import com.jfhealthcare.modules.system.entity.SysOrgGroupDef;
import com.jfhealthcare.modules.system.service.SysOrgGroupDefService;
import com.jfhealthcare.modules.system.service.SysOrgGroupRelService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 机构分组
 *
 * @author zhengtw
 */
@Slf4j
@Api(value = "b组织->机构分组管理api")
@RestController
@RequestMapping("/v2/rmis/sysop/sysorggroup")
public class SysOrgGroupDefController {

    @Autowired
    private SysOrgGroupDefService sysOrgGroupDefService;
    @Autowired
    private SysOrgGroupRelService sysOrgGroupRelService;

    @ApiOperation(value = "机构分组查询", notes = "机构分组查询说明")
    @RequestMapping(path = "/queryAll", method = RequestMethod.GET)
    public BaseResponse queryOrgGroupAll() {
        List <SysOrgGroupDef> list = sysOrgGroupDefService.queryOrgGroupAll();
        log.info("获取到的分组信息为：" + list);
        return BaseResponse.getSuccessResponse(list);
    }

    @ApiOperation(value = "根据主键id机构分组查询", notes = "根据主键id机构分组查询说明")
    @RequestMapping(path = "/{groupId}", method = RequestMethod.GET)
    public BaseResponse queryByPrimary(@PathVariable int groupId) {
        try {
            log.info("传入的分组id为：" + groupId);
            SysOrgGroupDef sysOrgGroupDef = sysOrgGroupDefService.queryByPrimary(groupId);
            log.info("获取到的分组信息为：" + sysOrgGroupDef);
            List <Map> orgInfo = sysOrgGroupRelService.getHospitalInfoByGroupId(groupId);
            Map returnInfo = BeanUtil.beanToMap(sysOrgGroupDef, false, false);
            returnInfo.put("orgInfo", orgInfo);
            return BaseResponse.getSuccessResponse(returnInfo);
        } catch (Exception e) {
            log.error("获取分组信息失败！", e);
            return BaseResponse.getFailResponse(e.getMessage());
        }
    }

    @ApiOperation(value = "机构分组新增", notes = "机构分组新增说明")
    @RequestMapping(path = "/save", method = RequestMethod.PUT)
    public BaseResponse saveOrgGroup(@RequestBody SysOrgGroupDef sysOrgGroupDef) {
        log.info("请求增加的分组信息名称为：" + sysOrgGroupDef.getGroupName());
        log.info("请求增加的分组信息属性为：" + sysOrgGroupDef.getGroupPro());
        log.info("请求增加的分组信息排序为：" + sysOrgGroupDef.getExtend1());
        log.info("请求增加的分组信息拼音简码为：" + sysOrgGroupDef.getExtend2());
        log.info("请求增加的分组信息五笔简码为：" + sysOrgGroupDef.getExtend3());
        try {
            sysOrgGroupDefService.save(sysOrgGroupDef);
            return BaseResponse.getSuccessResponse();
        } catch (Exception e) {
            log.error("增加机构分组信息失败！", e);
            return BaseResponse.getFailResponse(e.getMessage());
        }
    }

    @ApiOperation(value = "机构分组修改", notes = "机构分组修改说明")
    @RequestMapping(path = "/update", method = RequestMethod.PUT)
    public BaseResponse updateByPri(@RequestBody SysOrgGroupDef sysOrgGroupDef) {
        log.info("请求修改的分组信息名称为：" + sysOrgGroupDef.getGroupName());
        log.info("请求修改的分组信息属性为：" + sysOrgGroupDef.getGroupPro());
        log.info("请求修改的分组信息排序为：" + sysOrgGroupDef.getExtend1());
        log.info("请求修改的分组信息拼音简码为：" + sysOrgGroupDef.getExtend2());
        log.info("请求修改的分组信息五笔简码为：" + sysOrgGroupDef.getExtend3());
        try {
            int num = sysOrgGroupDefService.updateByPri(sysOrgGroupDef);
            if (num <= 0) {
                throw new Exception("0条数据被修改，请确认修改结果！");
            }
            return BaseResponse.getSuccessResponse();
        } catch (Exception e) {
            log.error("修改机构分组信息失败！", e);
            return BaseResponse.getFailResponse(e.getMessage());
        }
    }

    @ApiOperation(value = "机构分组删除", notes = "机构分组删除说明")
    @RequestMapping(path = "/{groupId}", method = RequestMethod.DELETE)
    public BaseResponse updateByPri(@PathVariable int groupId) {
        log.info("请求删除的分组信息groupId为：" + groupId);
        try {
            int num = sysOrgGroupDefService.deleteByPri(groupId);
            if (num <= 0) {
                throw new Exception("0条数据被删除，请确认删除结果！");
            }
            return BaseResponse.getSuccessResponse();
        } catch (Exception e) {
            log.error("删除机构分组信息失败！", e);
            return BaseResponse.getFailResponse("删除机构分组信息失败！" + e.getMessage());
        }
    }

    @ApiOperation(value = "根据条件查询", notes = "根据条件查询")
    @RequestMapping(path = "/queryByCond", method = RequestMethod.POST)
    public BaseResponse queryByCond(@RequestBody SysOrgGroupDef sysOrgGroupDef) {
        log.info("请求查询条件为：" + sysOrgGroupDef.getExtend2());
        List <SysOrgGroupDef> list = sysOrgGroupDefService.queryByCond(sysOrgGroupDef);
        log.info("获取到的分组信息为：" + list);
        return BaseResponse.getSuccessResponse(list);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/nindex")
    @ApiOperation(value = "查询最大nindex", notes = "查询最大nindex详情")
    public BaseResponse queryMaxNindex() {
        try {
            String nindex = sysOrgGroupDefService.queryMaxIndex();
            return BaseResponse.getSuccessResponse((Object) nindex);
        } catch (Exception e) {
            log.error("查询最大nindex失败!", e);
            return BaseResponse.getFailResponse("查询最大nindex失败!");
        }
    }
}
