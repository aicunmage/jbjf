package com.jfhealthcare.modules.system.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.jfhealthcare.common.base.BaseResponse;
import com.jfhealthcare.common.entity.CommonStaticValue;
import com.jfhealthcare.common.entity.LoginUserEntity;
import com.jfhealthcare.common.exception.RmisException;
import com.jfhealthcare.common.utils.TransferUtils;
import com.jfhealthcare.common.validator.Assert;
import com.jfhealthcare.common.validator.ValidatorUtils;
import com.jfhealthcare.common.validator.group.Edit;
import com.jfhealthcare.common.validator.group.Insert;
import com.jfhealthcare.modules.basics.BasicPageEntity;
import com.jfhealthcare.modules.system.annotation.LoginUser;
import com.jfhealthcare.modules.system.annotation.SysLogAop;
import com.jfhealthcare.modules.system.entity.SysRepGroup;
import com.jfhealthcare.modules.system.entity.SysRole;
import com.jfhealthcare.modules.system.request.RepGroupRequest;
import com.jfhealthcare.modules.system.request.SysRepGroupRequest;
import com.jfhealthcare.modules.system.service.RepGroupService;
import com.jfhealthcare.modules.system.service.SysRepGroupService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

/**
 * @author jianglinyan
 * @date 2017年9月27日下午2:24:20
 */
@Slf4j // 日志注解--lombok
@RestController
@Api(value = "a用户->用户和组关系接口api")
@RequestMapping("/v2/rmis/sysop/sysRepGroup")
public class SysRepGroupController {
	@Autowired
	private SysRepGroupService sysRepGroupService;
	
	@Autowired
	private RepGroupService repGroupService;

	/* 查询所有分组和人员关系详情信息 */
	@RequestMapping(path="/all",method = RequestMethod.POST)
	@ApiOperation(value = "查询所有分组和人员关系详情", notes = "查询所有分组和人员关系详情")
	public BaseResponse querySysRepGroup(@RequestBody BasicPageEntity basic) {
		try {
			PageInfo<SysRepGroup> sysRepGroup = sysRepGroupService.querySysRepGroup(basic.getPageNum(),
					basic.getPageSize());
			return BaseResponse.getSuccessResponse(sysRepGroup);
		} catch (RmisException rmis) {
			log.info("查询所有分组和人员关系失败!", rmis.getMessage());
			return BaseResponse.getFailResponse(rmis.getMessage());
		} catch (Exception e) {
			log.error("查询所有分组和人员关系失败!", e);
			return BaseResponse.getFailResponse("查询所有分组和人员关系失败!");
		}
	}

	/* 查询单个分组和人员关系信息 */
	@RequestMapping(path = "/one", method = RequestMethod.POST)
	@ApiOperation(value = "查询单个分组和人员关系", notes = "查询单个分组和人员关系")
	public BaseResponse querySysRepGroupById(@RequestBody SysRepGroupRequest sysRepGroupRequest) {
		try {
			Assert.isBlank(sysRepGroupRequest.getGroupId(), "分组编号不能为空!");
			SysRepGroup sysrg = new SysRepGroup();
			TransferUtils.copyPropertiesIgnoreNull(sysRepGroupRequest, sysrg);
			PageInfo<SysRepGroup> sysRepGroup = sysRepGroupService.querySysRepGroupById(sysrg,
					sysRepGroupRequest.getPageNum(), sysRepGroupRequest.getPageSize());
			return BaseResponse.getSuccessResponse(sysRepGroup);
		} catch (RmisException rmis) {
			log.info("查询单个分组和人员关系失败!", rmis.getMessage());
			return BaseResponse.getFailResponse(rmis.getMessage());
		} catch (Exception e) {
			log.error("查询单个分组和人员关系失败!", e);
			return BaseResponse.getFailResponse("查询单个分组和人员关系失败!");
		}
	}

	/* 根据用户ID查询所在的分组信息 */
	@RequestMapping(path = "/queryByOperId", method = RequestMethod.GET)
	@ApiOperation(value = "根据用户ID查询所在的分组信息", notes = "根据用户ID查询所在的分组信息")
	public BaseResponse querySysRepGroupByOperId( @LoginUser LoginUserEntity loginUserEntity) {
		try {
			String operId = loginUserEntity.getSysOperator().getLogincode();
			RepGroupRequest repGroupRequest = new RepGroupRequest();
			String role = getRole(loginUserEntity);
			if("supermanager".equals(role)) {
				//超级管理员
				return BaseResponse.getSuccessResponse(repGroupService.queryRepGroup(repGroupRequest).getList());
			}else if("manager".equals(role)) {
				//影像中心管理员
				repGroupRequest.setImageCenterId(loginUserEntity.getSysOperatorDtl().getOrgId());
				return BaseResponse.getSuccessResponse(repGroupService.queryRepGroup(repGroupRequest).getList());
			}else {
				//报告医生
				return BaseResponse.getSuccessResponse(sysRepGroupService.querySysRepGroupByOperId(operId));
			}
		} catch (RmisException rmis) {
			log.info("根据用户ID查询所在的分组信息失败!", rmis);
			return BaseResponse.getFailResponse(rmis.getMessage());
		} catch (Exception e) {
			log.error("根据用户ID查询所在的分组信息失败!", e);
			return BaseResponse.getFailResponse("根据用户ID查询所在的分组信息失败!");
		}
	}
	
	/* 批量删除组和人员关系表中的 相关数据 */
	@SysLogAop("删除组和人员关系表")
	@RequestMapping(path = "/{ids}", method = RequestMethod.DELETE)
	@ApiOperation(value = "删除分组和人员关系", notes = "删除分组删除分组和人员关系，需要删除的IDs 放到路径中如：/v2/sysRepGroup/1,2,3")
	public BaseResponse deleteSysRepGroup(@PathVariable String ids) {
		try {
			Assert.isBlank(ids, "ids不能为空!");
			String[] split = ids.split(",");
			sysRepGroupService.deleteSysRepGroup(split);
			return BaseResponse.getSuccessResponse();
		} catch (RmisException rmis) {
			log.info("批量删除分组和人员关系失败!", rmis.getMessage());
			return BaseResponse.getFailResponse(rmis.getMessage());
		} catch (Exception e) {
			log.error("批量删除分组和人员关系失败!", e);
			return BaseResponse.getFailResponse("批量删除分组和人员关系失败!");
		}
	}

	/* 新增分组和人员关系 */
	@SysLogAop("新增分组和人员关系")
	@RequestMapping(method = RequestMethod.PUT)
	@ApiOperation(value = "新增分组和人员关系", notes = "新增分组和人员关系")
	public BaseResponse saveSysRepGroup(@RequestBody SysRepGroupRequest sysRepGroupRequest, @LoginUser LoginUserEntity loginUserEntity) {
		try {
			ValidatorUtils.validateEntity(sysRepGroupRequest, Insert.class);
			SysRepGroup re = new SysRepGroup();
			TransferUtils.copyPropertiesIgnoreNull(sysRepGroupRequest, re);
			//增加所属影像中心
			if (!"super".equals(loginUserEntity.getSysOperator().getLogincode())) {
				re.setImageCenterId(loginUserEntity.getSysOperatorDtl().getOrgId());
			}
			
			sysRepGroupService.saveSysRepGroup(re);
			return BaseResponse.getSuccessResponse();
		} catch (RmisException rmis) {
			log.info("新增分组和人员关系失败!", rmis.getMessage());
			return BaseResponse.getFailResponse(rmis.getMessage());
		} catch (Exception e) {
			log.error("新增分组和人员关系失败!", e);
			return BaseResponse.getFailResponse("新增分组和人员关系失败!");
		}
	}

	/* 修改分组和人员关系信息 */
	@SysLogAop("修改分组和人员关系")
	@RequestMapping(method = RequestMethod.POST)
	@ApiOperation(value = "修改分组和人员关系", notes = "修改分组和人员关系")
	public BaseResponse updateSysRepGroup(@RequestBody SysRepGroupRequest sysRepGroupRequest, @LoginUser LoginUserEntity loginUserEntity) {
		try {
			ValidatorUtils.validateEntity(sysRepGroupRequest, Edit.class);
			SysRepGroup re = new SysRepGroup();
			TransferUtils.copyPropertiesIgnoreNull(sysRepGroupRequest, re);
			//增加所属影像中心
			if (!"super".equals(loginUserEntity.getSysOperator().getLogincode())) {
				re.setImageCenterId(loginUserEntity.getSysOperatorDtl().getOrgId());
			}
			sysRepGroupService.updateSysRepGroup(re);
			return BaseResponse.getSuccessResponse();
		} catch (RmisException rmis) {
			log.info("修改分组和人员关系失败!", rmis.getMessage());
			return BaseResponse.getFailResponse(rmis.getMessage());
		} catch (Exception e) {
			log.error("修改分组和人员关系失败!", e);
			return BaseResponse.getFailResponse("修改分组和人员关系失败!");
		}
	}
	

	/**
	 * 根据用户信息返回用户是否是超级管理员或者普通管理员
	 * @return
	 */
	public final static String getRole(LoginUserEntity loginUserEntity) {
		List<SysRole> roles = loginUserEntity.getSysRoles();
		// a. 影像中心管理员角色ID：b5817a2b-79ac-11e8-bd38-0242ac110002
		// b. 超级管理员角色ID：d298ce33-c918-11e7-97d4-1866daf153ec
		for (SysRole role : roles) {
			if (CommonStaticValue.ROLE_SUPER_MANAGER.equals(role.getId())) {
				// 超级管理员
				return "supermanager";
			}
		}
		// 不是超级管理员，就判断是否是管理员
		for (SysRole role : roles) {
			if (CommonStaticValue.ROLE_MANAGER.equals(role.getId())) {
				// 管理员
				return "manager";
			}
		}
		return null;
	}
}
