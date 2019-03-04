package com.allmsi.auth.model.vo;

import java.util.Date;

import com.allmsi.auth.model.po.Resources;

public class ResVo {

	private String id;

    private String resUrl;

    private String name;

    private Integer flag;
    
    private Date cTime;
    
	public ResVo() {

	}

	public ResVo(Resources resources) {
		if (resources != null) {
			this.id = resources.getId();
			this.resUrl = resources.getResUrl();
			this.name = resources.getName();
			this.flag = resources.getFlag();
			this.cTime = resources.getcTime();
		}
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getResUrl() {
		return resUrl;
	}

	public void setResUrl(String resUrl) {
		this.resUrl = resUrl;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Integer getFlag() {
		return flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}

	public Date getcTime() {
		return cTime;
	}

	public void setcTime(Date cTime) {
		this.cTime = cTime;
	}
}
