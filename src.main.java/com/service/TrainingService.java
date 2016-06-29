package com.service;


import java.util.List;

import org.springframework.stereotype.Service;

import com.pojos.Training;
import com.pojos.User;

@Service
public interface TrainingService
{
	public String registerTraining(Training training);
	public List<Training> searchTraining(User user);
	
}
