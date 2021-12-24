package com.fball.service.imp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ControllerAdvice;

import com.fball.dao.ClubDAO;
import com.fball.dto.ClubDTO;
import com.fball.dto.STTClubDTO;
import com.fball.service.ClubService;

@ControllerAdvice
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

	@Override
	public List<ClubDTO> getListClubByDistrict(String district) {
		List<ClubDTO> rs =  getAllListClub();
		List<ClubDTO> clubs = new ArrayList<>();
		for(ClubDTO i : rs) {
			if(i.getDistrict().equals(district)) {
				clubs.add(i);
			}
		}
		return clubs;
		
	}

}
