package org.yk.service;

import org.yk.entitys.ManagerInfo;
import org.yk.entitys.Student;

public interface LoginService {
	//ͨ������Ա��������Ա�����ж��Ƿ���ڴ˹���Ա������������������
	public ManagerInfo checkManagerLogin(String name,String password);
	//ѧ����¼
	public Student checkStudentLogin(int s_No,String password);

}
