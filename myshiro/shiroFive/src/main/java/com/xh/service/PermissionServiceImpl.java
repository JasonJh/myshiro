package com.xh.service;

import com.xh.dao.PermissionDao;
import com.xh.dao.PermissionDaoImpl;
import com.xh.entity.Permission;

public class PermissionServiceImpl implements PermissionService {
    private PermissionDao permissionDao=new PermissionDaoImpl();

    public Permission createPermission(Permission permission) {
        return permissionDao.createPermission(permission);
    }

    public void deletePermission(Long permissionId) {
        permissionDao.deletePermission(permissionId);
    }
}
