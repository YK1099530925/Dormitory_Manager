package org.yk.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yk.dao.LoginDao;
import org.yk.entitys.ManagerInfo;
import org.yk.entitys.Student;
import org.yk.service.LoginService;

@Service
public class LoginServiceImpl implements LoginService {
	@Autowired
	private LoginDao loginDao;

	@Override
	public ManagerInfo checkManagerLogin(String name, String password) {
		return loginDao.checkManagerLogin(name, password);
	}

	@Override
	public Student checkStudentLogin(int s_No, String password) {
		return loginDao.checkStudentLogin(s_No, password);
	}

}
