package com.fball.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fball.dto.ClubDTO;
import com.fball.dto.DataMatchPlayerDTO;
import com.fball.service.MyMatchService;

@Controller
@RequestMapping("/my-match")
public class MyMatchController {
	
	@Autowired
	public MyMatchService myMatchService;
	
	@GetMapping
	public String myMatch(HttpSession session, Model model) {
		
		List<DataMatchPlayerDTO> match =myMatchService.getMyMatch(session.getAttribute("email").toString());
		model.addAttribute("match", match);
		return "my-match";
	}
	

}
