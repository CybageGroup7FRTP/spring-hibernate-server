package com.service;


import org.springframework.stereotype.Service;

import com.pojos.Training;
import com.pojos.User;

@Service
public interface TrainingService
{
	public String registerTraining(Training training);
	
}
