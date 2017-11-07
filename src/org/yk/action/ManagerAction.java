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
	
	private int pageNow = 1;//初始当前页为第一页
	private int pageSize = 4;//每页显示4条记录
	
	// 管理员登录页面
	public String managerLogin() {
		return "managerLogin";
	}

	// 这个managerInfo是对应网页传过来的值
	private ManagerInfo managerInfo;

	private Building building;
	private Floor floor;
	private Dorm dorm;

	// 管理员登录验证
	public String managerCheckLogin() {
		// 得到登录的信息，如果不存在管理员，则mInfo为空
		ManagerInfo mInfo = loginService.checkManagerLogin(managerInfo.getManager(), managerInfo.getPassword());
		if (mInfo != null) {
			// 获得会话，用来保存登录信息
			Map<String, Object> session = ActionContext.getContext().getSession();
			// 把获取的对象保存在session中
			session.put("managerInfo", mInfo);

			return "managerWelcomPage";
		} else {
			return "managerLogin";
		}
	}
	// 验证用户名是否为空
	/*
	 * public void validate(){ if(managerInfo.getManager()==null ||
	 * managerInfo.getManager().equals("")){ this.addFieldError("manager",
	 * "用户名不能为空"); }else if(managerInfo.getPassword()==null ||
	 * managerInfo.getPassword().equals("")){ this.addFieldError("password",
	 * "密码不能为空"); } }
	 */

	private Map<String, Object> request;

	// 查询某栋某层某间的学生信息
	public String managerOper() {
		/*
		 * 出现的错误Exception occurred during processing request: null request没有获取到，
		 * 原因是没有实现RequestAware接口
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

	//问题：no session和延迟加载，当后台发送json数据格式的时候，存在外键，所以出现这个错误
	//因为吧获取的数据转换成json格式之后，需要获取外键的数据，此时，session已经关闭
	//所以解决方案：在转换成json数据格式的时候去掉外键列（不获取外键列的数据）
	
	public String slecetInfo() {
		System.out.println(pageNow);
		List<Student> students = managerService.getStudent(B_No, F_No, D_No, getPageNow(), getPageSize());
		//将得到的记录数送给pager，然后得到分页的类
		Pager pager = new Pager(pageNow, managerService.selectStudnetCount(B_No, F_No, D_No));
		//将分页的类设置为参数
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

	// 添加或编辑信息
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
		// 如果id为空 说明是我们要添加学生
		if (id == null) {
			modle = new Student();
		} else {// 否则，表示我们需要修改，所以我们直接从数据库中取得数据，并且当id不为空的时候，我们必须将id设置为隐藏域，否则id传递不过来也是会判断为添加数据的
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

	// 这个Student的作用是用来作为和栈顶对象的对应
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
