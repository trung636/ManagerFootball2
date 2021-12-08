package com.fball.cron;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.fball.dao.ManagerDAO;
import com.fball.dto.DateDTO;
import com.fball.dto.MatchSTTClub;
import com.fball.dto.STTClubDTO;
import com.fball.utils.DateTimeUtils;

@Configuration
@EnableScheduling
public class CronSystems {
	
	@Autowired
	private CronDAO cron;
	
	@Autowired
	private ManagerDAO manager;
	
//	mỗi ngày vào lúc 0h chạy một lần
	@Scheduled(cron = "0 0 0 * * *", zone = "Asia/Saigon")
//	24h chạy một lần
//	@Scheduled(fixedRate = 24*60*60*1000)
	public void setTimeClubToDay() {
		DateDTO date = DateTimeUtils.setDate("afterTomorrow");
		List<STTClubDTO> list = manager.getAllListSTTClub();
		for(STTClubDTO i : list) {
			MatchSTTClub match = new MatchSTTClub();
			match.setIdSTTClub(i.getId());
			match.setTimeStart("08:00");
			match.setTimeEnd("10:00");
			match.setRs("");
			match.setState(0);
			match.setDate(date.getDate());
			match.setMonth(date.getMonth());
			match.setYear(date.getYear());
			cron.updateMatchSTTCLub(match);
			
			match.setTimeStart("10:00");
			match.setTimeEnd("12:00");
			cron.updateMatchSTTCLub(match);
			
			match.setTimeStart("12:00");
			match.setTimeEnd("14:00");
			cron.updateMatchSTTCLub(match);
			
			match.setTimeStart("14:00");
			match.setTimeEnd("16:00");
			cron.updateMatchSTTCLub(match);
			
			match.setTimeStart("16:00");
			match.setTimeEnd("18:00");
			cron.updateMatchSTTCLub(match);
			
			match.setTimeStart("18:00");
			match.setTimeEnd("20:00");
			cron.updateMatchSTTCLub(match);
			
			match.setTimeStart("20:00");
			match.setTimeEnd("22:00");
			cron.updateMatchSTTCLub(match);
			
			match.setTimeStart("22:00");
			match.setTimeEnd("24:00");
			cron.updateMatchSTTCLub(match);
		}
	}
	@Scheduled(cron = "0 0 * * * *", zone = "Asia/Saigon")
	public void setTimeEndMatch() {
		
//		LocalDateTime dateTime = LocalDateTime.now(ZoneId.of("Asia/Ho_Chi_Minh"));
		
		
	}
	
	

}
