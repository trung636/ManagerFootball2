package com.fball.dao;

import java.util.List;

import com.fball.dto.ClubDTO;
import com.fball.dto.STTClubDTO;

public interface ManagerClubDAO {

	ClubDTO getClubByEmail(String email);

	List<STTClubDTO> getListSTTClubById(int idClub);

	String addSTTClub(STTClubDTO sttClubDTO);

}
