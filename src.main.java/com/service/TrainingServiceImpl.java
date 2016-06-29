package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.EmployeeDao;
import com.dao.TrainingDao;
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
	public List<Training> searchTraining(User user) 
	{
		// TODO Auto-generated method stub
		return trainingDao.searchTraining(user);
	}
		
	@Override
	public String deleteTraining(Training training) 
	{
		System.out.println("Service method ..trng name received as = " +training.getName());
		return trainingDao.deleteTraining(training);
	}

	
	
}
