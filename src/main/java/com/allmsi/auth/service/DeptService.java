package com.allmsi.auth.service;

import java.util.List;

import com.allmsi.auth.model.TreeDataBean;
import com.allmsi.auth.model.vo.DeptVo;
import com.allmsi.auth.model.vo.UserDeptVo;

/**
 * 部门信息操作
 * @author sunnannan
 *
 */
public interface DeptService {
	
	/**
	 * 查询用户所在部门信息
	 * @param userId
	 * @return
	 */
	List<UserDeptVo> listUserDeptVo(String userId);

	DeptVo selectByPrimaryKey(String id);

	List<DeptVo> listDeptList(List<String> deptIds);

	List<DeptVo> listParentDeptVo(String userId);

	List<DeptVo> listSubordinateDeptVo(String userId);
	
	TreeDataBean selectDeptTree(String id);
	
	List<DeptVo> listDeptTree(String id);

}
