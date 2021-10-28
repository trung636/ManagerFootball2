package com.fball.service.imp;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fball.dao.ManagerMatchDAO;
import com.fball.dto.MatchSTTClub;
import com.fball.service.ManagerMatchService;

@Service
public class ManagerMatchServiceImp implements  ManagerMatchService {
	
	@Autowired
	public ManagerMatchDAO managerMatchDAO;

	@Override
	public List<MatchSTTClub> getMatchSTTClubById(int id) {
		List<MatchSTTClub> rs = managerMatchDAO.getMatchSTTClubById(id);
		if(rs==null) {
			return null;
		}
		return rs;
	}

	@Override
	public String bookMatch(int idMatch, HttpSession session) {
		MatchSTTClub match = managerMatchDAO.getMatchSTTClubByIdMatch(idMatch);
		if(match==null) {
			return null;
		}
		match.setRs(session.getAttribute("email").toString());
		String rs = managerMatchDAO.bookMatch(match);
		return rs;
		
	}

}
