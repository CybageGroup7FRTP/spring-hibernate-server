package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


import com.pojos.Training;
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
}
