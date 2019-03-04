package com.allmsi.auth.service;

import java.util.List;

import com.allmsi.auth.model.vo.UserInfoVo;
import com.allmsi.auth.model.vo.UserVo;
import com.allmsi.sys.model.ListBean;

/**
 * 用户信息操作
 * 
 * @author sunnannan
 *
 */
public interface UserService {

	/**
	 * 通过token获取用户信息
	 * 
	 * @param token
	 * @return
	 */
	UserVo getUserInfoBytoken(String token);

	/**
	 * 批量查询用户
	 * 
	 * @param userIds
	 * @return
	 */
	List<UserInfoVo> listUserInfo(List<String> userIds);

	/**
	 * 获取部门下的用户
	 * 
	 * @param deptId
	 * @param page
	 * @param pageSize
	 * @return
	 */
	ListBean listUserInfoByDeptId(String deptId, Integer page, Integer pageSize);

	/**
	 * 角色下的用户列表
	 * 
	 * @param roleId
	 * @param page
	 * @param pageSize
	 * @return
	 */
	ListBean listUserInfoByRoleId(String roleId, Integer page, Integer pageSize);

	UserInfoVo selectByPrimaryKey(String id);

	List<UserInfoVo> listDeptUsersByUserId(String userId);

	List<UserInfoVo> listParentsDeptUsersByUserId(String userId);
	
	ListBean listrPrimarySectorDeptAllUser(String deptId);

	ListBean getAllUser(Integer page, Integer pageSize);

	/**
	 * 查询用户的角色集合
	 * 
	 * @param id
	 * @return
	 */
	List<String> listRoleIdForUserId(String id);

	ListBean listUserByRolesAndDepts(String roleIds, String deptIds);

}
