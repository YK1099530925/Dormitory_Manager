package org.yk.entitys;

import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name="YK_Student")
public class Student {
	@Id@Column(name="S_Id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private int S_No;
	private String name;
	private String sex;
	private Date birth;
	private String password;
	@OneToOne(targetEntity=Building.class)
	@JoinColumn(name="B_Id",referencedColumnName="B_Id")
	private Building building;
	@OneToOne(targetEntity=Floor.class)
	@JoinColumn(name="F_Id",referencedColumnName="F_Id")
	private Floor floor;
	@OneToOne(targetEntity=Dorm.class)
	@JoinColumn(name="D_Id",referencedColumnName="D_Id")
	private Dorm dorm;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public int getS_No() {
		return S_No;
	}
	public void setS_No(int s_No) {
		S_No = s_No;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public Date getBirth() {
		return birth;
	}
	public void setBirth(Date birth) {
		this.birth = birth;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
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
	
}