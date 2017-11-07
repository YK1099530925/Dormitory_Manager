package org.yk.entitys;

import javax.persistence.*;

@Entity
@Table(name="YK_Drom")
public class Dorm {
	@Id@Column(name="D_Id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private int D_No;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public int getD_No() {
		return D_No;
	}
	public void setD_No(int d_No) {
		D_No = d_No;
	}
}
