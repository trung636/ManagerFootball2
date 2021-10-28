package com.fball.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import com.fball.dto.DateDTO;
import com.fball.dto.MatchSTTClub;

public class DateTimeUtils {
	
		
	public static DateDTO setDate(String date) {
		
		LocalDate localDate = LocalDate.now(ZoneId.of("Asia/Ho_Chi_Minh"));
		DateDTO dto = new DateDTO();
		switch (date) {
		case "today":
			dto.setDate(localDate.getDayOfMonth());
			dto.setMonth(localDate.getMonthValue());
			dto.setYear(localDate.getYear());
			return dto;
		case "tomorrow":
			LocalDate tmr = localDate.plusDays(1);
			dto.setDate(tmr.getDayOfMonth());
			dto.setMonth(tmr.getMonthValue());
			dto.setYear(tmr.getYear());
			return dto;
		case "afterTomorrow":
			LocalDate aftmr = localDate.plusDays(2);
			dto.setDate(aftmr.getDayOfMonth());
			dto.setMonth(aftmr.getMonthValue());
			dto.setYear(aftmr.getYear());
			return dto;
		default:
			dto.setDate(localDate.getDayOfMonth());
			dto.setMonth(localDate.getMonthValue());
			dto.setYear(localDate.getYear());
			return dto;
		}
		
	}
	
	public static String checkTimeStart(MatchSTTClub match) {
		DateTimeFormatter  formatter = DateTimeFormatter.ofPattern("HH:mm dd:MM:yyyy");
		String rs =match.getTimeStart()+" "+ match.getDate() + ":" + match.getMonth() + ":" + match.getYear();
		LocalDateTime local = LocalDateTime.now(ZoneId.of("Asia/Ho_Chi_Minh"));
		LocalDateTime time = LocalDateTime.parse(rs, formatter);
		if(time.isAfter(local) == false) {
			return "fail";
		}
		return "success";
	}

	public static String checkTimeCancel(MatchSTTClub match) {
		
		DateTimeFormatter  formatter = DateTimeFormatter.ofPattern("HH:mm dd:MM:yyyy");

		String rs =match.getTimeStart()+" "+ match.getDate() + ":" + match.getMonth() + ":" + match.getYear();
		LocalDateTime local = LocalDateTime.now(ZoneId.of("Asia/Ho_Chi_Minh"));
		LocalDateTime time = LocalDateTime.parse(rs, formatter).minusHours(2);
		if(time.isAfter(local) == false) {
			return "fail";
		}
		return "success";
	}
}
