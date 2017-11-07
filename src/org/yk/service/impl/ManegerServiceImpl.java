package org.yk.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yk.dao.ManagerDao;
import org.yk.entitys.Building;
import org.yk.entitys.Dorm;
import org.yk.entitys.Floor;
import org.yk.entitys.Student;
import org.yk.service.ManagerService;
@Service
public class ManegerServiceImpl implements ManagerService {

	@Autowired
	private ManagerDao managerdao;
	
	@Override
	public List<Building> getBuildingAll() {
		return managerdao.getBuildingAll();
	}

	@Override
	public List<Floor> getFloorAll() {
		return managerdao.getFloorAll();
	}

	@Override
	public List<Dorm> getDormAll() {
		return managerdao.getDormAll();
	}

	@Override
	public List<Student> getStudent(int B_No, int F_No, int D_No,int pageNow,int pageSize) {
		return managerdao.getStudent(B_No, F_No, D_No, pageNow, pageSize);
	}

	@Override
	public void saveOrUpdate(Student student) {
		managerdao.saveOrUpdate(student);
	}

	@Override
	public Student getStudentInfo(Integer id) {
		return managerdao.getStudentInfo(id);
	}

	@Override
	public void delete(Integer id) {
		managerdao.delete(id);
	}

	@Override
	public boolean isSno(int s_No) {
		return (managerdao.getStudentInfoBySno(s_No) == null);
	}

	@Override
	public int selectStudnetCount(int B_No, int F_No, int D_No) {
		return managerdao.selectStudentCount(B_No, F_No, D_No);
	}
	
}
