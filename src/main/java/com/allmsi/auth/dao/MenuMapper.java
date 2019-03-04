package com.allmsi.auth.dao;

import java.util.List;

import com.allmsi.auth.model.po.Menu;

public interface MenuMapper {
	
	/**
	 * 查询需要存入缓存中的菜单信息
	 * @return
	 */
	List<Menu> selectMenuListForCacheAdd();

}