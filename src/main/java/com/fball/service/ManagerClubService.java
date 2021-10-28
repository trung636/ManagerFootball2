package com.fball.service;

import java.util.List;

import com.fball.dto.ClubDTO;
import com.fball.dto.STTClubDTO;

public interface ManagerClubService {

	ClubDTO getClubByEmail(String email);
	
	List<STTClubDTO> getListSTTClubById(int idClub);

	String addSTTClub(STTClubDTO sttClubDTO);

}
