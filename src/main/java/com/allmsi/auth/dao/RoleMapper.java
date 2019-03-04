package com.allmsi.auth.dao;

import java.util.List;
import java.util.Map;

import com.allmsi.auth.model.po.Role;

public interface RoleMapper {
	
	Role selectByPrimaryKey(String id);

	List<Role> listRoleByUserId(String userId);

	List<Role> listRoleByMap(Map<String, Object> map);

	List<Role> listRoles(List<String> roleIds);

}