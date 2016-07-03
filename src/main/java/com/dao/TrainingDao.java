package com.dao;

import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pojos.Sessions;
import com.pojos.Trainer;
import com.pojos.Training;
import com.pojos.User;

@Configuration
@Repository
public interface TrainingDao 
{

	public String registerTraining(Training training);
	public List<Training> searchTraining(Training training);
	public String deleteTraining(Training training);
	public String validateUser(User user);	
	public List<Sessions> listSessions(Training training);
	public List<Training> listmytrainings(User user);
	public boolean nominateIntoTraining(Training training);
	public boolean withdrawFromTraining(Training training);
	public List<Training> trainingsaddedbyme(Training training);
	public List<Training> trainingsConduct(Training training);
	public String addNomineeToTraining(Training training);
	public List<Training> mandatoryTraining(Training training);
	public List<Trainer> trainerWithTechnology(Trainer trainer);
	
}
