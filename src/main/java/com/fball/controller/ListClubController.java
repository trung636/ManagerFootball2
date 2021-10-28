package com.fball.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fball.dto.ClubDTO;
import com.fball.service.ClubService;

@Controller
@RequestMapping("/list-club")
public class ListClubController {
	
	@Autowired
	private ClubService clubService;

	@GetMapping()
	public String welcomeAllClub(Model model) {
		List<ClubDTO> clubs = clubService.getAllListClub();
		if(clubs==null) {
			
		}
		model.addAttribute("clubs", clubs);
		return "player/list-club";
	}
}
