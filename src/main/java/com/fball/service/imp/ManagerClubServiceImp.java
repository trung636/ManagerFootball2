package com.fball.service.imp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fball.dao.ManagerClubDAO;
import com.fball.dto.ClubDTO;
import com.fball.dto.DateDTO;
import com.fball.dto.MatchSTTClub;
import com.fball.dto.STTClubDTO;
import com.fball.service.ManagerClubService;
import com.fball.utils.DateTimeUtils;

@Service
public class ManagerClubServiceImp implements ManagerClubService {

	@Autowired
	private ManagerClubDAO managerClubDAO;
	
	
	@Override
	public ClubDTO getClubByEmail(String email) {
		ClubDTO clubDTO = managerClubDAO.getClubByEmail(email);
		if(clubDTO==null) {
			return null;
		}
		return clubDTO;
	}

	@Override
	public List<STTClubDTO> getListSTTClubById(int idClub) {
		List<STTClubDTO>  sttClub = managerClubDAO.getListSTTClubById(idClub);
		if(sttClub==null) {
			return null;
		}
		return sttClub;
	}

	@Override
	public String addSTTClub(STTClubDTO sttClubDTO) {
		
		List<STTClubDTO> list = managerClubDAO.getListSTTClubById(sttClubDTO.getId());
		
		for(STTClubDTO i :list) {
			if(i.getName().equals(sttClubDTO.getName())) {
				return "nameFail";
			}
		}
		
		String rs = managerClubDAO.addSTTClub(sttClubDTO);
		STTClubDTO club = managerClubDAO.getSTTCLubByName(sttClubDTO.getName(), sttClubDTO.getIdClub());
		List<DateDTO> date = new ArrayList<>();
		date.add(DateTimeUtils.setDate("today"));
		date.add(DateTimeUtils.setDate("tomorrow"));
		date.add(DateTimeUtils.setDate("afterTomorrow"));
		if(rs.equals("success")) {
			for(DateDTO d : date) {
				MatchSTTClub match = new MatchSTTClub();
				match.setIdSTTClub(club.getId());
				match.setTimeStart("08:00");
				match.setTimeEnd("10:00");
				match.setRs("");
				match.setState(0);
				match.setDate(d.getDate());
				match.setMonth(d.getMonth());
				match.setYear(d.getYear());
				managerClubDAO.updateMatchSTTCLub(match);
				
				match.setTimeStart("10:00");
				match.setTimeEnd("12:00");
				managerClubDAO.updateMatchSTTCLub(match);
				
				match.setTimeStart("12:00");
				match.setTimeEnd("14:00");
				managerClubDAO.updateMatchSTTCLub(match);
				
				match.setTimeStart("14:00");
				match.setTimeEnd("16:00");
				managerClubDAO.updateMatchSTTCLub(match);
				
				match.setTimeStart("16:00");
				match.setTimeEnd("18:00");
				managerClubDAO.updateMatchSTTCLub(match);
				
				match.setTimeStart("18:00");
				match.setTimeEnd("20:00");
				managerClubDAO.updateMatchSTTCLub(match);
				
				match.setTimeStart("20:00");
				match.setTimeEnd("22:00");
				managerClubDAO.updateMatchSTTCLub(match);
				
				match.setTimeStart("22:00");
				match.setTimeEnd("24:00");
				managerClubDAO.updateMatchSTTCLub(match);
			}
		}
		
		return rs;
	}
}
