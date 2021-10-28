package com.fball.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fball.dto.AccountDTO;
import com.fball.dto.PlayerDTO;
import com.fball.service.PlayerService;

@Controller
public class PlayerController {
	
	@Autowired
	private PlayerService playerService;
	
	@GetMapping("profile")
	public String profile(HttpSession session,Model model) {
		if(session.getAttribute("email")==null) {
			return "redirect:/login";
		}
		String email = session.getAttribute("email").toString();
		PlayerDTO player = playerService.getPlayerByEmail(email);
		if(player==null) {
			
		}
		model.addAttribute("players", player);
		return "profile";
	}
	
	@PostMapping("update_profile")
	private String updateProfile(@ModelAttribute PlayerDTO playerDAO, HttpSession session, RedirectAttributes redirectAttributes) {
		String email =session.getAttribute("email").toString();
		String result = playerService.updateProfile(email, playerDAO);
		if(result.equals("fail")) {
		}
		redirectAttributes.addFlashAttribute("updateSuccess", true);
		return "redirect:/profile";
	}
	
	@PostMapping("change_password")
	public String changePassword(@ModelAttribute AccountDTO accountDTO, HttpSession session, RedirectAttributes redirectAttributes) {
		
		if (session.getAttribute("email") == null) {
            return "redirect:/login";
        }
		accountDTO.setEmail(session.getAttribute("email").toString());
		String player = playerService.changePassword(accountDTO);
		if(player.equals("fail")) {
			redirectAttributes.addFlashAttribute("changePasswordFail", true);
		}else {
			redirectAttributes.addFlashAttribute("changePasswordSuccess", true);
		}
		return "redirect:/profile";
	}
//	@GetMapping("club/{idStt}")
//	public String matchClub(@PathVariable("idClub") int idStt, 
//			 Model model) {
//		
//		List<MatchSTTClub> matchClubs = managerService.getMatchSTTClubById(idStt);
//		model.addAttribute("matchClubs", matchClubs);
//		return "player/match";
//	}
	
//	@GetMapping("match/{idMatch}")
//	public String virtualMatch(@PathVariable("idMatch") int idMatch, @RequestParam int idVirtual) {
//		
//	}
}
