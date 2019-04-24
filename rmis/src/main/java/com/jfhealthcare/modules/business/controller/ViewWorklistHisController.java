package com.jfhealthcare.modules.business.controller;

import com.github.pagehelper.PageInfo;
import com.jfhealthcare.common.base.BaseResponse;
import com.jfhealthcare.common.base.PageResponse;
import com.jfhealthcare.common.base.ValueResponse;
import com.jfhealthcare.common.entity.LoginUserEntity;
import com.jfhealthcare.common.exception.RmisException;
import com.jfhealthcare.modules.business.entity.RepImageHis;
import com.jfhealthcare.modules.business.request.ViewWorklistRequest;
import com.jfhealthcare.modules.business.response.ViewWorklistResponse;
import com.jfhealthcare.modules.business.service.ViewWorklistHisService;
import com.jfhealthcare.modules.system.annotation.LoginUser;
import com.jfhealthcare.modules.system.annotation.SysLogAop;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author xujinma
 */
@Slf4j
@Api(value = "a-work->worklist api")
@RestController
@RequestMapping("/v2/rmis/sysop/worklisthis")
public class ViewWorklistHisController {

    @Value("${dcm.image.url}")
    private String dcmImageUrl;

    @Autowired
    private ViewWorklistHisService viewWorklistHisService;


    @RequestMapping(method = RequestMethod.POST)
    @ApiOperation(value = "worklist查询", notes = "worklist查询详情")
    public BaseResponse queryViewWorklist(@RequestBody ViewWorklistRequest viewWorklistRequest, @LoginUser LoginUserEntity loginUserEntity) {
        try {
            PageInfo <ViewWorklistResponse> vwls = viewWorklistHisService.queryViewWorklist(viewWorklistRequest, loginUserEntity);
            return PageResponse.getSuccessResponse(vwls);
        } catch (Exception e) {
            log.error("worklist查询!", e);
            return PageResponse.getFailResponse("worklist查询失败!");
        }
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{checkAccessionNum}")
    @ApiOperation(value = "worklist单个查询", notes = "worklist单个查询详情")
    public BaseResponse queryOneViewWorklist(@PathVariable("checkAccessionNum") String checkAccessionNum, @LoginUser LoginUserEntity loginUserEntity) {
        try {
            ViewWorklistResponse viewWorklistResponse = viewWorklistHisService.queryOneViewWorklist(checkAccessionNum);
            return BaseResponse.getSuccessResponse(viewWorklistResponse);
        } catch (Exception e) {
            log.error("worklist单个查询!", e);
            return BaseResponse.getFailResponse("worklist单个查询失败!");
        }
    }

    @RequestMapping(method = RequestMethod.GET, path = "/webviewer/{checkAccessionNum}")
    @ApiOperation(value = "webviewer地址查询", notes = "webviewer地址查询详情")
    public BaseResponse queryWebviewerUrlByAccessionNum(@PathVariable("checkAccessionNum") String checkAccessionNum) {
        try {
            String sopUrl = viewWorklistHisService.queryWebviewerUrlByAccessionNum(checkAccessionNum);
            return BaseResponse.getSuccessResponse(sopUrl);
        } catch (Exception e) {
            log.error("worklist单个查询!", e);
            return BaseResponse.getFailResponse("webviewer地址查询失败!");
        }
    }

    @RequestMapping(method = RequestMethod.GET, path = "/repimage/{repUid}")
    @ApiOperation(value = "worklist查询报告贴图", notes = "worklist查询报告贴图详情")
    public BaseResponse queryRepImageByRepUid(@PathVariable("repUid") String repUid) {
        try {
            List <RepImageHis> repimages = viewWorklistHisService.queryRepImageByRepUid(repUid);
            return ValueResponse.getSuccessValue(repimages, dcmImageUrl);
        } catch (Exception e) {
            log.error("worklist查询报告贴图失败!", e);
            return BaseResponse.getFailResponse("worklist查询报告贴图失败!");
        }
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/repimage/{repImageId}")
    @ApiOperation(value = "worklist删除报告贴图", notes = "worklist删除报告贴图详情")
    public BaseResponse deleteRepImageByRepImageId(@PathVariable("repImageId") String repImageId) {
        try {
            viewWorklistHisService.deleteRepImageByRepImageId(repImageId);
            return BaseResponse.getSuccessResponse("worklist删除报告贴图成功!");
        } catch (Exception e) {
            log.error("worklist删除报告贴图失败!", e);
            return BaseResponse.getFailResponse("worklist删除报告贴图失败!");
        }
    }

    @SysLogAop("更新报告")
    @ApiOperation(value = "更新报告", notes = "更新报告详情")
    @RequestMapping(method = RequestMethod.POST, path = "/report")
    public BaseResponse updateCheckListIndex(@RequestBody ViewWorklistRequest viewWorklistRequest, @LoginUser LoginUserEntity loginUserEntity) {
        try {
            Map <String, String> btnsMap = viewWorklistHisService.queryBtnsByCheckAccessionNum(viewWorklistRequest.getCheckAccessionNum(), loginUserEntity);
            ViewWorklistResponse viewWorklistResponse = viewWorklistHisService.queryOneViewWorklist(viewWorklistRequest.getCheckAccessionNum());
            viewWorklistResponse.setBtnsMap(btnsMap);
            return BaseResponse.getSuccessResponse(viewWorklistResponse);
        } catch (RmisException e) {
            log.error(e.getMessage(), e);
            return BaseResponse.getFailResponse(e.getMessage());
        } catch (Exception e) {
            log.error("更新报告失败!", e);
            return BaseResponse.getFailResponse("更新报告失败!");
        }
    }
}
