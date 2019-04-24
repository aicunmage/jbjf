package com.jfhealthcare.modules.business.controller;

import com.github.pagehelper.PageInfo;
import com.jfhealthcare.common.base.BaseResponse;
import com.jfhealthcare.common.base.PageResponse;
import com.jfhealthcare.common.base.ValueResponse;
import com.jfhealthcare.common.entity.LoginUserEntity;
import com.jfhealthcare.common.exception.RmisException;
import com.jfhealthcare.common.validator.ValidatorUtils;
import com.jfhealthcare.common.validator.group.Edit;
import com.jfhealthcare.common.validator.group.Query;
import com.jfhealthcare.modules.business.entity.RepImage;
import com.jfhealthcare.modules.business.entity.RepImageHis;
import com.jfhealthcare.modules.business.request.ViewWorklistRequest;
import com.jfhealthcare.modules.business.response.ViewWorklistResponse;
import com.jfhealthcare.modules.business.service.ViewWorklistHisService;
import com.jfhealthcare.modules.business.service.ViewWorklistService;
import com.jfhealthcare.modules.system.annotation.LoginUser;
import com.jfhealthcare.modules.system.annotation.SysLogAop;
import com.jfhealthcare.modules.system.entity.SysOrgClassify;
import com.jfhealthcare.modules.system.service.SysDictDtlService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
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
@RequestMapping("/v2/rmis/sysop/worklist")
public class ViewWorklistController {
	
	@Value("${dcm.image.url}")
	private String dcmImageUrl; 
	
	@Autowired
	private ViewWorklistService viewWorklistService;
	@Autowired
	private ViewWorklistHisService viewWorklistHisService;
	@Autowired
	private SysDictDtlService sysDictDtlService;

	@RequestMapping(method = RequestMethod.POST)
	@ApiOperation(value = "worklist查询", notes = "worklist查询详情")
	public BaseResponse queryViewWorklist(@RequestBody ViewWorklistRequest viewWorklistRequest, @LoginUser LoginUserEntity loginUserEntity) {
		try {
			//增加超时时间设置
			String timeOutTime = sysDictDtlService.queryDictDtlById("f12522da-7e6e-11e8-88f4-0242ac110002").getName();
			String timeOutWillBe = sysDictDtlService.queryDictDtlById("495611c4-7f3e-11e8-88f4-0242ac110002").getName();
			viewWorklistRequest.setTimeOutTime(timeOutTime);
			viewWorklistRequest.setTimeOutWillBe(timeOutWillBe);
			PageInfo<ViewWorklistResponse> vwls=viewWorklistService.queryViewWorklist(viewWorklistRequest, loginUserEntity);
			return PageResponse.getSuccessResponse(vwls);
		}catch (Exception e) {
			log.error("worklist查询!", e);
			return PageResponse.getFailResponse("worklist查询失败!");
		}
	}
	
	@RequestMapping(method = RequestMethod.GET,path="/{checkAccessionNum}")
	@ApiOperation(value = "worklist单个查询", notes = "worklist单个查询详情")
	public BaseResponse queryOneViewWorklist(@PathVariable("checkAccessionNum")String checkAccessionNum, @LoginUser LoginUserEntity loginUserEntity) {
		try {
			ViewWorklistResponse viewWorklistResponse=viewWorklistService.queryOneViewWorklist(checkAccessionNum);
			// 历史报告处理
			if(viewWorklistResponse == null || viewWorklistResponse.getCheckAccessionNum() == null){
				viewWorklistResponse = viewWorklistHisService.queryOneViewWorklist(checkAccessionNum);
			}
			return BaseResponse.getSuccessResponse(viewWorklistResponse);
		}catch (Exception e) {
			log.error("worklist单个查询!", e);
			return BaseResponse.getFailResponse("worklist单个查询失败!");
		}
	}
	
	@RequestMapping(method = RequestMethod.GET,path="/webviewer/{checkAccessionNum}")
	@ApiOperation(value = "webviewer地址查询", notes = "webviewer地址查询详情")
	public BaseResponse queryWebviewerUrlByAccessionNum(@PathVariable("checkAccessionNum")String checkAccessionNum) {
		try {
			String sopUrl=viewWorklistService.queryWebviewerUrlByAccessionNum(checkAccessionNum);
			return BaseResponse.getSuccessResponse(sopUrl);
		}catch (Exception e) {
			log.error("worklist单个查询!", e);
			return BaseResponse.getFailResponse("webviewer地址查询失败!");
		}
	}
	
	@RequestMapping(method = RequestMethod.GET,path="/repimage/{repUid}")
	@ApiOperation(value = "worklist查询报告贴图", notes = "worklist查询报告贴图详情")
	public BaseResponse queryRepImageByRepUid(@PathVariable("repUid")String repUid) {
		try {
			List<RepImage> repimages=viewWorklistService.queryRepImageByRepUid(repUid);
			// 历史报告处理
			if(repimages == null || repimages.size() == 0){
				List<RepImageHis> repimageHis = viewWorklistHisService.queryRepImageByRepUid(repUid);
				return ValueResponse.getSuccessValue(repimageHis,dcmImageUrl);
			}else {
				return ValueResponse.getSuccessValue(repimages, dcmImageUrl);
			}
		}catch (Exception e) {
			log.error("worklist查询报告贴图失败!", e);
			return BaseResponse.getFailResponse("worklist查询报告贴图失败!");
		}
	}
	
	@ApiOperation(value = "查询历史报告", notes = "查询历史报告详情")
	@RequestMapping(method = RequestMethod.POST,path="/historyReport")
	public BaseResponse queryHistoryReport(@RequestBody ViewWorklistRequest viewWorklistRequest) {
		try {
			
			ValidatorUtils.validateEntity(viewWorklistRequest, Query.class);
			List<ViewWorklistResponse> vwls=viewWorklistService.queryHistoryReport(viewWorklistRequest);
			return BaseResponse.getSuccessResponse(vwls);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return BaseResponse.getFailResponse(e.getMessage());
		}
	}
	
	
	@RequestMapping(method = RequestMethod.DELETE,path="/repimage/{repImageId}")
	@ApiOperation(value = "worklist删除报告贴图", notes = "worklist删除报告贴图详情")
	public BaseResponse deleteRepImageByRepImageId(@PathVariable("repImageId")String repImageId) {
		try {
			viewWorklistService.deleteRepImageByRepImageId(repImageId);
			return BaseResponse.getSuccessResponse("worklist删除报告贴图成功!");
		}catch (Exception e) {
			log.error("worklist删除报告贴图失败!", e);
			return BaseResponse.getFailResponse("worklist删除报告贴图失败!");
		}
	}
	
	
	
	@SysLogAop("更新报告")
	@ApiOperation(value = "更新报告", notes = "更新报告详情")
	@RequestMapping(method = RequestMethod.POST,path="/report")
	public BaseResponse updateCheckListIndex(@RequestBody ViewWorklistRequest viewWorklistRequest,@LoginUser LoginUserEntity loginUserEntity) {
		try {
			log.info(loginUserEntity.getSysOperator().getLogincode()+"：处理报告开始..........");
			ValidatorUtils.validateEntity(viewWorklistRequest, Edit.class);
			if(StringUtils.equals("isOpen",viewWorklistRequest.getCheckBut() )) {//isopen  用于第一次打开判断
				//判断是不是第一次打开
				Map<String, String> btnsMap = viewWorklistService.queryBtnsByCheckAccessionNum(viewWorklistRequest.getCheckAccessionNum(),loginUserEntity);
				if(StringUtils.equals("1",btnsMap.get("isWork"))) {//不需要操作的  不更新
					viewWorklistService.updateCheckListIndex(viewWorklistRequest,loginUserEntity);
					btnsMap = viewWorklistService.queryBtnsByCheckAccessionNum(viewWorklistRequest.getCheckAccessionNum(),loginUserEntity);
				}
				ViewWorklistResponse viewWorklistResponse = viewWorklistService.queryOneViewWorklist(viewWorklistRequest.getCheckAccessionNum());
				viewWorklistResponse.setBtnsMap(btnsMap);
				return BaseResponse.getSuccessResponse(viewWorklistResponse);
			}else {
				viewWorklistService.updateCheckListIndex(viewWorklistRequest,loginUserEntity);
			}
			log.info(loginUserEntity.getSysOperator().getLogincode()+"：处理报告结束.........");
			return BaseResponse.getSuccessResponse();
		} catch (RmisException e) {
			log.error(e.getMessage(), e);
			return BaseResponse.getFailResponse(e.getMessage());
		}catch (Exception e) {
			log.error("更新报告失败!", e);
			return BaseResponse.getFailResponse("更新报告失败!");
		}
	}
	
	@RequestMapping(method = RequestMethod.GET,path="/isRemind")
	@ApiOperation(value = "worklist报告是否有需要处理的", notes = "worklist报告是否有需要处理的详情 ：{1：要处理，0：不需要处理}")
	public BaseResponse queryViewWorklistIsRemind(@LoginUser LoginUserEntity loginUserEntity) {
		try {
			int isRemind=viewWorklistService.queryViewWorklistIsRemind(loginUserEntity);
			return BaseResponse.getSuccessResponse(isRemind);
		}catch (Exception e) {
			log.error("worklist报告是否有需要处理的失败!", e);
			return BaseResponse.getFailResponse("worklist报告是否有需要处理的失败!");
		}
	}

	@RequestMapping(method = RequestMethod.GET,path="/queryClassifyListByUser")
	@ApiOperation(value = "获取当前用户所属影像中心的所有机构分类", notes = "获取当前用户所属影像中心的所有机构分类")
	public BaseResponse queryClassifyListByUser(@LoginUser LoginUserEntity loginUserEntity) {
		try {
			List<SysOrgClassify> classifyList = viewWorklistService.queryClassifyListByUser(loginUserEntity);
			return BaseResponse.getSuccessResponse(classifyList);
		}catch (Exception e) {
			log.error("获取当前用户所属影像中心的所有机构分类失败!", e);
			return BaseResponse.getFailResponse("获取当前用户所属影像中心的所有机构分类失败!");
		}
	}
}
