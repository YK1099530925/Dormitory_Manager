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
	//映射连接表，指定表名为Building_Floor
	@JoinTable(name="Building_Floor",
	//映射连接表中名为B_Id的外键列，参照当前实体的主键列
	joinColumns=@JoinColumn(name="B_Id",referencedColumnName="B_Id"),
	//映射链接表中F_Id的外键列，参照关联实体的主键列
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
