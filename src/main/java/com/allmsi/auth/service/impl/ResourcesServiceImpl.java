package com.allmsi.auth.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.allmsi.auth.config.CacheInstenceSubclass;
import com.allmsi.auth.dao.ResourcesMapper;
import com.allmsi.auth.dao.RoleMapper;
import com.allmsi.auth.model.po.Resources;
import com.allmsi.auth.service.ResourcesService;
import com.allmsi.auth.service.UserService;
import com.allmsi.cache.CacheKeyPrefix;

@Service
public class ResourcesServiceImpl implements ResourcesService {

	@Resource
	private ResourcesMapper resourcesDao;
	
	@Resource
	private ResourcesMapper resDao;

	@Resource
	private RoleMapper roleDao;

	@Resource
	private UserService userService;

	@Resource
	private CacheInstenceSubclass cacheInstence;

	@Override
	public void setResCache() {
		Map<String, List<String>> map = getRoleResMapForCache();
		for (Entry<String, List<String>> entry : map.entrySet()) {
			List<String> valueList = entry.getValue();
			if (valueList != null && valueList.size() > 0) {
				String key = entry.getKey();
				Set<String> value = new HashSet<String>();
				value.addAll(valueList);
				cacheInstence.setObject(CacheKeyPrefix.RES.getValue() + key, value);
			}
		}
	}

	private Map<String, List<String>> getRoleResMapForCache() {
		List<Resources> resList = new ArrayList<Resources>();
		resList.addAll(resDao.selectResListForCacheAdd());
		Map<String, List<String>> map = new HashMap<String, List<String>>();
		for (Resources res : resList) {
			String roleId = res.getAuthDealId();
			if (!map.containsKey(roleId)) {
				List<String> list = new ArrayList<String>();
				list.add(res.getResUrl());
				map.put(roleId, list);
			} else {
				List<String> list = new ArrayList<String>();
				list.addAll(map.get(roleId));
				list.add(res.getResUrl());
				map.put(roleId, list);
			}
		}
		return map;
	}
	@SuppressWarnings("unchecked")
	@Override
	public String isVisit(String token, String resUrl) {
		String userId = cacheInstence.getUserId(token);
		Set<String> values = new HashSet<String>();
		List<String> roleIds = new ArrayList<String>();
		roleIds.addAll(userService.listRoleIdForUserId(userId));
		if (roleIds != null && roleIds.size() > 0) {
			for (String roleId : roleIds) {
				if (cacheInstence.getObject((CacheKeyPrefix.RES.getValue() + roleId)) != null) {
					values.addAll((Collection<? extends String>) cacheInstence
							.getObject(CacheKeyPrefix.RES.getValue() + roleId));
				}
			}
		}
		return (values.contains(resUrl)) ? resUrl : "";
	}
}
