package com.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.pojos.EmpRecord;
import com.pojos.Training;
import com.pojos.User;

@Repository
@Transactional
public class EmployeeDaoImpl implements EmployeeDao 
{
	@Autowired
	private SessionFactory sf;
	
	@Override
	public String getEmployeeDept(Training training) 
	{
		Session sess = sf.openSession();
		Transaction tx = sess.getTransaction();
		tx.begin();
		System.out.println("User Id: "+training.getTrainId());
		EmpRecord employee = sess.get(EmpRecord.class, training.getTrainId());
		System.out.println("Emp Dept: "+employee.getEmpDept());
		tx.commit();
		return employee.getEmpDept();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Integer> getEmployeeIdFromGroup(Training training) {
		
		return (List<Integer>) sf.openSession().createQuery("from EmpRecord e where e.groupName = :name").setParameter("name", training.getNominate()).list();
	}
}
