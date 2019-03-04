package com.allmsi.auth.model.vo;

import java.io.Serializable;

import com.allmsi.auth.model.po.Menu;
import com.allmsi.sys.util.BaseTree;

public class MenuTree extends BaseTree<MenuTree> implements Serializable {

	private static final long serialVersionUID = 1L;

	private String label;

	private String path;

	private String icon;

	private Integer sort;

	private String value;

	public MenuTree() {

	}

	public MenuTree(Menu menu) {
		if (menu != null) {
			this.setId(menu.getId());
			this.setpId(menu.getpId());
			this.value = menu.getId();
			this.label = menu.getLabel();
			this.path = menu.getPath();
			this.icon = menu.getIcon();
			this.sort = menu.getSort();
		}
	}

	public MenuTree(MenuVo menuVo) {
		if (menuVo != null) {
			this.setId(menuVo.getId());
			this.setpId(menuVo.getpId());
			this.label = menuVo.getLabel();
			this.path = menuVo.getPath();
			this.icon = menuVo.getIcon();
			this.sort = menuVo.getSort();
		}
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
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

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}