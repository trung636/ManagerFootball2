package com.fball.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fball.dao.ManagerDAO;
import com.fball.dto.MatchSTTClub;
import com.fball.service.ManagerService;

@Repository
public class ManagerServiceImp implements  ManagerService {

	@Autowired
	private ManagerDAO managerDAO;

	@Override
	public List<MatchSTTClub> getMatchSTTClubById(int idStt) {
		List<MatchSTTClub> rs = managerDAO.getMatchSTTClubById(idStt);
		if(rs==null) {
			return null;
		}
		return rs;
	}

}
