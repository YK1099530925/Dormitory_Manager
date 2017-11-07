package org.yk.entitys;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name="YK_Floor")
public class Floor {
	@Id@Column(name="F_Id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private int F_No;
	@ManyToMany(targetEntity=Dorm.class)
	@JoinTable(name="Floor_Dorm",
	joinColumns=@JoinColumn(name="F_Id",referencedColumnName="F_Id"),
	inverseJoinColumns=@JoinColumn(name="D_Id",referencedColumnName="D_Id"))
	private Set<Dorm> dorm = new HashSet<>();
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public int getF_No() {
		return F_No;
	}
	public void setF_No(int f_No) {
		F_No = f_No;
	}
	public Set<Dorm> getDorm() {
		return dorm;
	}
	public void setDorm(Set<Dorm> dorm) {
		this.dorm = dorm;
	}
	
}
