package com.allmsi.auth.dao;

import java.util.List;
import java.util.Map;

import com.allmsi.auth.model.po.User;

public interface UserMapper {

	User selectByPrimaryKey(String id);

	List<User> listUser(User user);

	List<User> listUserByDeptId(String deptId);

	List<User> listUserByRoleId(String roleId);

	List<User> listUsers(List<String> userIds);

	// 对外提供登录验证
	User verifyLogin(User user);

	List<User> listDeptUsers(List<String> deptId);

	List<User> listByRolesAndDepts(Map<String, String> map);
}