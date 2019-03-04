package com.allmsi.auth.model.vo;

import java.io.Serializable;
import java.util.Date;

import com.allmsi.auth.model.po.User;

public class UserVo implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private String id;
	
    private String loginName;
    
    private String userName;

    private String phone;

    private String email;

    private Integer sex;
    
    private Integer sort;
    
	private Date cTime;

    private String token;
    
    private String deptIds;
    
    private String deptNames;
    
    private String deptId;
    
    private String deptName;
    
    private String primaryDeptId;
    
    private String primaryDeptName;
    
    private String roleIds;
    
    private String roleNames;
  
    public UserVo() {
    	
    }
    
    public UserVo(User user) {
    	if (user != null) {
	    	this.id = user.getId();
	    	this.loginName = user.getLoginName();
	    	this.userName = user.getUserName();
	    	this.phone = user.getPhone();
	    	this.email = user.getEmail();
	    	this.sex = user.getSex();
	    	this.sort = user.getSort();
	    	this.cTime = user.getcTime();
    	}
    }

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
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

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getDeptIds() {
		return deptIds;
	}

	public void setDeptIds(String deptIds) {
		this.deptIds = deptIds;
	}

	public String getDeptNames() {
		return deptNames;
	}

	public void setDeptNames(String deptNames) {
		this.deptNames = deptNames;
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

	public String getPrimaryDeptId() {
		return primaryDeptId;
	}

	public void setPrimaryDeptId(String primaryDeptId) {
		this.primaryDeptId = primaryDeptId;
	}

	public String getPrimaryDeptName() {
		return primaryDeptName;
	}

	public void setPrimaryDeptName(String primaryDeptName) {
		this.primaryDeptName = primaryDeptName;
	}

	public String getRoleIds() {
		return roleIds;
	}

	public void setRoleIds(String roleIds) {
		this.roleIds = roleIds;
	}

	public String getRoleNames() {
		return roleNames;
	}

	public void setRoleNames(String roleNames) {
		this.roleNames = roleNames;
	}

}
