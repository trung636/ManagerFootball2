package com.fball.service;

import java.util.List;

import com.fball.dto.AccountDTO;
import com.fball.dto.PlayerDTO;
import com.fball.dto.STTClubDTO;

public interface PlayerService {

	PlayerDTO getPlayerByEmail(String email);

	String updateProfile(String email, PlayerDTO playerDTO);

	String changePassword(AccountDTO accountDTO);

	List<STTClubDTO> getListSTTClubById(int idClub);

}
