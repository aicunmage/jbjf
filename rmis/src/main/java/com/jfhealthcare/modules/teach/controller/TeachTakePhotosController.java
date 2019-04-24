package com.jfhealthcare.modules.teach.controller;

import com.jfhealthcare.common.base.BaseResponse;
import com.jfhealthcare.modules.business.mapper.ViewWorklistMapper;
import com.jfhealthcare.modules.teach.entity.TeachTakePhotosIndex;
import com.jfhealthcare.modules.teach.service.TeachTakePhotosService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * Created By lzw
 * CreateDate 2018/09/07
 */
@Slf4j
@Api(value = "teach->提供查询投照示教api")
@RestController
@RequestMapping(value="/v2/rmis/sysop/teach")
public class TeachTakePhotosController {

    @Autowired
    TeachTakePhotosService teachTakePhotosService;

    /**
     * 查询投照示教一级目录
     * @return
     */
    @RequestMapping(method= RequestMethod.GET,path="/allFirst")
    @ApiOperation(value = "查询投照示教一级目录", notes = "查询投照示教一级目录")
    public BaseResponse queryByUpper(){
        try {
            List<TeachTakePhotosIndex> list = teachTakePhotosService.queryPhotosByUpper(0);
            return BaseResponse.getSuccessResponse(list);
        }catch (Exception e) {
            log.error("查询投照示教一级目录失败！", e);
        }
        return BaseResponse.getFailResponse("查询投照示教一级目录失败！");
    }

    /**
     * 根据上级目录id查询对应的所有下级
     * @return
     */
    @RequestMapping(method= RequestMethod.GET,path="/son/{id}")
    @ApiOperation(value = "根据上级目录id查询下级目录", notes = "根据上级目录id查询下级目录")
    public BaseResponse querySonByUpper(@PathVariable("id") String id){
        try {
            Integer upper = Integer.parseInt(id);
            List<TeachTakePhotosIndex> list = teachTakePhotosService.queryPhotosByUpper(upper);
            return BaseResponse.getSuccessResponse(list);
        }catch (Exception e) {
            log.error("根据上级目录id查询下级目录失败！", e);
        }
        return BaseResponse.getFailResponse("根据上级目录id查询下级目录失败！");
    }

    /**
     * 查询投照示教图片路径
     * @param accseesionNum
     * @return
     */
    @RequestMapping(method= RequestMethod.GET,path="/{accseeionNum}")
    @ApiOperation(value = "查询拒绝的教学图片路径", notes = "查询拒绝的教学图片路径")
    public BaseResponse queryPathByAccessionNum(@PathVariable("accseeionNum") String accseesionNum){
        try {
            List<String> pathList = teachTakePhotosService.queryPathByAccessionNum(accseesionNum);
            return BaseResponse.getSuccessResponse(pathList);
        }catch (Exception e) {
            log.error("查询投照示教图片路径失败！", e);
        }
        return BaseResponse.getFailResponse("查询投照示教图片路径失败！");
    }
}
