package com.allmsi.auth.model.po;

import java.util.Date;

import com.allmsi.auth.model.vo.DeptVo;

public class Dept implements Cloneable {

	private String id;

	private String deptCode;

	private String deptName;

	private String parentId;

	private String phone;

	private String corporation;

	private String description;

	private String address;

	private String bLicenceId;

	private Integer deptType;

	private Integer sort;

	private String cUserId;

	private String uUserId;

	private Date cTime;

	private Date uTime;

	private int del;

	private String udType;

	private String dingDeptId;
	
	private String systemId;
	
	public Dept() {

	}

	public Dept(DeptVo deptVo) {
		if (deptVo != null) {
			this.id = deptVo.getId();
			this.deptCode = deptVo.getDeptCode();
			this.deptName = deptVo.getDeptName();
			this.parentId = deptVo.getParentId();
			this.phone = deptVo.getPhone();
			this.corporation = deptVo.getCorporation();
			this.description = deptVo.getDescription();
			this.address = deptVo.getAddress();
			this.bLicenceId = deptVo.getbLicenceId();
			this.deptType = deptVo.getDeptType();
			this.sort = deptVo.getSort();
		}
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDeptCode() {
		return deptCode;
	}

	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getCorporation() {
		return corporation;
	}

	public void setCorporation(String corporation) {
		this.corporation = corporation;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getbLicenceId() {
		return bLicenceId;
	}

	public void setbLicenceId(String bLicenceId) {
		this.bLicenceId = bLicenceId;
	}

	public Integer getDeptType() {
		return deptType;
	}

	public void setDeptType(Integer deptType) {
		this.deptType = deptType;
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

	public String getcUserId() {
		return cUserId;
	}

	public void setcUserId(String cUserId) {
		this.cUserId = cUserId;
	}

	public String getuUserId() {
		return uUserId;
	}

	public void setuUserId(String uUserId) {
		this.uUserId = uUserId;
	}

	public String getUdType() {
		return udType;
	}

	public void setUdType(String udType) {
		this.udType = udType;
	}

	public String getDingDeptId() {
		return dingDeptId;
	}

	public void setDingDeptId(String dingDeptId) {
		this.dingDeptId = dingDeptId;
	}
	
	public String getSystemId() {
		return systemId;
	}

	public void setSystemId(String systemId) {
		this.systemId = systemId;
	}
	
	@Override
	public Object clone() {
		Dept dept = null;
		try {
			dept = (Dept) super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return dept;
	}
}