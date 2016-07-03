package com.service;


import java.util.List;

import org.springframework.stereotype.Service;

import com.pojos.Sessions;
import com.pojos.Trainer;
import com.pojos.Training;
import com.pojos.User;

@Service
public interface TrainingService
{
	public String registerTraining(Training training);
	public List<Training> searchTraining(Training training);
	public String deleteTraining(Training training);
	public List<Sessions> listSessions(Training trianing);
	public boolean nominateIntoTraining(Training training);
	public boolean withdrawFromTraining(Training training);
	public List<Training> trainingsaddedbyme(Training training);
	public List<Training> trainingsConduct(Training training);
	public String addNomineeToTraining(Training training);
	public List<Training> mandatoryTraining(Training training);
	public List<Trainer> trainerWithTechnology(Trainer trainer);
	
}
