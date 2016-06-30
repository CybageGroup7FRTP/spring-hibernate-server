package com.pojos;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;


@Entity
@Table
public class EmpRecord 
{
	@Id
	@GeneratedValue(generator="foreignkey")
	@GenericGenerator(name="foreignkey", strategy="foreign",parameters = { @Parameter(value="user",name="property"),@Parameter(value="user",name="property") })
	private int empId;
	
	@Column(nullable=false)
	private String empName;
	
	private String empDesg;
	
	@Column(nullable=false)
	private String empDept;
	
	private String groupName;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="empId")
	private User user;
	
	public int getEmpId() {
		return empId;
	}
	
	public void setEmpId(int empId) {
		this.empId=empId;
	}
	
	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	@Column(nullable=false)
	public String getEmpDesg() {
		return empDesg;
	}

	public void setEmpDesg(String empDesg) {
		this.empDesg = empDesg;
	}

	public String getEmpDept() {
		return empDept;
	}

	public void setEmpDept(String empDept) {
		this.empDept = empDept;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	
	
	
}
