package com.fball.dao;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.fball.dto.AccountDTO;
import com.fball.dto.ClubDTO;
import com.fball.dto.STTClubDTO;

public interface ClubDAO {

	String checkAccountClub(AccountDTO accountDTO, HttpSession session);

	ClubDTO getClubByEmail(String email);

	String addClub(ClubDTO clubDTO);

	List<ClubDTO> getAllListClub();
	
	List<STTClubDTO> getListSTTClubById(int idClub);

}
