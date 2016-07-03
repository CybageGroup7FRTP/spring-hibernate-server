package com.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.pojos.User;
import com.service.LoginService;
import com.service.TrainingService;

import jdk.nashorn.internal.ir.RuntimeNode.Request;


@RestController
@SessionAttributes("mysession")
public class LoginController 
{
	@Autowired
	private LoginService loginservice;
	
	@Autowired
	private HttpSession session;
	
	@Autowired
	private HttpServletRequest request;
	
	public LoginController() 
	{
		System.out.println("Login Controller Called");
	}
	
	
	@RequestMapping("/")
	public @ResponseBody String WelcomeUser()
	{
		return "Welcome Cyabge Group 7 welcome user";
	}
	
	@CrossOrigin
	@RequestMapping(value="/login",method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody User validateUser(@RequestBody User user1)/*@RequestParam(value="username") String username, @RequestParam(value="password") String password*/
	{
		System.out.println("username = "+user1.getUsername());
		User u = loginservice.validateUser(user1);
		session = request.getSession();
		session.setAttribute("user", u); 
		System.out.println(session.getAttribute("user"));
		System.out.println("Session: "+request.getSession().getAttributeNames().nextElement());
		System.out.println("Session Time: "+request.getSession().getCreationTime());
		return u;
	} 
	
	
	@CrossOrigin
	@RequestMapping(value="/mysession",method=RequestMethod.POST)
	public @ResponseBody User yourSession()
	{
		session = request.getSession();
		System.out.println("Session Time: "+session.getCreationTime());
		System.out.println("Session: "+session.getAttributeNames().nextElement());
		User user = (User) request.getSession().getAttribute("user");
		System.out.println("UserAttributeFromSession: "+user);
		return user;
	}
}
