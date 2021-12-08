package com.fball.service.imp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fball.dao.FindMatchDAO;
import com.fball.dto.DateDTO;
import com.fball.dto.MatchSTTClub;
import com.fball.service.FindMatchService;
import com.fball.utils.DateTimeUtils;

@Service
public class FindMatchServiceImp implements FindMatchService {
	
	@Autowired
	private FindMatchDAO findMatchDAO;

	@Override
	public List<MatchSTTClub> getMatchByDateAndTime(DateDTO date, String time) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<MatchSTTClub> getListMatchByTime(String time, DateDTO date) {
		List<MatchSTTClub> rs = findMatchDAO.getListMatchByTime(date);
		List<MatchSTTClub> matchClubs = new ArrayList<MatchSTTClub>();
		for(MatchSTTClub i :rs) {
			String check = DateTimeUtils.filter(i.getTimeStart(), time);
			if(check.equals("success")) {
				matchClubs.add(i);
			}
		}
		return matchClubs;
	}


}
