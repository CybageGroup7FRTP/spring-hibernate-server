package com.dao;

import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Repository;

import com.pojos.Training;
import com.pojos.User;

@Configuration
@Repository
public interface TrainingDao 
{

	public String registerTraining(Training training);
	public List<Training> searchTraining(User user);
	public String deleteTraining(Training training);
	public String validateUser(User user);	

}
