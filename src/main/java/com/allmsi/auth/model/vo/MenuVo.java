package com.allmsi.auth.model.vo;

import java.io.Serializable;
import java.util.Date;

import com.allmsi.auth.model.po.Menu;

public class MenuVo implements Serializable{

	private static final long serialVersionUID = 1L;

	private String id;

	private String label;

	private String path;

	private String icon;

	private String pId;

	private Integer sort;

	private Date cTime;
	
	private String value;

	public MenuVo() {

	}

	public MenuVo(Menu menu) {
		if (menu != null) {
			this.id = menu.getId();
			this.value = menu.getId();
			this.label = menu.getLabel();
			this.path = menu.getPath();
			this.icon = menu.getIcon();
			this.pId = menu.getpId();
			this.cTime = menu.getcTime();
			this.sort = menu.getSort();
		}
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getpId() {
		return pId;
	}

	public void setpId(String pId) {
		this.pId = pId;
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

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
