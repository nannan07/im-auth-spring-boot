package com.allmsi.auth.service;

/**
 * 资源信息操作
 * 
 * @author sunnannan
 *
 */
public interface ResourcesService {

	/**
	 * 将资源信息存入缓存
	 */
	void setResCache();

	String isVisit(String token, String resUrl);

}
