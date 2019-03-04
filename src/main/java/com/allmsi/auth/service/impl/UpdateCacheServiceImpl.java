package com.allmsi.auth.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.allmsi.auth.config.SysInitManager;
import com.allmsi.auth.service.UpdateCacheService;
import com.allmsi.sys.model.protocol.SuccessProtocol;

@Service
public class UpdateCacheServiceImpl implements UpdateCacheService {
	@Resource
	private SysInitManager sysInitManager;

	@RequestMapping(value = "/update/cache", method = RequestMethod.GET)
	public Object updateCache() {
		sysInitManager.init();
		return new SuccessProtocol("","同步缓存");
	}
}
