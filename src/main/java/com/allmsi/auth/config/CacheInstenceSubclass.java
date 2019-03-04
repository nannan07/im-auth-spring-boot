package com.allmsi.auth.config;


import org.springframework.stereotype.Component;

import com.allmsi.auth.model.vo.UserVo;
import com.allmsi.cache.CacheFactory;
import com.allmsi.cache.CacheInstence;
import com.allmsi.cache.CacheKeyPrefix;

@Component
public class CacheInstenceSubclass  extends CacheInstence{

	public CacheInstenceSubclass(CacheFactory cacheFactory) {
		super(cacheFactory);
	}

	public UserVo getUser(String token) {
		UserVo userVo = (UserVo) getCache().getObject(CacheKeyPrefix.TOKEN.getValue() + token);
		return userVo;
	}

	public String getUserId(String token) {
		return getUser(token).getId();
	}
}
