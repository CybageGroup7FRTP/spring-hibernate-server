package com.service;

import org.springframework.stereotype.Service;

import com.pojos.User;

@Service
public interface LoginService
{
	public String validateUser(User user);
}
