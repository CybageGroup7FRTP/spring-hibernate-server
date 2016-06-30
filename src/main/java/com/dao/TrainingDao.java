package com.dao;

import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Repository;

import com.pojos.Sessions;
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
}
