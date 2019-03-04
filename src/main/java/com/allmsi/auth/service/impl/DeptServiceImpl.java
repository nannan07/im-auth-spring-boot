package com.allmsi.auth.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.allmsi.auth.dao.DeptMapper;
import com.allmsi.auth.model.TreeDataBean;
import com.allmsi.auth.model.po.Dept;
import com.allmsi.auth.model.vo.DeptTree;
import com.allmsi.auth.model.vo.DeptVo;
import com.allmsi.auth.model.vo.UserDeptVo;
import com.allmsi.auth.service.DeptService;
import com.allmsi.sys.util.StrUtil;
import com.allmsi.sys.util.TreeUtil;

@Service
public class DeptServiceImpl implements DeptService {

	@Resource
	private DeptMapper deptDao;

	@Override
	public List<UserDeptVo> listUserDeptVo(String userId) {
		List<UserDeptVo> userDeptVo = new ArrayList<UserDeptVo>();
		List<Dept> deptList = deptDao.listUserDeptVo(userId);
		for (Dept dept : deptList) {
			userDeptVo.add(new UserDeptVo(dept));
		}
		return userDeptVo;
	}

	@Override
	public DeptVo selectByPrimaryKey(String id) {
		return new DeptVo(deptDao.selectByPrimaryKey(id));
	}

	@Override
	public List<DeptVo> listDeptList(List<String> deptIds) {
		List<DeptVo> list = new ArrayList<DeptVo>();
		if (deptIds != null && deptIds.size() > 0) {
			List<Dept> dLIst = deptDao.listDeptList(deptIds);
			for (Dept dept : dLIst) {
				list.add(new DeptVo(dept));
			}
		}
		return list;
	}

	@Override
	public List<DeptVo> listParentDeptVo(String userId) {
		List<DeptVo> userDeptVo = new ArrayList<DeptVo>();
		List<Dept> deptList = deptDao.listUserDeptVo(userId);
		List<Dept> dList = new ArrayList<Dept>();
		for (Dept dept : deptList) {
			dList.addAll(deptDao.listParentDeptTree(dept.getId()));
		}
		for (Dept dept : dList) {
			userDeptVo.add(new DeptVo(dept));
		}
		return userDeptVo;
	}

	@Override
	public List<DeptVo> listSubordinateDeptVo(String userId) {
		List<Dept> list = new ArrayList<Dept>();
		List<Dept> deptList = deptDao.listUserDeptVo(userId);
		for (Dept dept : deptList) {
			String id = dept.getId();
			list.addAll(deptDao.selectDeptTree(id));
		}
		List<DeptVo> userDeptVo = new ArrayList<DeptVo>();
		for (Dept dept : list) {
			userDeptVo.add(new DeptVo(dept));
		}
		return userDeptVo;
	}

	@Override
	public TreeDataBean selectDeptTree(String id) {
		if (StrUtil.isEmpty(id)) {
			id = "";
		}
		List<Dept> list = new ArrayList<Dept>();
		if (StrUtil.isEmpty(id)) {
			List<Dept> deptList = getDeptByPId(null);
			List<String> ids = new ArrayList<String>();
			for (Dept dept : deptList) {
				ids.add(dept.getId());
			}
			for (String string : ids) {
				list.addAll(deptDao.selectDeptTree(string));
			}
		} else {
			list.addAll(deptDao.selectDeptTree(id));
		}
		List<DeptTree> deptTreeList = new ArrayList<DeptTree>();
		for (Dept dept : list) {
			deptTreeList.add(new DeptTree(dept));
		}
		return new TreeDataBean(TreeUtil.getFatherNode(id, deptTreeList));
	}
	
	private List<Dept> getDeptByPId(String pid) {
		List<Dept> deptList = new ArrayList<Dept>();
		deptList.addAll(deptDao.selectDeptByPId(pid));
		return deptList;
	}

	@Override
	public List<DeptVo> listDeptTree(String id) {
		if (StrUtil.isEmpty(id)) {
			id = "";
		}
		List<Dept> list = new ArrayList<Dept>();
		if (StrUtil.isEmpty(id)) {
			List<Dept> deptList = getDeptByPId(null);
			List<String> ids = new ArrayList<String>();
			for (Dept dept : deptList) {
				ids.add(dept.getId());
			}
			for (String string : ids) {
				list.addAll(deptDao.selectDeptTree(string));
			}
		} else {
			list.addAll(deptDao.selectDeptTree(id));
		}
		List<DeptVo> dList = new ArrayList<DeptVo>();
		for (Dept dept : list) {
			dList.add(new DeptVo(dept));
		}
		return dList;
	}
}
