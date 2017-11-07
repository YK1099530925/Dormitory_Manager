package org.yk.dao;

import java.util.List;

import org.yk.entitys.Building;
import org.yk.entitys.Dorm;
import org.yk.entitys.Floor;
import org.yk.entitys.Student;

public interface ManagerDao {
	//��ѯ����
	public List<Building> getBuildingAll();
	//��ѯ����
	public List<Floor> getFloorAll();
	//��ѯ���Һ�
	public List<Dorm> getDormAll();
	//ͨ������Ų�ѯ���ҵ�ѧ����Ϣ������ÿҳ��ѯ�ĸ�
	public List<Student> getStudent(int B_No,int F_No,int D_No,int pageNow,int pageSize);
	//����һ���ж�������¼
	public int selectStudentCount(int B_No,int F_No,int D_No);
	//���ѧ����Ϣ
	public void saveOrUpdate(Student student);
	//�޸ĵ�ʱ���ȡѧ������Ϣ
	public Student getStudentInfo(Integer id);
	//ɾ����Ϣ
	public void delete(Integer id);
	//ͨ��S_No�õ�ѧ������Ϣ
	public Student getStudentInfoBySno(int s_No);
}
