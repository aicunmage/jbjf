package com.jfhealthcare.modules.system.controller;

import cn.hutool.core.bean.BeanUtil;
import com.github.pagehelper.PageInfo;
import com.jfhealthcare.common.base.BaseResponse;
import com.jfhealthcare.modules.system.entity.SysOrgClassify;
import com.jfhealthcare.modules.system.service.SysOrgClassifyService;
import com.jfhealthcare.modules.system.service.SysOrgGroupRelService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

/**
 * Created By lzw
 * CraeteDate 9:56 2018/9/14
 */
@Slf4j
@Api(value = "b组织->机构分类管理api")
@RestController
@RequestMapping("/v2/rmis/sysop/sysorgclassify")
public class SysOrgClassifyController {

    @Autowired
    private SysOrgClassifyService sysOrgClassifyService;
    @Autowired
    private SysOrgGroupRelService sysOrgGroupRelService;

    @ApiOperation(value = "机构分类查询", notes = "机构分类查询说明")
    @RequestMapping(path = "/queryAll", method = RequestMethod.POST)
    public BaseResponse queryOrgClassifyAll(@RequestBody SysOrgClassify sysOrgClassify){
        PageInfo<Map> list = sysOrgClassifyService.queryByCond(sysOrgClassify);
        log.info("获取到的分类信息为：" + list);
        return BaseResponse.getSuccessResponse(list);
    }

    @ApiOperation(value = "根据主键id机构类组查询", notes = "根据主键id机构分类查询说明")
    @RequestMapping(path = "/{classifyId}", method = RequestMethod.GET)
    public BaseResponse queryByPrimary(@PathVariable int classifyId) {
        try {
            log.info("传入的分类id为：" + classifyId);
            SysOrgClassify SysOrgClassify = sysOrgClassifyService.queryByPrimary(classifyId);
            log.info("获取到的分类信息为：" + SysOrgClassify.toString());
            List <Map> orgInfo = sysOrgGroupRelService.getHospitalInfoByClassifyId(classifyId);
            Map returnInfo = BeanUtil.beanToMap(SysOrgClassify, false, false);
            returnInfo.put("orgInfo", orgInfo);
            return BaseResponse.getSuccessResponse(returnInfo);
        } catch (Exception e) {
            log.error("获取分类信息失败！", e);
            return BaseResponse.getFailResponse(e.getMessage());
        }
    }

    @ApiOperation(value = "机构分类新增", notes = "机构分类新增说明")
    @RequestMapping(path = "/save", method = RequestMethod.PUT)
    public BaseResponse saveOrgClassify(@RequestBody SysOrgClassify sysOrgClassify) {
      log.info("新增机构分类信息：" + sysOrgClassify.toString());
        try {
            sysOrgClassifyService.save(sysOrgClassify);
            return BaseResponse.getSuccessResponse();
        } catch (Exception e) {
            log.error("增加机构类组信息失败！", e);
            return BaseResponse.getFailResponse(e.getMessage());
        }
    }

    @ApiOperation(value = "机构分类修改", notes = "机构分类修改说明")
    @RequestMapping(path = "/update", method = RequestMethod.PUT)
    public BaseResponse updateByPri(@RequestBody SysOrgClassify sysOrgClassify) {
        log.info("请求修改的分类信息为：" + sysOrgClassify.toString());
        try {
            int num = sysOrgClassifyService.updateByPri(sysOrgClassify);
            if (num <= 0) {
                throw new Exception("0条数据被修改，请确认修改结果！");
            }
            return BaseResponse.getSuccessResponse();
        } catch (Exception e) {
            log.error("修改机构分类信息失败！", e);
            return BaseResponse.getFailResponse(e.getMessage());
        }
    }

    @ApiOperation(value = "机构分类删除", notes = "机构分类删除说明")
    @RequestMapping(path = "/del/{classifyId}", method = RequestMethod.DELETE)
    public BaseResponse deleteByPri(@PathVariable int classifyId) {
        log.info("请求删除的分类信息classifyId为：" + classifyId);
        try {
            int num = sysOrgClassifyService.deleteByPri(classifyId);
            if (num <= 0) {
                throw new Exception("0条数据被删除，请确认删除结果！");
            }
            return BaseResponse.getSuccessResponse();
        } catch (Exception e) {
            log.error("删除机构分类信息失败！", e);
            return BaseResponse.getFailResponse("删除机构分类信息失败！" + e.getMessage());
        }
    }

   /* @ApiOperation(value = "根据条件查询", notes = "根据条件查询")
    @RequestMapping(path = "/queryByCond", method = RequestMethod.GET)
    public BaseResponse queryByCond(@RequestBody SysOrgClassify sysOrgClassify) {
        log.info("请求查询条件为：" + sysOrgClassify);
        PageInfo<Map> list = sysOrgClassifyService.queryByCond(sysOrgClassify);
        log.info("获取到的分类信息为：" + list);
        return BaseResponse.getSuccessResponse(list);
    }*/

    @RequestMapping(method = RequestMethod.GET, path = "/nindex")
    @ApiOperation(value = "查询最大nindex", notes = "查询最大nindex详情")
    public BaseResponse queryMaxNindex() {
        try {
            String nindex = sysOrgClassifyService.queryMaxIndex();
            return BaseResponse.getSuccessResponse((Object) nindex);
        } catch (Exception e) {
            log.error("查询最大nindex失败!", e);
            return BaseResponse.getFailResponse("查询最大nindex失败!");
        }
    }
}
