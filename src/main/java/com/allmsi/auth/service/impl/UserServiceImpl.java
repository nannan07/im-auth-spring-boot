package com.allmsi.auth.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.allmsi.auth.config.CacheInstenceSubclass;
import com.allmsi.auth.dao.DeptMapper;
import com.allmsi.auth.dao.UserMapper;
import com.allmsi.auth.model.po.Dept;
import com.allmsi.auth.model.po.User;
import com.allmsi.auth.model.vo.DeptVo;
import com.allmsi.auth.model.vo.UserDeptVo;
import com.allmsi.auth.model.vo.UserInfoVo;
import com.allmsi.auth.model.vo.UserRoleVo;
import com.allmsi.auth.model.vo.UserVo;
import com.allmsi.auth.service.DeptService;
import com.allmsi.auth.service.RoleService;
import com.allmsi.auth.service.UserService;
import com.allmsi.cache.CacheKeyPrefix;
import com.allmsi.sys.model.ListBean;
import com.allmsi.sys.util.StrUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * 用户信息处理类
 * 
 * @author sunnannan
 *
 */
@Service
public class UserServiceImpl implements UserService {

	@Resource
	private CacheInstenceSubclass cacheInstence;

	@Resource
	private RoleService roleService;

	@Resource
	private DeptService deptService;

	@Resource
	private UserMapper userDao;

	@Resource
	private DeptMapper deptDao;

	private final String CEO_USER_ID = "01302000167471";

	private final String CEO_USER_DEPT = "CEO";

	@Value("${im.user.default.psw:1234}")
	private String imUserDefaultPsw;

	@Override
	public UserVo getUserInfoBytoken(String token) {
		if (StrUtil.isEmpty(token)) {
			return null;
		}
		return (UserVo) cacheInstence.getCache().getObject(CacheKeyPrefix.TOKEN.getValue() + token);
	}

	@Override
	public List<UserInfoVo> listUserInfo(List<String> userIds) {
		List<UserInfoVo> list = new ArrayList<UserInfoVo>();
		if (userIds != null && userIds.size() > 0) {
			List<User> uLIst = userDao.listUsers(userIds);
			for (User user : uLIst) {
				list.add(new UserInfoVo(user));
			}
		}
		return list;
	}

	@Override
	public ListBean listUserInfoByDeptId(String deptId, Integer page, Integer pageSize) {
		if (page == null) {
			page = 1;
		}
		if (pageSize == null) {
			pageSize = 10;
		}
		PageHelper.startPage(page, pageSize);
		List<User> userList = userDao.listUserByDeptId(deptId);
		PageInfo<User> pageInfo = new PageInfo<User>(userList);
		int total = (int) pageInfo.getTotal();
		List<UserInfoVo> userVoList = new ArrayList<UserInfoVo>();
		for (User user : userList) {
			userVoList.add(new UserInfoVo(user));
		}
		return new ListBean(total, userVoList);
	}

	@Override
	public ListBean listUserInfoByRoleId(String roleId, Integer page, Integer pageSize) {
		if (page == null) {
			page = 1;
		}
		if (pageSize == null) {
			pageSize = 10;
		}
		PageHelper.startPage(page, pageSize);
		List<User> userList = userDao.listUserByRoleId(roleId);
		PageInfo<User> pageInfo = new PageInfo<User>(userList);
		int total = (int) pageInfo.getTotal();
		List<UserInfoVo> userVoList = new ArrayList<UserInfoVo>();
		for (User user : userList) {
			userVoList.add(new UserInfoVo(user));
		}
		return new ListBean(total, userVoList);
	}

	/**
	 * 用户详细信息，包含用户的部门信息和角色信息
	 */
	@Override
	public UserInfoVo selectByPrimaryKey(String id) {
		User user = userDao.selectByPrimaryKey(id);
		UserInfoVo userInfo = new UserInfoVo(user);
		List<DeptVo> deptList = deptService.listParentDeptVo(id);
		if (deptList != null && deptList.size() > 0) {
			if (deptList.size() > 1) {
				DeptVo primaryDeptVo = deptList.get(1);
				userInfo.setDeptId(primaryDeptVo.getId());
				userInfo.setDeptName(primaryDeptVo.getDeptName());
			} else if (deptList.size() == 1) {
				DeptVo deptVo = deptList.get(0);
				if (CEO_USER_ID.equals(id)) {
					userInfo.setDeptId(deptVo.getId());
					userInfo.setDeptName(CEO_USER_DEPT);
				} else {
					userInfo.setDeptId(deptVo.getId());
					userInfo.setDeptName(deptVo.getDeptName());
				}
			}
		}
		List<UserDeptVo> uDeptVoList = deptService.listUserDeptVo(id);
		userInfo.setuDeptVoList(uDeptVoList);
		userInfo.setuRoleVoList(roleService.listUserRole(id));
		return userInfo;
	}

	@Override
	public List<UserInfoVo> listDeptUsersByUserId(String userId) {
		List<UserInfoVo> list = new ArrayList<UserInfoVo>();
		if (StrUtil.isEmpty(userId)) {
			return list;
		}
		List<String> deptId = new ArrayList<String>();
		List<DeptVo> deptList = deptService.listParentDeptVo(userId);// 向上的部门树
		String deptIdIndex = "";
		if (deptList != null && 4 == deptList.size()) {
			deptIdIndex = deptList.get(3).getId();
			deptId.add(deptIdIndex);
		} else {
			// 用户所在部门
			List<UserDeptVo> udList = deptService.listUserDeptVo(userId);
			for (UserDeptVo userDeptVo : udList) {
				deptId.add(userDeptVo.getDeptId());
			}
		}
		// 查询部门成员
		if (deptId != null && deptId.size() > 0) {
			List<User> ulist = userDao.listDeptUsers(deptId);
			for (User user : ulist) {
				list.add(new UserInfoVo(user));
			}
		}

		return list;
	}

	@Override
	public List<UserInfoVo> listParentsDeptUsersByUserId(String userId) {
		List<UserInfoVo> list = new ArrayList<UserInfoVo>();
		if (StrUtil.isEmpty(userId)) {
			return list;
		}
		List<String> depIds = new ArrayList<String>();
		List<Dept> deptList = deptDao.listUserDeptVo(userId);// 查询用户所在的部门
		if (deptList != null && deptList.size() > 0) {
			for (Dept dept : deptList) {
				List<Dept> dList = deptDao.listParentDeptTree(dept.getId());// 查询用户所在的部门向上查询
				if (dList != null && dList.size() > 0) {
					int size = dList.size();
					if (1 == size) {// 吴延
						depIds.add(dList.get(0).getId());
					}
					if (2 == size) {
						depIds.add(dList.get(1).getId());
					}
					if (2 < size) {
						for (int i = 1; i < size; i++) {
							if (!depIds.contains(dList.get(i).getId())) {
								depIds.add(dList.get(i).getId());
							}
						}
					}
				}
			}
			if (depIds != null && depIds.size() > 0) {
				List<User> ulist = userDao.listDeptUsers(depIds);
				for (User tempUser : ulist) {
					list.add(new UserInfoVo(tempUser));
				}
			}
		}
		return list;
	}

	@Override
	public ListBean listrPrimarySectorDeptAllUser(String deptId) {
		if (StrUtil.isEmpty(deptId)) {
			return null;
		}
		List<DeptVo> dList = deptService.listDeptTree(deptId);
		List<String> deptIdList = new ArrayList<String>();
		if (dList != null && dList.size() > 0) {
			for (DeptVo deptVo : dList) {
				deptIdList.add(deptVo.getId());
			}
		}
		if (deptIdList != null && deptIdList.size() > 0) {
			return listUserByRolesAndDepts("", String.join(",", deptIdList));
		}
		return null;
	}

	@Override
	public ListBean getAllUser(Integer page, Integer pageSize) {
		User user = new User();
		if (page == null) {
			page = 1;
		}
		if (pageSize == null) {
			pageSize = 10;
		}
		PageHelper.startPage(page, pageSize);

		List<User> userList = userDao.listUser(user);
		PageInfo<User> pageInfo = new PageInfo<User>(userList);
		int total = (int) pageInfo.getTotal();
		List<UserInfoVo> userVoList = new ArrayList<UserInfoVo>();
		for (User tempUser : userList) {
			userVoList.add(new UserInfoVo(tempUser));
		}
		return new ListBean(total, userVoList);
	}

	@Override
	public List<String> listRoleIdForUserId(String id) {
		List<String> ids = new ArrayList<String>();
		List<UserRoleVo> ur = roleService.listUserRole(id);
		for (UserRoleVo userRoleVo : ur) {
			ids.add(userRoleVo.getRoleId());
		}
		return ids;
	}

	@Override
	public ListBean listUserByRolesAndDepts(String roleIds, String deptIds) {
		Map<String, String> map = new HashMap<String, String>();
		if (StrUtil.notEmpty(roleIds)) {
			map.put("roleIds", roleIds);
		}
		if (StrUtil.notEmpty(deptIds)) {
			map.put("deptIds", deptIds);
		}
		List<User> list = userDao.listByRolesAndDepts(map);
		if (list != null && list.size() > 0) {
			List<UserInfoVo> userVoList = new ArrayList<UserInfoVo>();
			for (User user : list) {
				userVoList.add(new UserInfoVo(user));
			}
			return new ListBean(userVoList.size(), userVoList);
		}
		return null;
	}

}
