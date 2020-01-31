package com.orange.service;

import com.orange.model.UmsAdmin;
import com.orange.model.UmsPermission;

import java.util.List;

public interface UmsAdminService {
    /**
     * 根据用户名获取后台管理员
     *
     * @param username
     * @return
     */
    UmsAdmin getAdminByUsername(String username);

    /**
     * 注册
     *
     * @param umsAdmin
     * @return
     */
    UmsAdmin register(UmsAdmin umsAdmin);

    /**
     * 登录
     *
     * @param username
     * @param password
     * @return
     */
    String login(String username, String password);

    /**
     * 获取用户所有权限（包括角色+- 权限）
     *
     * @param adminId
     * @return
     */
    List<UmsPermission> getPermissionList(long adminId);
}
