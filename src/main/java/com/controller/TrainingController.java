package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.pojos.Sessions;
import com.pojos.Trainer;
import com.pojos.Training;
import com.pojos.User;
import com.service.TrainingService;

@RestController
public class TrainingController
{
	@Autowired
	private TrainingService trainingService;
	
	@CrossOrigin
	@RequestMapping(value="/registertraining",method=RequestMethod.POST,consumes=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Training registerTraining(@RequestBody Training training)
	{
		System.out.println(training.getName());
		Training training2 = trainingService.registerTraining(training);
		return training2;
	}
	
	@CrossOrigin
	@RequestMapping(value="/searchtraining",method=RequestMethod.POST,consumes=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<Training> searchTraining(@RequestBody Training training)
	{
		return trainingService.searchTraining(training);
	}
	
	@CrossOrigin
	@RequestMapping(value="/delete",method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody void removeTraining(@RequestBody Training training)
	{
		
		System.out.println("Training to be deleted is: "+training.getName());
		System.out.println("inside delete training method");
		trainingService.deleteTraining(training);
	}
	
	@CrossOrigin
	@RequestMapping(value="/listsessions",method=RequestMethod.POST,consumes=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<Sessions> listsessions(@RequestBody Training training)
	{
		return trainingService.listSessions(training);
	}
	
	@CrossOrigin
	@RequestMapping(value="/nominatefortraining",method=RequestMethod.POST,consumes=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody boolean nominateIntoTraining(@RequestBody Training training)
	{
		return trainingService.nominateIntoTraining(training);
	}
	
	@CrossOrigin
	@RequestMapping(value="/withdrawfromtraining",method=RequestMethod.POST,consumes=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody boolean withdrawFromTraining(@RequestBody Training training)
	{
		System.out.println("Reached controller");
		return trainingService.withdrawFromTraining(training);
	}
	
	@CrossOrigin
	@RequestMapping(value="/trainingsconductedbyme",method=RequestMethod.POST,consumes=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<Training> trainingsConduct(@RequestBody Training training)
	{
		return trainingService.trainingsConduct(training);
	}
	
	@CrossOrigin
	@RequestMapping(value="/addedtrainings",method=RequestMethod.POST,consumes=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<Training> trainingsaddedbyme(@RequestBody Training training)
	{
		System.out.println("Training Employee Id: "+training.getTrainingExecId());
		return trainingService.trainingsaddedbyme(training);
	}
	
	@CrossOrigin
	@RequestMapping(value="/addnomination",method=RequestMethod.POST,consumes=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody boolean addNomineeToTraining(@RequestBody Training training)
	{
		System.out.println("Add nomination got called");
		String s = trainingService.addNomineeToTraining(training);
		System.out.println("Nomination List: "+s);
		if(s!=null)
			return true;
		return false;
	}
	
	@CrossOrigin
	@RequestMapping(value="/mandatorytraining",method=RequestMethod.POST,consumes=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<Training> mandatoryTraining(@RequestBody Training training)
	{
		return trainingService.mandatoryTraining(training);
	}
	
	@CrossOrigin
	@RequestMapping(value="/trainerwithtechnology",method=RequestMethod.POST,consumes=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<Trainer> trainerWithTechnology(@RequestBody Trainer trainer)
	{
		return trainingService.trainerWithTechnology(trainer);
	}
}
