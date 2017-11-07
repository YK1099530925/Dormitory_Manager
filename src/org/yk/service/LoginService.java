package org.yk.service;

import org.yk.entitys.ManagerInfo;
import org.yk.entitys.Student;

public interface LoginService {
	//通过管理员名，管理员密码判断是否存在此管理员，如果存在则允许操作
	public ManagerInfo checkManagerLogin(String name,String password);
	//学生登录
	public Student checkStudentLogin(int s_No,String password);

}
