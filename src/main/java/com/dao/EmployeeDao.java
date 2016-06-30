package com.dao;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder.In;

import com.pojos.Training;
import com.pojos.User;

public interface EmployeeDao 
{
	public String getEmployeeDept(Training training);
	public List<Integer> getEmployeeIdFromGroup(Training training);
}
