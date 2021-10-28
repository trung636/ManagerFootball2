package com.fball.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fball.dto.ClubDTO;
import com.fball.dto.STTClubDTO;
import com.fball.service.ManagerClubService;

@Controller
@RequestMapping("/manager-club")
public class ManagerClubController {
	
	@Autowired
	private ManagerClubService managerClubService;
	
	@GetMapping
	private String managerClub(HttpSession session, Model model ) {
		
		if(session.getAttribute("email")==null) {
			return "redirect:/login";
		}
		ClubDTO clubDTO = managerClubService.getClubByEmail(session.getAttribute("email").toString());
		if(clubDTO==null) {
			
		}
		Integer a = (Integer) session.getAttribute("idClub");
		List<STTClubDTO> sttClubs = managerClubService.getListSTTClubById(a);
		
		model.addAttribute("clubDTO", clubDTO);
		model.addAttribute("sttClubs", sttClubs);
		return "manager/club";
	}
	
	@PostMapping("/add")
	private String addSTTClub(@ModelAttribute STTClubDTO sttClubDTO, HttpSession session, RedirectAttributes redirectAttributes) {
		
		if(session.getAttribute("email")==null) {
			return "redirect:/login";
		}
		sttClubDTO.setEmail(session.getAttribute("email").toString());
		String rs = managerClubService.addSTTClub(sttClubDTO);
		if(rs.equals("nameFail")) {
			redirectAttributes.addFlashAttribute("nameFail", true);
			return "redirect:/manager-club/add";
		}
		
		redirectAttributes.addFlashAttribute("addSuccess", true);
		return"redirect:/manager-club";
	}
	
	
	@GetMapping("/add")
	private String viewAddSTTClub() {
		return "view_add_sttClub";
	}
	
}
