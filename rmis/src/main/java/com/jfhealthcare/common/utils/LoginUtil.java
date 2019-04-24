package com.jfhealthcare.common.utils;

import com.jfhealthcare.common.entity.CommonStaticValue;
import com.jfhealthcare.common.entity.LoginUserEntity;
import com.jfhealthcare.modules.system.entity.SysRole;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * Created By lzw
 * CraeteDate 10:25 2018/9/19
 */
@Slf4j
public class LoginUtil {

    /**
     * 根据用户信息返回用户是否是超级管理员或者普通管理员
     * @return
     */
    public static String getRole(LoginUserEntity loginUserEntity) {
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
