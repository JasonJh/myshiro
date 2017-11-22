package com.xh.dao;

import com.xh.entity.Role;

public interface RoleDao {
    public Role createRole(Role role);
    public void deleteRole(Long roleId);

    public void correlationPermissions(Long roleId,Long... permissionId);
    public void uncorrelationPermissions(Long roleId,Long... permissionId);
}
