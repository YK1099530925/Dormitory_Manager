package org.yk.service;

import java.util.List;

import org.yk.entitys.Building;
import org.yk.entitys.Dorm;
import org.yk.entitys.Floor;
import org.yk.entitys.Student;

public interface ManagerService {
	//查询栋数
		public List<Building> getBuildingAll();
		//查询层数
		public List<Floor> getFloorAll();
		//查询寝室号
		public List<Dorm> getDormAll();
		//通过栋层号查询寝室的学生信息，最多显示四条记录
		public List<Student> getStudent(int B_No,int F_No,int D_No,int pageNow,int pageSize);
		//返回一共有多少条记录
		public int selectStudnetCount(int B_No,int F_No,int D_No);
		//添加信息
		public void saveOrUpdate(Student student);
		//点击编辑时获取学生的信息
		public Student getStudentInfo(Integer id);
		//通过id来获取学生的信息
		public void delete(Integer id);
		//判断是否存在此名字
		public boolean isSno(int s_No);
}
