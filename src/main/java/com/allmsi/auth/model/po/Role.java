package com.allmsi.auth.model.po;

import java.util.Date;

import com.allmsi.auth.model.vo.RoleVo;

public class Role implements Cloneable {

	private String id;

	private String roleName;

	private String cUserId;

	private Date cTime;

	private String uUserId;

	private Date uTime;

	private int del;

	private String systemId;

	public Role() {

	}

	public Role(RoleVo roleVo) {
		if (roleVo != null) {
			this.id = roleVo.getId();
			this.roleName = roleVo.getRoleName();
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

	public String getcUserId() {
		return cUserId;
	}

	public void setcUserId(String cUserId) {
		this.cUserId = cUserId;
	}

	public Date getcTime() {
		return cTime;
	}

	public void setcTime(Date cTime) {
		this.cTime = cTime;
	}

	public String getuUserId() {
		return uUserId;
	}

	public void setuUserId(String uUserId) {
		this.uUserId = uUserId;
	}

	public Date getuTime() {
		return uTime;
	}

	public void setuTime(Date uTime) {
		this.uTime = uTime;
	}

	public int getDel() {
		return del;
	}

	public void setDel(int del) {
		this.del = del;
	}

	public String getSystemId() {
		return systemId;
	}

	public void setSystemId(String systemId) {
		this.systemId = systemId;
	}
}