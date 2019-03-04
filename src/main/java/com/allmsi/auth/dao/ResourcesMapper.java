package com.allmsi.auth.dao;

import java.util.List;

import com.allmsi.auth.model.po.Resources;

public interface ResourcesMapper {
	
	/**
	 * 角色对应的缓存
	 * @return
	 */
	List<Resources> selectResListForCacheAdd();

}