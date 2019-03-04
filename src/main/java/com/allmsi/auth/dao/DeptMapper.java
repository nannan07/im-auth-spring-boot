package com.allmsi.auth.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.allmsi.auth.model.po.Dept;

@Mapper
public interface DeptMapper {

	List<Dept> selectDeptTree(String id);

	Dept selectByPrimaryKey(String id);

	List<Dept> listUserDeptVo(String userId);

	List<Dept> listParentDeptTree(String deptId);

	List<Dept> listDeptList(List<String> deptIds);

	List<Dept> selectDeptByPId(String pid);

}