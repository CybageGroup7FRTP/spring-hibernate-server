package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.EmployeeDao;
import com.dao.TrainingDao;
import com.pojos.Sessions;
import com.pojos.Training;
import com.pojos.User;

@Service
public class TrainingServiceImpl implements TrainingService
{
	@Autowired
	private TrainingDao trainingDao;
	
	@Override
	public String registerTraining(Training training) {
		// TODO Auto-generated method stub
		return trainingDao.registerTraining(training);
	}
	
	@Override
	public List<Training> searchTraining(Training training) 
	{
		// TODO Auto-generated method stub
		return trainingDao.searchTraining(training);
	}
		
	@Override
	public String deleteTraining(Training training) 
	{
		System.out.println("Service method ..trng name received as = " +training.getName());
		return trainingDao.deleteTraining(training);
	}

	@Override
	public List<Sessions> listSessions(Training training) {
		return trainingDao.listSessions(training);
	}
	
	@Override
	public boolean nominateIntoTraining(Training training) {
		// TODO Auto-generated method stub
		return trainingDao.nominateIntoTraining(training);
	}
	
	@Override
	public boolean withdrawFromTraining(Training training) {
		// TODO Auto-generated method stub
		return trainingDao.withdrawFromTraining(training);
	}
	
	@Override
	public List<Training> trainingsaddedbyme(Training training) {
		// TODO Auto-generated method stub
		return trainingDao.trainingsaddedbyme(training);
	}
	
	@Override
	public List<Training> trainingsConduct(Training training) {
		// TODO Auto-generated method stub
		return trainingDao.trainingsConduct(training);
	}
	
}
