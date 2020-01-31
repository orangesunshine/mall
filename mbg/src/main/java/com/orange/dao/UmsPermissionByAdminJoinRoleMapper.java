package com.orange.dao;

import com.orange.model.UmsPermission;

import java.util.List;

public interface UmsPermissionByAdminJoinRoleMapper {
    List<UmsPermission> getPermissionList(long adminId);
}
