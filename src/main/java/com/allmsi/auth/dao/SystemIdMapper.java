package com.allmsi.auth.dao;

import com.allmsi.auth.model.po.SystemId;

public interface SystemIdMapper {
	SystemId selectByPrimaryKey(String id);

}