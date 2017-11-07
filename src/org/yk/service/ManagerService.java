package org.yk.service;

import java.util.List;

import org.yk.entitys.Building;
import org.yk.entitys.Dorm;
import org.yk.entitys.Floor;
import org.yk.entitys.Student;

public interface ManagerService {
	//��ѯ����
		public List<Building> getBuildingAll();
		//��ѯ����
		public List<Floor> getFloorAll();
		//��ѯ���Һ�
		public List<Dorm> getDormAll();
		//ͨ������Ų�ѯ���ҵ�ѧ����Ϣ�������ʾ������¼
		public List<Student> getStudent(int B_No,int F_No,int D_No,int pageNow,int pageSize);
		//����һ���ж�������¼
		public int selectStudnetCount(int B_No,int F_No,int D_No);
		//�����Ϣ
		public void saveOrUpdate(Student student);
		//����༭ʱ��ȡѧ������Ϣ
		public Student getStudentInfo(Integer id);
		//ͨ��id����ȡѧ������Ϣ
		public void delete(Integer id);
		//�ж��Ƿ���ڴ�����
		public boolean isSno(int s_No);
}
