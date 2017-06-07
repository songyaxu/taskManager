package com.taskmanager.util;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class TimeUtil {
	public static SimpleDateFormat dateformatAll= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	public static Timestamp currentTime(){
		return new Timestamp(System.currentTimeMillis()); 
	}
	public static String getTimeWithoutMilliSecond(Timestamp time){
		return dateformatAll.format(time);
	}
	public static Timestamp gettimestampTime(String time){
		String temp="";
		String[] date=time.split(" ");
		String[] str=date[0].split("/"); //03/22/2017 13:21:35
		temp=str[2]+"-"+str[0]+"-"+str[1]+" "+date[1];
		return Timestamp.valueOf(temp);
	}
}
