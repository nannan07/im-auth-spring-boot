package com.allmsi.auth.dao;

import java.util.List;

import com.allmsi.auth.model.po.UserRole;

public interface UserRoleMapper {

	int insertSelective(UserRole record);

	int insertBatch(List<UserRole> userRoleList);

	int deleteUser(String userid);

	int deleteRole(String roleid);

}