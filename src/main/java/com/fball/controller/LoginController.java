package com.fball.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fball.dto.AccountDTO;
import com.fball.dto.ClubDTO;
import com.fball.dto.NotifiDTO;
import com.fball.dto.PlayerDTO;
import com.fball.service.LoginService;

@Controller
public class LoginController {
	
	@Autowired
	private LoginService loginService;
	
	@GetMapping("/")
	public String welcome() {
		return "redirect:/login";
	}
	
	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
	@PostMapping("/check_login")
	public String checkLogin(@ModelAttribute AccountDTO accountDTO, HttpSession session) {
		
		String result = loginService.checkLogin(accountDTO, session);
		if(result.equals("fail")) {
			return "redirect:/login";
		}
		if(session.getAttribute("namePlayer")!=null) {
			return "redirect:/home";			
		}
		return "redirect:/notifi";
	}
	
	@GetMapping("notifi")
	public String notifi(HttpSession session, Model model) {
		
		if(session.getAttribute("email")==null) {
			return "redirect:/login";
		}
		List<NotifiDTO> notifi = loginService.getAllNotifi(session.getAttribute("email").toString());
		model.addAttribute("notifi", notifi);
		return "notifi";
	}
	@GetMapping("/logout")
	private String logout(HttpServletRequest request) {
		HttpSession session = request.getSession();
        session.invalidate();
        return "redirect:/login";
	}
	
	@GetMapping("/registy")
	private String registy(@RequestParam(name = "name",required = false, defaultValue  = "") String name, 
							Model model ) {
		if(name.equals("")||name.equals("registyPlayer") || name.equals( "registyClub")) {
			System.out.println("aaa");
		}else {
			name = "";
			model.addAttribute("pathFail", "aaa");
		}
		model.addAttribute("name", name);
		return "registy";
	}
	
	@PostMapping("/new_player")
	private String newPlayer(@ModelAttribute PlayerDTO playerDTO, RedirectAttributes redirectAttributes) {
		
		String result = loginService.newPlayer(playerDTO);
		if(result.equals("failEmail")) {
			redirectAttributes.addFlashAttribute("emailFail", true);
			return "redirect:/registy";
		}
		redirectAttributes.addFlashAttribute("registySuccess", true);
		return "redirect:/login?name=registyPlayer";
	}
	
	@PostMapping("/new_club")
	private String newClub(@ModelAttribute ClubDTO clubDTO, RedirectAttributes redirectAttributes){
		String result = loginService.newClub(clubDTO);
		if(result.equals("failEmail")) {
			redirectAttributes.addFlashAttribute("emailFail", true);
			return "redirect:/registy?name=registyClub";
		}
		redirectAttributes.addFlashAttribute("registySuccess", true);
		return "redirect:/login";
	}
	
//	@PostMapping("/check_email")
//	private String checkEmail(@RequestParam String email) {
//		
//		return email;
//		
//	}
}
