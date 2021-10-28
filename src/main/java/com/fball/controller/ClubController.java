package com.fball.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fball.dto.STTClubDTO;
import com.fball.service.ClubService;

@Controller
@RequestMapping("/club")
public class ClubController {
	
	@Autowired
	private ClubService clubService;
	
	@GetMapping("{idClub}")
	public String welcomClub(@PathVariable("idClub") int idClub, Model model) {
		
		List<STTClubDTO> sttClubs = clubService.getListSTTClubById(idClub);
		if(sttClubs==null) {
			
		}
		if(sttClubs.isEmpty()) {
			System.out.println("ooo");
		}
		model.addAttribute("sttClubs", sttClubs);
		return "player/club";
	}
}
