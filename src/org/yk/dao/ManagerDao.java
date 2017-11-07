package org.yk.dao;

import java.util.List;

import org.yk.entitys.Building;
import org.yk.entitys.Dorm;
import org.yk.entitys.Floor;
import org.yk.entitys.Student;

public interface ManagerDao {
	//查询栋数
	public List<Building> getBuildingAll();
	//查询层数
	public List<Floor> getFloorAll();
	//查询寝室号
	public List<Dorm> getDormAll();
	//通过栋层号查询寝室的学生信息，但是每页查询四个
	public List<Student> getStudent(int B_No,int F_No,int D_No,int pageNow,int pageSize);
	//返回一共有多少条记录
	public int selectStudentCount(int B_No,int F_No,int D_No);
	//添加学生信息
	public void saveOrUpdate(Student student);
	//修改的时候获取学生的信息
	public Student getStudentInfo(Integer id);
	//删除信息
	public void delete(Integer id);
	//通过S_No得到学生的信息
	public Student getStudentInfoBySno(int s_No);
}
