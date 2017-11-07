package org.yk.dao.impl;

import org.springframework.stereotype.Repository;
import org.yk.dao.LoginDao;
import org.yk.entitys.ManagerInfo;
import org.yk.entitys.Student;
@Repository
public class LoginDaoImpl extends BaseDao implements LoginDao {

	@Override
	public ManagerInfo checkManagerLogin(String name, String password) {
		ManagerInfo managerInfo = null;
		String hql = "from ManagerInfo where manager = ? and password = ?";
		managerInfo = (ManagerInfo)getSession().createQuery(hql).setParameter(0, name).setParameter(1, password).uniqueResult();
		return managerInfo;
	}

	@Override
	public Student checkStudentLogin(int s_No, String password) {
		String hql = "from Student where S_No = ? and password = ?";
		return (Student)getSession().createQuery(hql).setParameter(0, s_No).setParameter(1, password).uniqueResult();
	}

}
