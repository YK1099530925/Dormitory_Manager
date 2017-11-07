package org.yk.action;


import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.RequestAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.yk.entitys.Building;
import org.yk.entitys.Dorm;
import org.yk.entitys.Floor;
import org.yk.entitys.ManagerInfo;
import org.yk.entitys.Student;
import org.yk.entitys.StudentInfo;
import org.yk.service.LoginService;
import org.yk.service.ManagerService;
import org.yk.service.pager.Pager;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;
import net.sf.json.util.PropertyFilter;

@Scope("prototype")
@Component
public class ManagerAction extends ActionSupport implements RequestAware, ModelDriven<Student>, Preparable {

	private static final long serialVersionUID = 1L;

	@Autowired
	private LoginService loginService;

	@Autowired
	private ManagerService managerService;
	
	private int pageNow = 1;//��ʼ��ǰҳΪ��һҳ
	private int pageSize = 4;//ÿҳ��ʾ4����¼
	
	// ����Ա��¼ҳ��
	public String managerLogin() {
		return "managerLogin";
	}

	// ���managerInfo�Ƕ�Ӧ��ҳ��������ֵ
	private ManagerInfo managerInfo;

	private Building building;
	private Floor floor;
	private Dorm dorm;

	// ����Ա��¼��֤
	public String managerCheckLogin() {
		// �õ���¼����Ϣ����������ڹ���Ա����mInfoΪ��
		ManagerInfo mInfo = loginService.checkManagerLogin(managerInfo.getManager(), managerInfo.getPassword());
		if (mInfo != null) {
			// ��ûỰ�����������¼��Ϣ
			Map<String, Object> session = ActionContext.getContext().getSession();
			// �ѻ�ȡ�Ķ��󱣴���session��
			session.put("managerInfo", mInfo);

			return "managerWelcomPage";
		} else {
			return "managerLogin";
		}
	}
	// ��֤�û����Ƿ�Ϊ��
	/*
	 * public void validate(){ if(managerInfo.getManager()==null ||
	 * managerInfo.getManager().equals("")){ this.addFieldError("manager",
	 * "�û�������Ϊ��"); }else if(managerInfo.getPassword()==null ||
	 * managerInfo.getPassword().equals("")){ this.addFieldError("password",
	 * "���벻��Ϊ��"); } }
	 */

	private Map<String, Object> request;

	// ��ѯĳ��ĳ��ĳ���ѧ����Ϣ
	public String managerOper() {
		/*
		 * ���ֵĴ���Exception occurred during processing request: null requestû�л�ȡ����
		 * ԭ����û��ʵ��RequestAware�ӿ�
		 */
		request.put("Buildings", managerService.getBuildingAll());
		request.put("Floors", managerService.getFloorAll());
		request.put("Dorms", managerService.getDormAll());
		return "managerSelect";
	}

	private int B_No;
	private int F_No;
	private int D_No;

	private String result;

	//���⣺no session���ӳټ��أ�����̨����json���ݸ�ʽ��ʱ�򣬴�����������Գ����������
	//��Ϊ�ɻ�ȡ������ת����json��ʽ֮����Ҫ��ȡ��������ݣ���ʱ��session�Ѿ��ر�
	//���Խ����������ת����json���ݸ�ʽ��ʱ��ȥ������У�����ȡ����е����ݣ�
	
	public String slecetInfo() {
		System.out.println(pageNow);
		List<Student> students = managerService.getStudent(B_No, F_No, D_No, getPageNow(), getPageSize());
		//���õ��ļ�¼���͸�pager��Ȼ��õ���ҳ����
		Pager pager = new Pager(pageNow, managerService.selectStudnetCount(B_No, F_No, D_No));
		//����ҳ��������Ϊ����
		request.put("pages", pager);
		try {
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setCharacterEncoding("utf-8");
			PrintWriter pWriter = response.getWriter();
			JsonConfig jsonConfig = new JsonConfig();
			jsonConfig.setJsonPropertyFilter(new PropertyFilter() {
				@Override
				public boolean apply(Object sorce, String name, Object value) {
					if (name.equals("building") || name.equals("floor") || name.equals("dorm")) {
						return true;
					}
					return false;
				}
			});
			JSONArray jsonArray = JSONArray.fromObject(students, jsonConfig);
			pWriter.write(jsonArray.toString());
			pWriter.flush();
			pWriter.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	private Integer id;

	// ��ӻ�༭��Ϣ
	public String insert() {
		request.put("Buildings", managerService.getBuildingAll());
		request.put("Floors", managerService.getFloorAll());
		request.put("Dorms", managerService.getDormAll());
		return "insert";
	}

	public void prepareInsert() {
		if (id != null) {
			modle = managerService.getStudentInfo(id);
		}
	}

	public String input() {
		managerService.saveOrUpdate(modle);
		return "managerWelcomPage";
	}

	public void prepareInput() {
		// ���idΪ�� ˵��������Ҫ���ѧ��
		if (id == null) {
			modle = new Student();
		} else {// ���򣬱�ʾ������Ҫ�޸ģ���������ֱ�Ӵ����ݿ���ȡ�����ݣ����ҵ�id��Ϊ�յ�ʱ�����Ǳ��뽫id����Ϊ�����򣬷���id���ݲ�����Ҳ�ǻ��ж�Ϊ������ݵ�
			modle = managerService.getStudentInfo(id);
		}
	}

	private InputStream inputStream;

	public InputStream getInputStream() {
		return inputStream;
	}

	public String delete() {
		try {
			managerService.delete(id);
			inputStream = new ByteArrayInputStream("1".getBytes("utf-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			try {
				inputStream = new ByteArrayInputStream("0".getBytes("utf-8"));
			} catch (UnsupportedEncodingException e1) {
				e1.printStackTrace();
			}
		}
		return "ajax-success";
	}
	
	private int s_no;
	
	public String validateSno() throws UnsupportedEncodingException{
		if(managerService.isSno(s_no)){
			inputStream = new ByteArrayInputStream("1".getBytes("utf-8"));
		}else {
			inputStream = new ByteArrayInputStream("0".getBytes("utf-8"));
		}
		return "ajax-success";
	}

	@Override
	public void prepare() throws Exception {
	}

	// ���Student��������������Ϊ��ջ������Ķ�Ӧ
	private Student modle;

	@Override
	public Student getModel() {
		return modle;
	}

	public ManagerInfo getManagerInfo() {
		return managerInfo;
	}

	public void setManagerInfo(ManagerInfo managerInfo) {
		this.managerInfo = managerInfo;
	}

	public Map<String, Object> getRequest() {
		return request;
	}

	public void setRequest(Map<String, Object> request) {
		this.request = request;
	}

	public Building getBuilding() {
		return building;
	}

	public void setBuilding(Building building) {
		this.building = building;
	}

	public Floor getFloor() {
		return floor;
	}

	public void setFloor(Floor floor) {
		this.floor = floor;
	}

	public Dorm getDorm() {
		return dorm;
	}

	public void setDorm(Dorm dorm) {
		this.dorm = dorm;
	}

	public int getB_No() {
		return B_No;
	}

	public void setB_No(int b_No) {
		B_No = b_No;
	}

	public int getF_No() {
		return F_No;
	}

	public void setF_No(int f_No) {
		F_No = f_No;
	}

	public int getD_No() {
		return D_No;
	}

	public void setD_No(int d_No) {
		D_No = d_No;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getS_no() {
		return s_no;
	}

	public void setS_no(int s_no) {
		this.s_no = s_no;
	}

	public int getPageNow() {
		return pageNow;
	}

	public void setPageNow(int pageNow) {
		this.pageNow = pageNow;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
}
