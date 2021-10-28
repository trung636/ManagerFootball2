package com.fball.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fball.dao.ClubDAO;
import com.fball.dto.ClubDTO;
import com.fball.dto.STTClubDTO;
import com.fball.service.ClubService;

@Service
public class ClubServiceImp implements ClubService {
	
	@Autowired
	private ClubDAO clubDAO;

	@Override
	public List<ClubDTO> getAllListClub() {
		List<ClubDTO> clubs =  clubDAO.getAllListClub();
		if(clubs==null) {
			return null;
		}
		return clubs;
	}

	@Override
	public List<STTClubDTO> getListSTTClubById(int idClub) {
		List<STTClubDTO>  sttClub = clubDAO.getListSTTClubById(idClub);
		if(sttClub==null) {
			return null;
		}
		return sttClub;
	}

}
