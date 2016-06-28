package com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	
	
		

	
	
}
