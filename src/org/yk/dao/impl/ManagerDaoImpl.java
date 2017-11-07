package org.yk.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.yk.dao.ManagerDao;
import org.yk.entitys.Building;
import org.yk.entitys.Dorm;
import org.yk.entitys.Floor;
import org.yk.entitys.Student;
@Repository
public class ManagerDaoImpl extends BaseDao implements ManagerDao {

	@Override
	public List<Building> getBuildingAll() {
		String hql = "from Building";
		return getSession().createQuery(hql).list();
	}

	@Override
	public List<Floor> getFloorAll() {
		String hql = "from Floor";
		return getSession().createQuery(hql).list();
	}

	@Override
	public List<Dorm> getDormAll() {
		String hql = "from Dorm";
		return getSession().createQuery(hql).list();
	}

	@Override
	public List<Student> getStudent(int B_No, int F_No, int D_No,int pageNow,int pageSize) {
		String hql = "from Student where B_Id = ? and F_Id = ? and D_Id = ?";
		List<Student> list = getSession().createQuery(hql).setParameter(0, B_No).setParameter(1, F_No).setParameter(2, D_No).setFirstResult(pageSize*(pageNow - 1)).setMaxResults(pageSize).list();
		return list;
	}

	@Override
	public void saveOrUpdate(Student student) {
		getSession().saveOrUpdate(student);
	}

	@Override
	public Student getStudentInfo(Integer id) {
 		return getSession().get(Student.class, id);
	}

	@Override
	public void delete(Integer id) {
		String hql = "delete from Student s where s.id = ?";
		getSession().createQuery(hql).setParameter(0, id).executeUpdate();
	}

	@Override
	public Student getStudentInfoBySno(int s_No) {
		String hql = "from Student where S_No = ?";
		return (Student)getSession().createQuery(hql).setParameter(0, s_No).uniqueResult();
	}

	@Override
	public int selectStudentCount(int B_No, int F_No, int D_No) {
		String hql = "from Student where B_Id = ? and F_Id = ? and D_Id = ?";
		return getSession().createQuery(hql).setParameter(0, B_No).setParameter(1, F_No).setParameter(2, D_No).list().size();
	}
	
	
}
