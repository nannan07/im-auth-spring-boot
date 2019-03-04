package com.allmsi.auth.config;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.allmsi.auth.service.ResourcesService;
import com.allmsi.cache.Cache;
import com.allmsi.cache.CacheKeyPrefix;
import com.allmsi.sys.model.protocol.WarnProtocol;
import com.allmsi.sys.util.StrUtil;
import com.google.gson.Gson;

/**
 * Login connector 拦截非法访问的链接和不存在的用户访问请求
 * 
 * @author sunnannan
 *
 */
public class LoginInterceptor extends HandlerInterceptorAdapter {

	private final CacheInstenceSubclass cacheInstenceSubclass;

	private final ResourcesService resourcesService;

	private final String DOWNLOAD_FILE_URL = "/download";

	private final int time;

	public LoginInterceptor(CacheInstenceSubclass cacheInstenceSubclass, ResourcesService resourcesService, int time) {
		this.cacheInstenceSubclass = cacheInstenceSubclass;
		this.resourcesService = resourcesService;
		this.time = time;
	}

	/**
	 * 获取缓存对象
	 * 
	 * @return
	 */
	public Cache getCache() {
		return cacheInstenceSubclass.getCache();
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String result = new Gson().toJson(new WarnProtocol(0));
		String url = request.getServletPath();
		String token = "";
		if (StrUtil.notEmpty(DOWNLOAD_FILE_URL, url) && url.contains(DOWNLOAD_FILE_URL)) {
			token = request.getParameter("token");
		} else {
			token = getAuthorization(request);
		}
		String key = CacheKeyPrefix.TOKEN.getValue() + token;
		if (getCache().isExists(key)) {
			getCache().expire(key, time);
			if (isVisit(token, request.getServletPath())) {
				return true;
			} else {
				result = new Gson().toJson(new WarnProtocol(403));
				return true;
			}
		}
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods", "POST, GET");
		PrintWriter out = response.getWriter();
		out.print(result);
		out.flush();
		out.close();
		return false;
	}

	// 得到指定请求消息头的值。如果没有该头，返回null
	private String getAuthorization(HttpServletRequest request) {
		return request.getHeader("Authorization");
	}

	/**
	 * 判断resUrl链接请求是否允许
	 * 
	 * @param token
	 * @param resUrl
	 * @return
	 */
	public boolean isVisit(String token, String resUrl) {
		String flag = resourcesService.isVisit(token, resUrl);
		return (StrUtil.isEmpty(flag)) ? false : true;
	}
}
