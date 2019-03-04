package com.allmsi.auth.model.vo;

import com.allmsi.auth.model.po.Role;

public class UserRoleVo {

	private String roleId;

	private String roleName;
	
	public UserRoleVo(){
		
	}
	
	public UserRoleVo(Role role){
		if(role != null ){
			this.roleId = role.getId();
			this.roleName = role.getRoleName();
		}
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
}
