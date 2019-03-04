package com.allmsi.auth.service;

import java.util.List;

import com.allmsi.auth.model.po.Role;
import com.allmsi.auth.model.vo.RoleVo;
import com.allmsi.auth.model.vo.UserRoleVo;

/**
 * 角色信息操作
 * 
 * @author sunnannan
 *
 */
public interface RoleService {

	List<UserRoleVo> listUserRole(String userId);
	
	List<Role> listRoleByMap(String userId,String sysId);
	
	List<RoleVo> listRoles(List<String> roleIds);
	
	RoleVo selectByPrimaryKey(String id);

}
