package com.jfhealthcare.modules.system.controller;


import java.util.Date;
import java.util.List;

import com.jfhealthcare.modules.system.entity.SysOrgClassify;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.jfhealthcare.common.base.BaseResponse;
import com.jfhealthcare.common.base.PageResponse;
import com.jfhealthcare.common.entity.LoginUserEntity;
import com.jfhealthcare.common.utils.RmisRandomUtils;
import com.jfhealthcare.common.validator.Assert;
import com.jfhealthcare.common.validator.ValidatorUtils;
import com.jfhealthcare.common.validator.group.Edit;
import com.jfhealthcare.common.validator.group.Insert;
import com.jfhealthcare.modules.system.annotation.LoginUser;
import com.jfhealthcare.modules.system.annotation.SysLogAop;
import com.jfhealthcare.modules.system.entity.SysOrgGroupRel;
import com.jfhealthcare.modules.system.entity.SysOrganization;
import com.jfhealthcare.modules.system.request.SysOrganizationRequest;
import com.jfhealthcare.modules.system.response.OrganizationResponse;
import com.jfhealthcare.modules.system.service.SysOrgGroupRelService;
import com.jfhealthcare.modules.system.service.SysOrganizationService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import springfox.documentation.annotations.ApiIgnore;


/**
 * 机构管理
 * @author xujinma
 */
@Slf4j
@Api(value = "b组织->机构管理api")
@RestController
@RequestMapping("/v2/rmis/sysop/sysorganization")
public class SysOrganizationController {

	@Autowired
	private SysOrganizationService sysOrganizationService;
	
	@Autowired
	private SysOrgGroupRelService sysOrgGroupRelService;
	
	@ApiOperation(value = "查询机构", notes = "查询机构说明")
	@RequestMapping(method = RequestMethod.POST,value="/all")
	public BaseResponse querySysOrganization(@RequestBody SysOrganizationRequest sysOrganizationRequest) {
		PageInfo<OrganizationResponse> queryByCondition = sysOrganizationService.queryByCondition(sysOrganizationRequest);
		return PageResponse.getSuccessPage(queryByCondition);
	}
	
	@ApiOperation(value = "查询所有机构", notes = "查询所有机构说明")
	@RequestMapping(method = RequestMethod.GET)
	public BaseResponse queryAllSysOrganization() {
		try {
			List<OrganizationResponse> all=sysOrganizationService.queryAllSysOrganization();
			return BaseResponse.getSuccessResponse(all);
		} catch (Exception e) {
			log.error("查询所有机构！", e);
			return BaseResponse.getFailResponse("查询所有机构！");
		}
		
	}
	
	@ApiOperation(value = "查询单个机构", notes = "查询单个机构说明")
	@RequestMapping(method = RequestMethod.GET,value="/{id}")
	public BaseResponse querySingleSysOrganization(@PathVariable String id) {
		OrganizationResponse sysOrganization=null;
		try {
			Assert.isBlank(id, "查询id不能为空！");
			sysOrganization = sysOrganizationService.querySingleSysOrganization(id);
		} catch (Exception e) {
			log.error("单个查询异常！", e);
			return BaseResponse.getFailResponse("单个查询异常！");
		}
		return BaseResponse.getSuccessResponse(sysOrganization);
	}
	
	@SysLogAop("新增机构")
	@ApiOperation(value = "新增机构", notes = "新增机构说明")
	@RequestMapping(method = RequestMethod.PUT)
	public BaseResponse addSysOrganization(@RequestBody SysOrganizationRequest sysOrganizationRequest){
		
		try {
			//新增机构表
			ValidatorUtils.validateEntity(sysOrganizationRequest,Insert.class);
			String hospitalId = sysOrganizationService.insertSysOrganization(sysOrganizationRequest);
			// 新增机构分组关联表，影像中心不需要增加
			if (sysOrganizationRequest.getClsId() != null && !"0".equals(sysOrganizationRequest.getClsId())) {
				SysOrgGroupRel sysOrgGroupRel = new SysOrgGroupRel();
				sysOrgGroupRel.setImageId(sysOrganizationRequest.getImageCenter());
				sysOrgGroupRel.setHospitalId(hospitalId);
				sysOrgGroupRel.setGroupId(sysOrganizationRequest.getOrgGroupId());
				//新增机构分类关联id关系---lzw
				sysOrgGroupRel.setClassifyId(sysOrganizationRequest.getOrgClassifyId());
				sysOrgGroupRel.setIsValid(1);
				sysOrgGroupRelService.save(sysOrgGroupRel);
			}
		} catch (Exception e) {
			log.error("新增异常！", e);
			return BaseResponse.getFailResponse("新增失败！" + e.getMessage());
		}
		return BaseResponse.getSuccessResponse("新增成功！");
	}
	
	@SysLogAop("修改机构")
	@ApiOperation(value = "修改机构", notes = "修改机构说明")
	@RequestMapping(method = RequestMethod.POST)
	public BaseResponse updateSysOrganization(@RequestBody SysOrganizationRequest sysOrganizationRequest){
		
		try {
			ValidatorUtils.validateEntity(sysOrganizationRequest,Edit.class);
			sysOrganizationService.updateSysOrganization(sysOrganizationRequest);

			//修改机构分组关联表,影像中心不需要增加或者修改
			if (sysOrganizationRequest.getClsId() != null && !"0".equals(sysOrganizationRequest.getClsId())) {
				List<SysOrgGroupRel> list = sysOrgGroupRelService.queryByHospId(sysOrganizationRequest.getId());
				SysOrgGroupRel sysOrgGroupRel = null;
				if (list != null && list.size() > 0) {
					// 存在关联，修改
					sysOrgGroupRel = list.get(0);// 只取第一条作为修改
					sysOrgGroupRel.setImageId(sysOrganizationRequest.getImageCenter());
					sysOrgGroupRel.setGroupId(sysOrganizationRequest.getOrgGroupId());
					//修改机构分类关联关系---lzw
					sysOrgGroupRel.setClassifyId(sysOrganizationRequest.getOrgClassifyId());
					sysOrgGroupRel.setCreateDate(new Date());
					sysOrgGroupRelService.updateByPrimary(sysOrgGroupRel);
				} else {
					// 不存在关联，新增
					sysOrgGroupRel = new SysOrgGroupRel();
					sysOrgGroupRel.setImageId(sysOrganizationRequest.getImageCenter());
					sysOrgGroupRel.setHospitalId(sysOrganizationRequest.getId());
					sysOrgGroupRel.setGroupId(sysOrganizationRequest.getOrgGroupId());
					//新增机构分类关联关系---lzw
					sysOrgGroupRel.setClassifyId(sysOrganizationRequest.getOrgClassifyId());
					sysOrgGroupRel.setIsValid(1);
					sysOrgGroupRelService.save(sysOrgGroupRel);
				}
			}
		} catch (Exception e) {
			log.error("机构修改异常！", e);
			return BaseResponse.getFailResponse("机构修改失败！" + e.getMessage());
		}
		return BaseResponse.getSuccessResponse("机构修改成功！");
	}
	
	@SysLogAop("删除机构")
	@ApiOperation(value = "删除机构", notes = "删除机构说明")
	@RequestMapping(method = RequestMethod.DELETE,value="/{id}")
	public BaseResponse deleteSysOrganization(@PathVariable String id){
		
		try {
			Assert.isBlank(id, "删除id不能为空！");
			sysOrganizationService.deleteSysOrganization(id);
			//删除机构分组信息，影像中心不需要
			OrganizationResponse orgInfo = sysOrganizationService.querySingleSysOrganization(id);
			if (orgInfo != null && orgInfo.getClsId() != null && !"0".equals(orgInfo.getClsId())) {
				SysOrgGroupRel sysOrgGroupRel = new SysOrgGroupRel();
				sysOrgGroupRel.setHospitalId(orgInfo.getId());
				sysOrgGroupRel.setIsValid(0);
				sysOrgGroupRel.setExpireDate(new Date());
				sysOrgGroupRelService.deleteGroupRel(sysOrgGroupRel);
			}
			
		} catch (Exception e) {
			log.error("机构删除异常！", e);
			return BaseResponse.getFailResponse("机构删除失败！" + e.getMessage());
		}
		return BaseResponse.getSuccessResponse("机构删除成功！");
	}
	
	@ApiOperation(value = "获取机构编号", notes = "新增时获取机构编号值")
	@RequestMapping(method = RequestMethod.GET,path="/code")
	public BaseResponse getOrganuzationCode(){
		String code = "";
		try {
			boolean isContinue = Boolean.TRUE;
			SysOrganization sysorg = new SysOrganization();
			while (isContinue) {
				code = RmisRandomUtils.getRandomNumForOrgCode();
				sysorg.setCode(code);
				List<SysOrganization> sysOrganizations = sysOrganizationService.queryBySysOrg(sysorg);
				if (CollectionUtils.isEmpty(sysOrganizations)) {
					isContinue = Boolean.FALSE;
				}
			}
		} catch (Exception e) {
			log.error("获取机构编号异常！", e);
			return BaseResponse.getFailResponse("获取机构编号失败！");
		}
		return BaseResponse.getSuccessResponse("获取机构编号成功",(Object) code);
	}
	
	@ApiOperation(value = "查询用户对应机构信息", notes = "查询用户对应机构信息说明")
	@RequestMapping(method = RequestMethod.GET,path="/oper")
	public BaseResponse querySysOrgInfoByLogincode(@ApiIgnore @LoginUser LoginUserEntity loginUserEntity) {
		try {
			String orgId = loginUserEntity.getSysOperatorDtl().getOrgId();
			SysOrganization sysOrganization=new SysOrganization();
			sysOrganization.setId(orgId);
			List<SysOrganization> sysOrganizations = sysOrganizationService.queryBySysOrg(sysOrganization);
			Assert.isListOnlyOne(sysOrganizations, "orgId 对应多个机构异常！");
			return BaseResponse.getSuccessResponse(sysOrganizations.get(0));
		} catch (Exception e) {
			log.error("查查询用户对应机构信息异常！", e);
			return BaseResponse.getFailResponse("查查询用户对应机构信息异常！");
		}
		
	}
	
	@ApiOperation(value = "查询所有的影像中心信息", notes = "查询所有的影像中心信息说明")
	@RequestMapping(method = RequestMethod.GET, path = "/allImageCenter")
	public BaseResponse queryAllImageCenter() {
		try {
			SysOrganizationRequest sysOrganizationRequest = new SysOrganizationRequest();
			// 影像中心标志
			sysOrganizationRequest.setClsId("0");
			PageInfo<OrganizationResponse> response = sysOrganizationService.queryByCondition(sysOrganizationRequest);
			return PageResponse.getSuccessPage(response);
		} catch (Exception e) {
			log.error("查询所有的影像中心信息异常！", e);
			return BaseResponse.getFailResponse("查询所有的影像中心信息异常！" + e.getMessage());
		}
	}
	
	@ApiOperation(value = "根据条件查询影像中心信息", notes = "根据条件查询影像中心信息说明")
	@RequestMapping(method = RequestMethod.POST, path = "/getImageCenterByCond")
	public BaseResponse queryImageCenterByConde(@RequestBody SysOrganizationRequest sysOrganizationRequest) {
		try {
			// 影像中心标志
			sysOrganizationRequest.setClsId("0");
			PageInfo<OrganizationResponse> response = sysOrganizationService.queryByCondition(sysOrganizationRequest);
			return PageResponse.getSuccessPage(response);
		} catch (Exception e) {
			log.error("根据条件查询影像中心信息异常！", e);
			return BaseResponse.getFailResponse("根据条件查询影像中心信息异常！" + e.getMessage());
		}

	}

	@ApiOperation(value = "查询影像中心下的所有分类信息", notes = "查询影像中心下的所有分类信息")
	@RequestMapping(method = RequestMethod.POST, path = "/getClassifyByOrgCode")
	public BaseResponse getClassifyByOrgCode(@RequestBody SysOrganizationRequest sysOrganizationRequest) {
		try {
			List<SysOrgClassify> list = sysOrganizationService.getClassifyByOrgCode(sysOrganizationRequest.getCode());
			return BaseResponse.getSuccessResponse(list);
		} catch (Exception e) {
			log.error("查询影像中心下的所有分类信息异常！", e);
			return BaseResponse.getFailResponse("查询影像中心下的所有分类信息异常！" + e.getMessage());
		}

	}
}
