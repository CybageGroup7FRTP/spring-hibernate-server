package com.dao;

import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Repository;

import com.pojos.Training;

@Configuration
@Repository
public class ShowTrainingTableImpl implements ShowTrainingTableDao {

	public List<Training> showTable() {
		System.out.println("inside show dao");
		return null;
	}
	

}
