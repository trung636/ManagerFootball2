package com.fball.utils;	

import java.time.LocalTime;
import java.util.List;
import java.util.StringJoiner;

import com.fball.dto.DataMatchPlayerDTO;
import com.fball.dto.MatchSTTClub;
import com.fball.dto.VirtualMatchDTO;


public class Utils {
	
	public static String checkVirtualUtils(List<VirtualMatchDTO>  virtuals,String email) {
		StringJoiner str = new StringJoiner(",");
		for(VirtualMatchDTO i : virtuals) {
			str.add(i.getList());
		}
		String[] check = str.toString().split(",");
		for(String i : check) {
			if(i.equals(email)) {
				return "fail";
			}
		}
		return "success";
	}

	public static String checkDataMatchPlayerInDate(List<DataMatchPlayerDTO> datas, MatchSTTClub match) {
		
		if(datas.size()==5) {
			return "fail";
		}
		
		String rs = DateTimeUtils.checkTimeStart(match);
		if(rs.equals("fail")) {
			return "fail";
		}
//		check time
		LocalTime	start = LocalTime.parse(((MatchSTTClub)match).getTimeStart());
		LocalTime	end = LocalTime.parse(((MatchSTTClub)match).getTimeEnd());
		for(DataMatchPlayerDTO i : datas) {
			LocalTime s = LocalTime.parse(i.getTimeStart());
			LocalTime e = LocalTime.parse(i.getTimeEnd());
			if(e.minusMinutes(1).isBefore(start)==true ) {
			
			}else if(s.plusMinutes(1).isAfter(end)==true) {
				
			}else {
				return "fail";
			}
		}
		return "success";
		
	}

}
