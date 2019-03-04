package com.allmsi.auth.model.vo;

import com.allmsi.auth.model.po.Dept;

public class UserDeptVo {

	private String deptId;

	private String udType;

	private String deptName;
	
	private Integer deptType;

	public UserDeptVo() {

	}

	public UserDeptVo(Dept dept) {
		if (dept != null) {
			this.deptId = dept.getId();
			this.udType = dept.getUdType();
			this.deptName = dept.getDeptName();
			this.deptType = dept.getDeptType();
		}
	}

	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public String getUdType() {
		return udType;
	}

	public void setUdType(String udType) {
		this.udType = udType;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public Integer getDeptType() {
		return deptType;
	}

	public void setDeptType(Integer deptType) {
		this.deptType = deptType;
	}
}