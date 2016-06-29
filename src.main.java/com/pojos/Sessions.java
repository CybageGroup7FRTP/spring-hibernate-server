package com.pojos;

import java.util.Date;

import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Embeddable
public class Sessions 
{
	
	private int sessionNo;
<<<<<<< HEAD
	
	@Temporal(TemporalType.DATE)
	private Date date;
	
	@Temporal(TemporalType.TIME)
=======
	private Date startDate;
	private Date endDate;
>>>>>>> 71f4f9ea96de5c0445512e6bdef22919574e2809
	private Date startTime;
	
	@Temporal(TemporalType.TIME)
	private Date endTime;
<<<<<<< HEAD
	
=======
	private int id;
>>>>>>> 71f4f9ea96de5c0445512e6bdef22919574e2809
	private String venue;
	
	public int getSessionNo() {
		return sessionNo;
	}
	public void setSessionNo(int sessionNo) {
		this.sessionNo = sessionNo;
	}
<<<<<<< HEAD
	
	
	public Date getDate() {
		return date;
	}
	
	public void setDate(Date date) {
		this.date = date;
=======
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
>>>>>>> 71f4f9ea96de5c0445512e6bdef22919574e2809
	}
	
	
	public Date getStartTime() {
		return startTime;
	}
	
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	
	
	public Date getEndTime() {
		return endTime;
	}
	
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
<<<<<<< HEAD
	
=======
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
>>>>>>> 71f4f9ea96de5c0445512e6bdef22919574e2809
	public String getVenue() {
		return venue;
	}
	
	public void setVenue(String venue) 
	{
		this.venue = venue;
	}
	
	
	
}
