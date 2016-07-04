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
import com.pojos.Trainer;
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
	public Training registerTraining(Training training) {
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
		System.out.println("Nomination List" + nominationList);
		if (nominationList.contains("#")) {
			List<EmpRecord> list = empDao.getEmployeeIdFromGroup(training);
			List<Integer> i = new ArrayList<>();
			for (EmpRecord e : list)
				i.add(e.getEmpId());
			training.setTraineeempId(i);
			System.out.println("Trainee empId: " + training.getTraineeempId());
		} else {
			nomination.add(Integer.parseInt(nominationList));
			training.setTraineeempId(nomination);
		}

		Session sess = sf.openSession();
		Transaction tx = sess.getTransaction();
		tx.begin();
		sess.save(training);
		tx.commit();
		return training;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Training> searchTraining(Training training) {
		Session sess = sf.openSession();
		Transaction tx = sess.getTransaction();
		tx.begin();
		Query q = sess.createQuery("from Training t where t.targetedAudience like ? and t.name like ?")
				.setString(0, empDao.getEmployeeDept(training)).setString(1, "%" + training.getName() + "%");

		/*
		 * .setParameter("targetedAudience", empDao.getEmployeeDept(training))
		 * .setParameter("name",'%'++'%');
		 */

		/*
		 * Query q = sess.
		 * createSQLQuery("select * from Training where targetedAudience like ? "
		 * + " and name like ?").setP
		 */
		List<Training> trainingSet = (List<Training>) q.list();
		tx.commit();
		System.out.println("Name of the training" + trainingSet.get(0).getName());
		return trainingSet;

	}

	// Persisting the customer object
	public String validateUser(User user) {
		Session sess = sf.openSession();
		Transaction tx = sess.getTransaction();
		tx.begin();
		Query q = sess.createQuery("from User u where u.username= :username and password = :password")
				.setParameter("username", user.getUsername()).setParameter("password", user.getPassword());
		tx.commit();
		List<User> result = q.list();
		if (result.size() == 1) {
			System.out.println(" working " + result.get(0).getEmpType());
			return result.get(0).getEmpType();
		} else {
			System.out.println("Not working");
			user.setEmpType("noemp");
			return user.getEmpType();
		}
	}

	@Override
	@Transactional
	public String deleteTraining(Training training) 
	{

		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();

		int trainId = training.getTrainId();
		String hql = "delete from Training where trainId =:trainId";
		Training t = (Training) session.get(Training.class,training.getTrainId());
		session.delete(t);
		//session.createQuery(hql).setLong("trainId", trainId).executeUpdate();
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
		List<Sessions> trainingSessions = (List<Sessions>) session
				.createSQLQuery("select * from Training_sessions where Training_trainId = ?")
				.setInteger(0, training.getTrainId()).list();
		tx.commit();

		return trainingSessions;

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Training> listmytrainings(User user) {

		Session session = sf.openSession();
		List<Training> trainingList = new ArrayList<>();
		List<Integer> list = session
				.createSQLQuery("select Training_trainId from training_traineeempid where traineeempId = ?")
				.setInteger(0, user.getEmpId()).list();
		for (Integer i : list) {
			Training training = session.get(Training.class, i);
			trainingList.add(training);
		}
		return trainingList;
	}

	@Override
	public boolean nominateIntoTraining(Training training) {
		// TODO Auto-generated method stub
		List<Integer> list = training.getTraineeempId();
		if (list.add(Integer.parseInt(training.getNominate()))) {
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
		try {
			System.out.println("Reached DAO");
			Session session = sf.openSession();
			Transaction tx = session.getTransaction();
			tx.begin();
			System.out.println(session
					.createSQLQuery("delete from Training_traineeempId where traineeempId = ? and Training_trainId = ?")
					.setInteger(0, Integer.parseInt(training.getNominate())).setInteger(1, training.getTrainId())
					.executeUpdate());
			tx.commit();
			return true;
		} catch (Exception e) {
			return false;
		}

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Training> trainingsaddedbyme(Training training) {
		// TODO Auto-generated method stub
		Session session = sf.openSession();
		List<Training> traininglist = session
				.createSQLQuery("select * from  Training where name like ? and trainingExecId = :trainingExecId")
				.setString(0, "%" + training.getName() + "%")
				.setParameter("trainingExecId", training.getTrainingExecId()).list();
		System.out.println("Training List" + traininglist);
		return traininglist;
	}

	@SuppressWarnings("unchecked")
	@Transactional
	@Override
	public List<Training> trainingsConduct(Training training) {
		// TODO Auto-generated method stub
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();
		tx.begin();
		List<Training> traininglist = session.createQuery("From Training t where t.trainerId = :trainerId")
				.setParameter("trainerId", training.getTrainerId()).list();
		tx.commit();
		System.out.println("Training List" + traininglist);
		return traininglist;
	}

	@Transactional
	@Override
	public String addNomineeToTraining(Training training) {
		try {
			System.out.println("Dao got called for training " + training.getTrainId());
			Session session = sf.openSession();
			Transaction tx = session.beginTransaction();
			tx.begin();
			Training trainingList = session.load(Training.class, training.getTrainId());
			System.out.println("Training Id: " + trainingList.getTrainId());
			List<Integer> nomineeList = trainingList.getTraineeempId();
			System.out.println("Nomination List" + nomineeList);
			String nominationList = training.getNominate();
			System.out.println("What we send as nominne is: " + nominationList);
			if (nominationList.contains("#")) {
				List<EmpRecord> list = empDao.getEmployeeIdFromGroup(training);
				if (list == null)
					return "No such employee to add";
				List<Integer> i = new ArrayList<>();
				for (EmpRecord e : list)
					i.add(e.getEmpId());
				nomineeList.addAll(i);
				tx.commit();
				return "Nominee Added";
			} else {
				// No Employee with such empid msg
				System.out.println("I came at the end");
				User u = session.get(User.class, Integer.parseInt(training.getNominate()));
				System.out.println("User Object" + u);
				if (u != null) {
					nomineeList.add(Integer.parseInt(nominationList));
					tx.commit();
					return "Nominee Added";
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Exception Got triggered");
			return "No such employee to add";
		}
		return "No such employee to add";
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Training> mandatoryTraining(Training training) {
		Session session = sf.openSession();
		List<Training> trainingList = new ArrayList<>();
		List<Integer> list = session
				.createSQLQuery("select Training_trainId from Training_traineeempId where traineeempId = ?")
				.setInteger(0, Integer.parseInt(training.getNominate())).list();

		for (Integer i : list) {
			Training training2 = session.get(Training.class, i);
			trainingList.add(training2);
		}
		return trainingList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Trainer> trainerWithTechnology(Trainer trainer) {
		System.out.println("Trainer technology: " + trainer.getName());
		Session session = sf.openSession();
		List<Trainer> trainersList = session
				.createQuery("select t from Trainer t join t.technology tech where tech = :tech")
				.setParameter("tech", trainer.getName()).list();
		return trainersList;
	}

	@SuppressWarnings("unchecked")
	public List<Training> trainingsByTrainer(Training training) {
		Session session = sf.openSession();
		List<Training> list = session.createQuery("from Training t where traineeempId = :trainee").list();
		if (list != null)
			return list;
		else
			return null;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Training> showTraining(Training training) {
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();
		tx.begin();
		List<Training> table = session.createQuery("From Training t where t.trainingExecId = :trainerId").setParameter("trainerId", training.getTrainingExecId()).list();
		tx.commit();
		System.out.println("Training DAO" +table);
		return table;
	}
}
