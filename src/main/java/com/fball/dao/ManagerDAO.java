package com.fball.dao;

import java.util.List;

import com.fball.dto.MatchSTTClub;
import com.fball.dto.STTClubDTO;

public interface ManagerDAO {

	String addSTTClub(STTClubDTO sttClubDTO);

	List<STTClubDTO> getAllListSTTClub();

	List<MatchSTTClub> getMatchSTTClubById(int id);

	List<STTClubDTO> getSTTClubById(int idClub);


}
