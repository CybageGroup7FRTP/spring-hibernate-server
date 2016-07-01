package com.dao;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.jws.soap.SOAPBinding.Use;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.pojos.EmpRecord;
import com.pojos.Sessions;
import com.pojos.Training;
import com.pojos.User;

@Configuration
@Repository
@Transactional
public class TrainingDaoImpl implements TrainingDao {
	// Autowiring sessionFactory
	@Autowired
	private SessionFactory sf;

	@Autowired
	private EmployeeDao empDao;

	@Override
	public String registerTraining(Training training) {
		// TODO Auto-generated method stub
		List<Sessions> sessions = new ArrayList<>();
		List<Integer> nomination = new ArrayList<>();
		int n = training.getDuration();
		Date d1 = training.getStartDate();
		Calendar c1 = Calendar.getInstance();
		c1.setTime(d1);
		for (int i = 0; i < n; c1.add(Calendar.DATE, 1), i++) {
			if (c1.getTime().getDay() != 0 && c1.getTime().getDay() != 6) {

				System.out.println(c1.getTime());
				Sessions sess = new Sessions();
				sess.setSessionNo(i + 1);
				sess.setDate(c1.getTime());
				sess.setVenue(training.getLocation());
				sess.setStartTime(training.getStartTime());
				sess.setEndTime(training.getEndTime());
				sessions.add(sess);
				System.out.println(i);
			} else
				i--;
		}
		training.setSessions(sessions);
		String nominationList = training.getNominate();
		System.out.println("Nomination List"+nominationList);
		if(nominationList.contains("#"))
		{
			List<EmpRecord> list = empDao.getEmployeeIdFromGroup(training);
			List<Integer> i = new ArrayList<>();
			for (EmpRecord e : list)
				i.add(e.getEmpId());
			training.setTraineeempId(i);
			System.out.println("Trainee empId: "+training.getTraineeempId());
		}
		else
		{
			nomination.add(Integer.parseInt(nominationList));
			training.setTraineeempId(nomination);
		}
		
		Session sess = sf.openSession();
		Transaction tx = sess.getTransaction();
		tx.begin();
		sess.save(training);
		tx.commit();
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Training> searchTraining(Training training) {
		Session sess = sf.openSession();
		Transaction tx = sess.getTransaction();
		tx.begin();
		Query q = sess.createQuery("from Training t where t.targetedAudience like ? and t.name like ?")
				.setString(0, empDao.getEmployeeDept(training))
				.setString(1, "%"+training.getName()+"%");
			
				
				/*.setParameter("targetedAudience", empDao.getEmployeeDept(training))
				.setParameter("name",'%'++'%');*/
		
		/*Query q = sess.createSQLQuery("select * from Training where targetedAudience like ? "
				+ " and name like ?").setP*/
		List<Training> trainingSet = (List<Training>) q.list();
		tx.commit();
		return trainingSet;

	}

	// Persisting the customer object
	public String validateUser(User user)
	{
			Session sess =  sf.openSession();
			Transaction tx = sess.getTransaction();
			tx.begin();
			Query q = sess.createQuery("from User u where u.username= :username and password = :password")
					.setParameter("username", user.getUsername())
					.setParameter("password", user.getPassword());
			tx.commit();
			List<User> result = q.list();
			if(result.size()==1)
			{
				System.out.println(" working "+result.get(0).getEmpType());
				return result.get(0).getEmpType();
			}
			else
			{
				System.out.println("Not working");
				user.setEmpType("noemp");
				return user.getEmpType();
			}
	}
	
	
	@Override
	@Transactional
	public String deleteTraining(Training training) {
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();
		
		String name = training.getName();
		String hql = "delete from Training where name =:name";
		session.createQuery(hql).setString("name",name).executeUpdate();
		tx.commit();
		
		System.out.println("Deletion Successful");
		return null;
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Sessions> listSessions(Training training) {
		// TODO Auto-generated method stub
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();
		
		String name = training.getName();
		List<Sessions> trainingSessions = (List<Sessions>) session.createSQLQuery("select * from Training_sessions where Training_trainId = ?").setInteger(0, training.getTrainId()).list();
		tx.commit();
		
		return trainingSessions;
		
	}
	
	@Override
	public List<Training> listmytrainings(User user) {
		
		Session session = sf.openSession();
		List<Training> trainingList = new ArrayList<>();
		List<Integer> list = session.createSQLQuery("select Training_trainId from training_traineeempid where traineeempId = ?").setInteger(0, user.getEmpId()).list();
		for(Integer i : list)
		{
			Training training = session.get(Training.class, i);
			trainingList.add(training);
		}
		return trainingList;
	}
	
	@Override
	public boolean nominateIntoTraining(Training training) {
		// TODO Auto-generated method stub
		List<Integer> list = training.getTraineeempId();
		if(list.add(Integer.parseInt(training.getNominate())))
		{
			Session session = sf.openSession();
			Transaction tx = session.beginTransaction();
			training.setTraineeempId(list);
			session.save(training);
			tx.commit();
			return true;
		}
		return false;
	}

	@Override
	public boolean withdrawFromTraining(Training training) {
		// TODO Auto-generated method stub
		
		List<Integer> list = training.getTraineeempId();
		if(list.remove(Integer.parseInt(training.getNominate()))==1)
		{
			Session session = sf.openSession();
			Transaction tx = session.beginTransaction();
			training.setTraineeempId(list);
			session.save(training);
			tx.commit();
			return true;
		}
		return false;
	}
}
