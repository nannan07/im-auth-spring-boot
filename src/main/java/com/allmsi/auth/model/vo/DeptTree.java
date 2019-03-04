package com.allmsi.auth.model.vo;

import java.io.Serializable;

import com.allmsi.auth.model.po.Dept;
import com.allmsi.sys.util.BaseTree;

public class DeptTree extends BaseTree<DeptTree> implements Serializable {

	private static final long serialVersionUID = 1L;

	private String label;

	public DeptTree() {

	}

	public DeptTree(Dept dept) {
		if (dept != null) {
			setId(dept.getId());
			setpId(dept.getParentId());
			this.label = dept.getDeptName();
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

}
