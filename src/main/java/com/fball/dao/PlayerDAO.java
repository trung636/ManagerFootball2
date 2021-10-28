package com.fball.dao;

import javax.servlet.http.HttpSession;

import com.fball.dto.AccountDTO;
import com.fball.dto.PlayerDTO;

public interface PlayerDAO {

	String checkAccountPlayer(AccountDTO accountDTO, HttpSession session);

	PlayerDTO getPlayerByEmail(String email);

	String updateProfile(String email, PlayerDTO playerDTO);

	String updatePassword(PlayerDTO player);

	String addPlayer(PlayerDTO playerDTO);

}
