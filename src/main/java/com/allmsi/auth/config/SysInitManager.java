package com.allmsi.auth.config;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.allmsi.auth.service.MenuService;
import com.allmsi.auth.service.ResourcesService;

/**
 * 系统启动时执行的操作
 * @author sunnannan
 *
 */
@Component
public class SysInitManager {

	@Resource
	private MenuService menuService;
	
	@Resource
	private ResourcesService resourcesService;

	/**
	 * 构造方法执行后，初始化，
	 */
	@PostConstruct
	public void init() {
		menuService.setMenuCache();
		resourcesService.setResCache();
	}

}
