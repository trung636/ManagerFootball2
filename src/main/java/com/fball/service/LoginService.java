package com.fball.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.fball.dto.AccountDTO;
import com.fball.dto.ClubDTO;
import com.fball.dto.NotifiDTO;
import com.fball.dto.PlayerDTO;

public interface LoginService {

	String checkLogin(AccountDTO accountDTO, HttpSession session);

	String newPlayer(PlayerDTO playerDTO);

	String newClub(ClubDTO clubDTO);

	List<NotifiDTO> getAllNotifi(String email);

}
