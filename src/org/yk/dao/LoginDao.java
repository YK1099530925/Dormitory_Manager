package org.yk.dao;

import org.yk.entitys.ManagerInfo;
import org.yk.entitys.Student;

public interface LoginDao {
	public ManagerInfo checkManagerLogin(String name,String password);
	public Student checkStudentLogin(int s_No,String password);
}
