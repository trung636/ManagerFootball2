package com.fball.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fball.dto.ClubDTO;
import com.fball.dto.FindClubDTO;
import com.fball.service.ClubService;

@Controller
@RequestMapping("/list-club")
public class ListClubController {
	
	@Autowired
	private ClubService clubService;

	@GetMapping()
	public String welcomeAllClub(
			@RequestParam(required = false, defaultValue = "") String district,
			Model model) {
		if(!district.equals("")) {
			List<ClubDTO> clubs = clubService.getListClubByDistrict(district);
			model.addAttribute("clubs", clubs);
			return "player/list-club";
		}
			List<ClubDTO> clubs = clubService.getAllListClub();
			model.addAttribute("clubs", clubs);
		
		return "player/list-club";
	}
	@PostMapping
	public String findClubByDistrict(@ModelAttribute FindClubDTO findCLubDTO) {
		
		return "redirect:/list-club?district="+findCLubDTO.getDistrict();
	}
}
