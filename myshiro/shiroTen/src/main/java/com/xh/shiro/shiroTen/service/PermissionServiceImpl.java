package com.xh.shiro.shiroTen.service;


import com.xh.shiro.shiroTen.dao.PermissionDao;
import com.xh.shiro.shiroTen.dao.PermissionDaoImpl;
import com.xh.shiro.shiroTen.entity.Permission;

/**
 * <p>User: Zhang Kaitao
 * <p>Date: 14-1-28
 * <p>Version: 1.0
 */
public class PermissionServiceImpl implements PermissionService {

    private PermissionDao permissionDao = new PermissionDaoImpl();

    public Permission createPermission(Permission permission) {
        return permissionDao.createPermission(permission);
    }

    public void deletePermission(Long permissionId) {
        permissionDao.deletePermission(permissionId);
    }
}
