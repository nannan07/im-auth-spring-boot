package com.allmsi.auth.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.allmsi.auth.config.CacheInstenceSubclass;
import com.allmsi.auth.dao.RoleMapper;
import com.allmsi.auth.dao.SystemIdMapper;
import com.allmsi.auth.dao.UserMapper;
import com.allmsi.auth.model.po.Role;
import com.allmsi.auth.model.po.SystemId;
import com.allmsi.auth.model.po.User;
import com.allmsi.auth.model.vo.DeptVo;
import com.allmsi.auth.model.vo.MenuTree;
import com.allmsi.auth.model.vo.MenuVo;
import com.allmsi.auth.model.vo.UserRoleVo;
import com.allmsi.auth.model.vo.UserVo;
import com.allmsi.auth.service.DeptService;
import com.allmsi.auth.service.LoginService;
import com.allmsi.auth.service.RoleService;
import com.allmsi.cache.CacheKeyPrefix;
import com.allmsi.sys.model.TreeDataBean;
import com.allmsi.sys.util.MD5Util;
import com.allmsi.sys.util.StrUtil;
import com.allmsi.sys.util.TreeUtil;
import com.allmsi.sys.util.UUIDUtil;

@Service
public class LoginServiceImpl implements LoginService {
	
	@Value("${im.session.out:1800}")
	private Integer imSessionOut;

	@Value("${im.menu.root.id.value:0}")
	private String imMenuRoot;

	@Resource
	private CacheInstenceSubclass cacheInstence;

	@Resource
	private UserMapper userDao;

	@Resource
	private RoleMapper roleDao;

	@Resource
	private SystemIdMapper systemDao;

	@Resource
	private DeptService deptService;

	@Resource
	private RoleService roleService;

	private final String CEO_USER_ID = "01302000167471";

	private final String CEO_USER_DEPT = "CEO";
	
	@Override
	public Map<String, Object> getLoginInfo(String sToken, User user) {
		if(user == null) {
			return null;
		}
		UserVo apiUser = new UserVo(user);
		if (apiUser == null || StrUtil.isEmpty(apiUser.getId())) {
			return null;
		} else {
			apiUser.setToken(UUIDUtil.getUUID());
			String userId = apiUser.getId();
			List<DeptVo> deptList = deptService.listParentDeptVo(userId);
			String deptIds = "";
			String deptNames = "";
			for (DeptVo deptVo : deptList) {
				deptIds = deptIds + deptVo.getId() + ",";
				deptNames = deptNames + deptVo.getDeptName() + ",";
			}
			if (deptIds.endsWith(",")) {
				deptIds = deptIds.substring(0, deptIds.length() - 1);
			}
			if (deptNames.endsWith(",")) {
				deptNames = deptNames.substring(0, deptNames.length() - 1);
			}

			if (deptList != null && deptList.size() > 0) {
				if (deptList.size() > 1) {
					DeptVo deptVo = deptList.get(deptList.size() - 1);
					apiUser.setDeptId(deptVo.getId());
					apiUser.setDeptName(deptVo.getDeptName());
					DeptVo primaryDeptVo = deptList.get(1);
					apiUser.setPrimaryDeptId(primaryDeptVo.getId());
					apiUser.setPrimaryDeptName(primaryDeptVo.getDeptName());
				} else if (deptList.size() == 1) {
					DeptVo deptVo = deptList.get(0);
					if (CEO_USER_ID.equals(userId)) {
						apiUser.setDeptId(deptVo.getId());
						apiUser.setDeptName(CEO_USER_DEPT);
						apiUser.setPrimaryDeptId(deptVo.getId());
						apiUser.setPrimaryDeptName(CEO_USER_DEPT);
					} else {
						apiUser.setDeptId(deptVo.getId());
						apiUser.setDeptName(deptVo.getDeptName());
						apiUser.setPrimaryDeptId(deptVo.getId());
						apiUser.setPrimaryDeptName(deptVo.getDeptName());
					}
				}
			}

			apiUser.setDeptIds(deptIds);
			apiUser.setDeptNames(deptNames);
			List<UserRoleVo> roles = roleService.listUserRole(userId);
			String roleIds = "";
			String roleNames = "";
			for (UserRoleVo userRoleVo : roles) {
				roleIds = roleIds + userRoleVo.getRoleId() + ",";
				roleNames = roleNames + userRoleVo.getRoleName() + ",";
			}
			if (roleIds.endsWith(",")) {
				roleIds = roleIds.substring(0, roleIds.length() - 1);
			}
			if (roleNames.endsWith(",")) {
				roleNames = roleNames.substring(0, roleNames.length() - 1);
			}
			apiUser.setRoleIds(roleIds);
			apiUser.setRoleNames(roleNames);
			cacheInstence.setObject(CacheKeyPrefix.TOKEN.getValue() + apiUser.getToken(), apiUser, imSessionOut);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("userInfo", apiUser);
			map.put("menu", selectMenuTreeByUserId(sToken, apiUser.getId()));
			return map;
		}
	}

	/**
	 * sToken 三方系统登录的授权码，暂时为systemId
	 */
	@Override
	public Object login(String sToken, String loginName, String password) {
		User user = new User();
		user.setLoginName(loginName);
		user.setPassword(MD5Util.encode(password));
		user = userDao.verifyLogin(user);
		return getLoginInfo(sToken, user);
	}

	/**
	 * 用户可以访问的菜单树
	 */
	public TreeDataBean selectMenuTreeByUserId(String systemId, String userId) {
		List<MenuTree> menuTreeList = getRemovalMenuVoList(systemId, userId);
		String menuRoot = "0";
		if ("mis".equals(systemId)) {
			menuRoot = "mis";
		}
		return new TreeDataBean(TreeUtil.getTreeWithOutRootNood(menuRoot, menuTreeList));
	}

	/**
	 * 排序后的菜单信息集合
	 * 
	 * @param userId
	 * @return
	 */
	private List<MenuTree> getRemovalMenuVoList(String systemId, String userId) {
		List<MenuTree> menuTreeList = new ArrayList<MenuTree>();
		Map<String, MenuVo> map = getMenuIdMenuVoMap(systemId, userId);
		Collection<MenuVo> value = map.values();
		for (MenuVo webMenuVo : value) {
			if (map.containsKey(webMenuVo.getpId()) || StrUtil.isEmpty(webMenuVo.getpId())) {
				menuTreeList.add(new MenuTree(webMenuVo));
			}
		}
		Collections.sort(menuTreeList, new Comparator<MenuTree>() {
			public int compare(MenuTree arg0, MenuTree arg1) {
				int hits0 = arg0.getSort();
				int hits1 = arg1.getSort();
				if (hits0 < hits1) {
					return -1;
				}
				return 1;
			}
		});
		return menuTreeList;
	}

	@SuppressWarnings("unchecked")
	private Map<String, MenuVo> getMenuIdMenuVoMap(String systemId, String userId) {
		List<MenuVo> webMenuVoList = new ArrayList<MenuVo>();
		if (StrUtil.notEmpty(userId)) {
			List<String> roleIds = new ArrayList<String>();
			Map<String, Object> map = new HashMap<>();
			if (StrUtil.notEmpty(systemId)) {
				map.put("sysId", systemId);
			}
			map.put("userId", userId);
			List<Role> roleList = roleDao.listRoleByMap(map);
			for (Role role : roleList) {
				roleIds.add(role.getId());
			}
			if (roleIds != null && roleIds.size() > 0) {
				for (String roleId : roleIds) {
					Object object = cacheInstence.getObject(CacheKeyPrefix.MENU.getValue() + roleId);
					if (object != null) {
						webMenuVoList.addAll((List<MenuVo>) object);
					}
				}
			}
		}

		Map<String, MenuVo> map = new HashMap<String, MenuVo>();
		if (webMenuVoList != null && webMenuVoList.size() > 0) {
			for (MenuVo webMenuVo : webMenuVoList) {
				String menuId = webMenuVo.getId();
				if (!map.containsKey(menuId)) {
					map.put(menuId, webMenuVo);
				}
			}
		}
		return map;
	}

	public Object logout(String token) {
		return cacheInstence.del(CacheKeyPrefix.TOKEN.getValue() + token);
	}

	@Override
	public Object logout(String sysId, String uToken) {
		if (StrUtil.notEmpty(uToken) && StrUtil.notEmpty(sysId)) {
			SystemId systemId = systemDao.selectByPrimaryKey(sysId);
			if (systemId != null) {
				return cacheInstence.del(CacheKeyPrefix.TOKEN.getValue() + uToken);
			}
		}
		return false;
	}
}
