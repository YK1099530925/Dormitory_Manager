package org.yk.entitys;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name="YK_Building")
public class Building {
	@Id@Column(name="B_Id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private int B_No;
	@ManyToMany(targetEntity=Floor.class)
	//ӳ�����ӱ�ָ������ΪBuilding_Floor
	@JoinTable(name="Building_Floor",
	//ӳ�����ӱ�����ΪB_Id������У����յ�ǰʵ���������
	joinColumns=@JoinColumn(name="B_Id",referencedColumnName="B_Id"),
	//ӳ�����ӱ���F_Id������У����չ���ʵ���������
	inverseJoinColumns=@JoinColumn(name="F_Id",referencedColumnName="F_Id"))
	private Set<Floor> floor = new HashSet<>();
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public int getB_No() {
		return B_No;
	}
	public void setB_No(int b_No) {
		B_No = b_No;
	}
	public Set<Floor> getFloor() {
		return floor;
	}
	public void setFloor(Set<Floor> floor) {
		this.floor = floor;
	}
	
}
