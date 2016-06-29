package com.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.pojos.EmpRecord;
import com.pojos.User;

@Repository
@Transactional
public class EmployeeDaoImpl implements EmployeeDao 
{
	@Autowired
	private SessionFactory sf;
	
	@Override
	public String getEmployeeDept(User user) 
	{
		Session sess = sf.getCurrentSession();
		EmpRecord employee = sess.get(EmpRecord.class, user.getEmpId());
		return employee.getEmpDept();
	}
}
