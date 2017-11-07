package org.yk.action;

import java.util.Map;

import org.apache.struts2.interceptor.RequestAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.yk.entitys.Student;
import org.yk.service.LoginService;
import org.yk.service.StudentService;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

@Scope("prototype")
@Component
public class StudentAction extends ActionSupport implements RequestAware,
ModelDriven<Student>,Preparable{

	private static final long serialVersionUID = 1L;
	
	@Autowired
	private LoginService loginService;
	@Autowired
	private StudentService studentService;
	
	private Student student;
	
	public String studentLogin(){
		return "studentLogin";
	}
	
	//���ֵ�����:��ʼ��ѧ����¼��studentInfo���й�����Studentʵ�岢��Ҳ������Studentʵ�壬����
	//����¼��ʱ��ͨ��ѧ����ѧ�ź������¼������ѧ����ѧ����StudentInfo��������ӵ��У����Ե�¼��ʱ���޷�
	//��action�л�ȡStudentInfo�е�ѧ����һ���ԣ����Գ������ӳټ��ص�����

	public String studentCheckLogin(){
		Student stu = loginService.checkStudentLogin(student.getS_No(), student.getPassword());
		if(stu != null){
			Map<String, Object> session = ActionContext.getContext().getSession();
			session.put("student", stu);
			return "studentWelcomPage";
		}else {
			return "studentLogin";
		}
	}
	
	public void prepareStudentCheckLogin(){
		Student stu = loginService.checkStudentLogin(student.getS_No(), student.getPassword());
		model = new Student();
		model = stu;//����½��ѧ����model
	}
	
	/*public void vilidate(){
		if(student.getS_No() == 0 || student.getPassword() == null){
			this.addFieldError("S_No", "ѧ�Ų���Ϊ��");
		}else if(student.getPassword() == null || student.getPassword().equals("")){
			this.addFieldError("password", "���벻��Ϊ��");
		}
	}*/

	public LoginService getLoginService() {
		return loginService;
	}

	public void setLoginService(LoginService loginService) {
		this.loginService = loginService;
	}

	
	private Map<String, Object> request;

	public Map<String, Object> getRequest() {
		return request;
	}

	public void setRequest(Map<String, Object> request) {
		this.request = request;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	@Override
	public void prepare() throws Exception {}

	private Student model;
	
	@Override
	public Student getModel() {
		return model;
	}
	
}
