package com.fball.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fball.dto.MatchSTTClub;
import com.fball.service.MatchService;
import com.fball.utils.DateTimeUtils;

@Controller
@RequestMapping("/match")
public class MatchController {
	
	@Autowired
	private MatchService matchService;
	
	@GetMapping()
	public String welcomeMatch(
			@RequestParam(name="idStt", required = false, defaultValue = "0") int idStt,
			@RequestParam(name="date", required = false, defaultValue = "today") String date, 
			Model model) {
		
		if(idStt==0) {
			List<MatchSTTClub> matchClubs = matchService.getAllMatch(DateTimeUtils.setDate(date));
			if(matchClubs==null) {
			}
			model.addAttribute("matchClubs", matchClubs);
		}else {
			List<MatchSTTClub> matchClubs = matchService.getListMatchSTTClubByIdStt(idStt, DateTimeUtils.setDate(date));
			if(matchClubs==null) {
			}
			model.addAttribute("matchClubs", matchClubs);
			model.addAttribute("date", date);
		}
		model.addAttribute("idStt", idStt);
		return	"player/match";
		}
		
	@GetMapping("/book")
	public String bookMatch(
			@RequestParam(required = false, defaultValue = "0") int idStt,
			@RequestParam(name="date", required = false, defaultValue = "today") String date,
			@RequestParam int idMatch, 
			HttpSession session, 
			RedirectAttributes redirectAttributes) {
		String rs = matchService.bookMatch(idMatch, session);
		if(rs.equals("bookFail")) {
			redirectAttributes.addFlashAttribute("bookFail", true);
		}else if(rs.equals("timeFail")){
			redirectAttributes.addFlashAttribute("timeFail", true);
		}else {
			redirectAttributes.addFlashAttribute("bookSuccess", true);
		}
		if(idStt==0) {
			return "redirect:/match";
		}
		return "redirect:/match?idStt="+idStt+"&date="+date;
	}

	@GetMapping("/cancel")
	public String cancelMatch(
			@RequestParam(required = false, defaultValue = "0") int idStt,
			@RequestParam(name="date", required = false, defaultValue = "today") String date,
			@RequestParam int idMatch, 
			HttpSession session, 
			RedirectAttributes redirectAttributes) {
		String rs = matchService.cancelMatch(idMatch, session);
		
		if(rs.equals("accountFail")) {
			redirectAttributes.addFlashAttribute("accountFail", true);
		}
		
		if(rs.equals("timeFail")) {
			redirectAttributes.addFlashAttribute("timeFail", true);
		}
		
		if(idStt==0) {
				return "redirect:/match";
		}
		return "redirect:/match?idStt="+idStt+"&date="+date;
	}
	
}
