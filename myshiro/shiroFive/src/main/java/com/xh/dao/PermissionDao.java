package com.xh.dao;

import com.xh.entity.Permission;

public interface PermissionDao {

    public Permission createPermission(Permission permission);

    public void deletePermission(Long permission);
}
