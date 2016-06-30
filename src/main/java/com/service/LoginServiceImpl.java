package com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.LoginDao;
import com.dao.TrainingDao;
import com.pojos.User;

@Service
public class LoginServiceImpl implements LoginService
{
	@Autowired
	private LoginDao loginDao;

	@Override
	public User validateUser(User user)
	{
		// TODO Auto-generated method stub
		System.out.println(user.getUsername());
		return loginDao.validateUser(user);
	}
}
