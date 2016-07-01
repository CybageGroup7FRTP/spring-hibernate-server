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
		trainingService.registerTraining(training);
		return new Training();
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
		return trainingService.withdrawFromTraining(training);
	}
	
	
}
