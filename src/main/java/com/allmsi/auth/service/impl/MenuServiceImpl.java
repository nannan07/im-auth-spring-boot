package com.allmsi.auth.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.allmsi.auth.config.CacheInstenceSubclass;
import com.allmsi.auth.dao.MenuMapper;
import com.allmsi.auth.model.po.Menu;
import com.allmsi.auth.model.vo.MenuVo;
import com.allmsi.auth.service.MenuService;
import com.allmsi.cache.CacheKeyPrefix;

@Service
public class MenuServiceImpl implements MenuService {

	@Resource
	private CacheInstenceSubclass cacheInstence;

	@Resource
	private MenuMapper menuDao;

	public void setMenuCache() {
		Map<String, List<MenuVo>> map = getRoleMenuMapForCache();
		for (Entry<String, List<MenuVo>> entry : map.entrySet()) {
			List<MenuVo> valueList = entry.getValue();
			if (valueList != null && valueList.size() > 0) {
				String key = entry.getKey();
				cacheInstence.setObject(CacheKeyPrefix.MENU.getValue() + key, valueList);
			}
		}
	}

	/**
	 * 查询角色对应的菜单信息
	 * 
	 * @return
	 */
	private Map<String, List<MenuVo>> getRoleMenuMapForCache() {
		List<Menu> menuList = new ArrayList<Menu>();
		menuList.addAll(menuDao.selectMenuListForCacheAdd());
		Map<String, List<MenuVo>> map = new HashMap<String, List<MenuVo>>();
		for (Menu webMenu : menuList) {
			String roleId = webMenu.getAuthDealId();
			if (!map.containsKey(roleId)) {
				List<MenuVo> list = new ArrayList<MenuVo>();
				list.add(new MenuVo(webMenu));
				map.put(roleId, list);
			} else {
				List<MenuVo> list = new ArrayList<MenuVo>();
				list.addAll(map.get(roleId));
				list.add(new MenuVo(webMenu));
				map.put(roleId, list);
			}
		}
		return map;
	}

}