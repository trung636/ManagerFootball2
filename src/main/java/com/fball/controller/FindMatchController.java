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

import com.fball.dto.MatchSTTClub;
import com.fball.dto.TimeDTO;
import com.fball.service.FindMatchService;
import com.fball.utils.DateTimeUtils;

@Controller
@RequestMapping("/find-match")
public class FindMatchController {
	
	@Autowired
	private FindMatchService findMatch;
	
	
	@GetMapping("/time")
	public String getListMatchByDateAndTime(
			@RequestParam(required = false, defaultValue = "") String time,
			@RequestParam(required = false, defaultValue = "") String date,
			Model model) {
		if(time.equals("")) {
			model.addAttribute("matchClubs", null);
		}else {
			List<MatchSTTClub> matchClubs =findMatch.getListMatchByTime(time, DateTimeUtils.setDate(date));
			model.addAttribute("matchClubs", matchClubs);
		}
		return "find-match-time";
	}
	
	@GetMapping
	public String findMatch() {
		return "find-match";
	}
	@GetMapping("/random")
	public String findMatchRandom() {
		return "find-match-random";
	}
	
	@PostMapping("/time")
	public String findMatchByDateAndTime(
			@ModelAttribute	TimeDTO timeDTO) {
		
			return "redirect:/find-match/time?time="+timeDTO.getTime()+"&date="+timeDTO.getDate();
	}
}
