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
		EmpRecord employee = sess.get(EmpRecord.class, training.getTrainId());
		tx.commit();
		return employee.getEmpDept();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<EmpRecord> getEmployeeIdFromGroup(Training training) {
		
		return  sf.openSession().createQuery("from EmpRecord e where e.groupName = :name").setParameter("name", training.getNominate().replace("#", "")).list();
	}
}
