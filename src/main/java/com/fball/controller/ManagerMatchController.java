package com.fball.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fball.dto.MatchSTTClub;
import com.fball.dto.VirtualMatchDTO;
import com.fball.service.ManagerMatchService;
import com.fball.service.VirtualMatchService;

@Controller
@RequestMapping("/manager-match")
public class ManagerMatchController {

	@Autowired
	private ManagerMatchService managerMatchService;
	
	@Autowired
	private VirtualMatchService virtualMatchService;
	
	@GetMapping("/{id}")
	public String viewMatchSTTClub(@PathVariable int id, HttpSession session, Model model) {
		
		List<MatchSTTClub> matchClubs = managerMatchService.getMatchSTTClubById(id);
		
		model.addAttribute("idClub", id);
		model.addAttribute("matchClubs", matchClubs);
		
		return	"manager/match";
	}
	@GetMapping("/virtual-match/{idMatch}")
	public String viewVirtualMatchByIdMatch(@PathVariable("idMatch") int idMatch, Model model) {
		List<VirtualMatchDTO> virtuals = virtualMatchService.getListVirtualMatchByIdMatch(idMatch);
		model.addAttribute("virtuals", virtuals);
		model.addAttribute("idMatch", idMatch);
		return "manager/virtual_match";
	}
}
