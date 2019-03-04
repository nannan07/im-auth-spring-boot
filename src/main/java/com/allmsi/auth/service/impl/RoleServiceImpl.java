package com.allmsi.auth.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.allmsi.auth.dao.RoleMapper;
import com.allmsi.auth.model.po.Role;
import com.allmsi.auth.model.vo.RoleVo;
import com.allmsi.auth.model.vo.UserRoleVo;
import com.allmsi.auth.service.RoleService;
import com.allmsi.sys.util.StrUtil;

@Service
public class RoleServiceImpl implements RoleService {

	@Resource
	private RoleMapper roleDao;

	@Override
	public List<UserRoleVo> listUserRole(String userId) {
		List<UserRoleVo> userRoleVoList = new ArrayList<UserRoleVo>();
		List<Role> roleList = roleDao.listRoleByUserId(userId);
		for (Role role : roleList) {
			userRoleVoList.add(new UserRoleVo(role));
		}
		return userRoleVoList;
	}
	

	@Override
	public List<Role> listRoleByMap(String userId, String sysId) {
		Map<String, Object> map = new HashMap<>();
		if (StrUtil.notEmpty(sysId)) {
			map.put("sysId", sysId);
		}
		map.put("userId", userId);
		return roleDao.listRoleByMap(map);
	}

	@Override
	public List<RoleVo> listRoles(List<String> roleIds) {
		List<RoleVo> list = new ArrayList<RoleVo>();
		if (roleIds != null && roleIds.size() > 0) {
			List<Role> rLIst = roleDao.listRoles(roleIds);
			for (Role role : rLIst) {
				list.add(new RoleVo(role));
			}
		}
		return list;
	}

	@Override
	public RoleVo selectByPrimaryKey(String id) {
		return new RoleVo(roleDao.selectByPrimaryKey(id));
	}

}
