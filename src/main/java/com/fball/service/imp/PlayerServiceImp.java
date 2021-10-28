package com.fball.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fball.dao.PlayerDAO;
import com.fball.dto.AccountDTO;
import com.fball.dto.PlayerDTO;
import com.fball.dto.STTClubDTO;
import com.fball.service.PlayerService;

@Service
public class PlayerServiceImp implements PlayerService {

	@Autowired
	private PlayerDAO playerDAO;
	
	@Override
	public PlayerDTO getPlayerByEmail(String email) {
		PlayerDTO player =playerDAO.getPlayerByEmail(email);
		if(player==null) {
			return null;
		}
		return player;
	}

	@Override
	public String updateProfile(String email, PlayerDTO playerDTO) {
		String result = playerDAO.updateProfile(email, playerDTO);
		return result;
	}

	@Override
	public String changePassword(AccountDTO accountDTO) {
		
		PlayerDTO player = playerDAO.getPlayerByEmail(accountDTO.getEmail());
		if(player==null) {
			return "fail";
		}
		if(!accountDTO.getPassword().equals(player.getPassword())) {
			return "fail";
		}
		player.setPassword(accountDTO.getCurrentPassword());
		
		return playerDAO.updatePassword(player);
	}

	@Override
	public List<STTClubDTO> getListSTTClubById(int idClub) {
		// TODO Auto-generated method stub
		return null;
	}

}
