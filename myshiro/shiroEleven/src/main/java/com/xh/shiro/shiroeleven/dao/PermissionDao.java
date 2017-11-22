package com.xh.shiro.shiroeleven.dao;

import com.xh.shiro.shiroeleven.entity.Permission;

public interface PermissionDao {
    public Permission createPermission(Permission permission);

    public void deletePermission(Long permissionId);
}
