package com.fball.service.imp;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.fball.dao.ClubDAO;
import com.fball.dao.NotifiDAO;
import com.fball.dao.PlayerDAO;
import com.fball.dto.AccountDTO;
import com.fball.dto.ClubDTO;
import com.fball.dto.NotifiDTO;
import com.fball.dto.PlayerDTO;
import com.fball.service.LoginService;

@Service
public class LoginServiceImp implements LoginService {

	@Autowired
	private PlayerDAO playerDAO;
	
	@Autowired
	private ClubDAO clubDAO;
	
	@Autowired
	private NotifiDAO notifiDAO;
	
	
	@Override
	public String checkLogin(AccountDTO accountDTO, HttpSession session) {
		
		PlayerDTO user = playerDAO.checkAccountPlayer(accountDTO,session);
		
		if(user == null) {
			return "fail";
		}
		List<GrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority("ROLE_"+user.getRolePlayer()));

		UsernamePasswordAuthenticationToken principal = new UsernamePasswordAuthenticationToken(accountDTO.getEmail(), accountDTO.getPassword(), authorities);
	    SecurityContext sc = SecurityContextHolder.getContext();
	    sc.setAuthentication(principal);
		return "success";
	}


	@Override
	public String newPlayer(PlayerDTO playerDTO) {
		
		PlayerDTO player = playerDAO.getPlayerByEmail(playerDTO.getEmail());
		ClubDTO club = clubDAO.getClubByEmail(playerDTO.getEmail());
		
		if(player!=null) {
			return "failEmail";
		}
		if(club != null) {
			return "failEmail";
		}
		playerDAO.addPlayer(playerDTO);
		
		return "success";
	}


	@Override
	public String newClub(ClubDTO clubDTO) {
		PlayerDTO player = playerDAO.getPlayerByEmail(clubDTO.getEmail());
		ClubDTO club = clubDAO.getClubByEmail(clubDTO.getEmail());
		if(player!=null) {
			return "failEmail";
		}
		if(club != null) {
			return "failEmail";
		}
		clubDAO.addClub(clubDTO);
		
		return "success";
	}


	@Override
	public List<NotifiDTO> getAllNotifi(String email) {
		List<NotifiDTO> notifi = notifiDAO.getAllNotifi(email);
		
		return notifi;
	}

}
