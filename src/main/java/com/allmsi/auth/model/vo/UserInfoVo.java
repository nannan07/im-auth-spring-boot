package com.allmsi.auth.model.vo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.allmsi.auth.model.po.User;

public class UserInfoVo{

	private String id;

	private String userName;

	private String phone;

	private String email;

	private Integer sex;

	private Integer sort;

	private Date cTime;

	// 用户部门
	private String deptId;

	private String deptName;
	
	private String udType;

	List<UserRoleVo> uRoleVoList = new ArrayList<UserRoleVo>();

	List<UserDeptVo> uDeptVoList = new ArrayList<UserDeptVo>();

	public UserInfoVo() {

	}

	public UserInfoVo(User user) {
		if (user != null) {
			this.id = user.getId();
			this.userName = user.getUserName();
			this.phone = user.getPhone();
			this.email = user.getEmail();
			this.sex = user.getSex();
			this.sort = user.getSort();
			this.cTime = user.getcTime();
			this.deptId = user.getDeptId();
			this.deptName = user.getDeptName();
		}
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public Date getcTime() {
		return cTime;
	}

	public void setcTime(Date cTime) {
		this.cTime = cTime;
	}

	public List<UserRoleVo> getuRoleVoList() {
		return uRoleVoList;
	}

	public void setuRoleVoList(List<UserRoleVo> uRoleVoList) {
		this.uRoleVoList = uRoleVoList;
	}

	public List<UserDeptVo> getuDeptVoList() {
		return uDeptVoList;
	}

	public void setuDeptVoList(List<UserDeptVo> uDeptVoList) {
		this.uDeptVoList = uDeptVoList;
	}

	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getUdType() {
		return udType;
	}

	public void setUdType(String udType) {
		this.udType = udType;
	}
}
