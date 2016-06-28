package com.test;

import java.util.Calendar;
import java.util.Date;

public class Test
{
  public static void main(String[] args) 
  {
	  Date d1 = new Date();
	  Date d2 = new Date(2016,7,10);
	  Calendar c1 = Calendar.getInstance();
	  c1.setTime(d1);
	  for(int i=0;i<10;c1.add(Calendar.DATE, 1),i++)
	  {
		  if(c1.getTime().getDay()!=0 && c1.getTime().getDay()!=6)
		  {
			  System.out.println(c1.getTime());
			  System.out.println(i);
		  }
		  else
			  i--;
	  }
  }
}
