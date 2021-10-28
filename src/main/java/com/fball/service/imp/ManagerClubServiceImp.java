package com.fball.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fball.dao.ManagerClubDAO;
import com.fball.dto.ClubDTO;
import com.fball.dto.STTClubDTO;
import com.fball.service.ManagerClubService;

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
		if(list==null) {
			return null;
		}
		for(STTClubDTO i :list) {
			if(i.getName().equals(sttClubDTO.getName())) {
				return "nameFail";
			}
		}
		String rs = managerClubDAO.addSTTClub(sttClubDTO);
		
		return rs;
	}
}
