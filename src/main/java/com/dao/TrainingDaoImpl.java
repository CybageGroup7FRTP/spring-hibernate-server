package com.dao;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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
		Query q = sess.createQuery("from Training t where t.targetedAudience = :targetedAudience")
				.setParameter("targetedAudience", empDao.getEmployeeDept(training));
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
}
