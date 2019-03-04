package com.allmsi.auth.model.vo;

import java.util.Date;

import com.allmsi.auth.model.po.Role;

public class RoleVo {

	private String id;

	private String roleName;
	
	private Date cTime;

	public RoleVo() {

	}

	public RoleVo(String id, String roleName) {
		this.id = id;
		this.roleName = roleName;
	}

	public RoleVo(Role role) {
		if (role != null) {
			this.id = role.getId();
			this.roleName = role.getRoleName();
			this.cTime = role.getcTime();
		}
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public Date getcTime() {
		return cTime;
	}

	public void setcTime(Date cTime) {
		this.cTime = cTime;
	}
}
