package com.allmsi.auth.service;

import java.util.Map;

import com.allmsi.auth.model.po.User;

/**
 * 系统登录和退出操作
 * 
 * @author sunnannan
 *
 */
public interface LoginService {

	public Object login(String sToken, String loginName, String password);

	public Object logout(String sysId, String uToken);

	Map<String, Object> getLoginInfo(String sToken, User user);

}
