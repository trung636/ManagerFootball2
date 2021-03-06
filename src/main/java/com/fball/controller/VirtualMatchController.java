package com.fball.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fball.dto.VirtualMatchDTO;
import com.fball.service.VirtualMatchService;

@Controller
@RequestMapping("/virtual-match")
public class VirtualMatchController {
	
	@Autowired
	private VirtualMatchService virtualMatchService;
	
	@GetMapping
	public String virtualmatch() {
		return "redirect:/club";
	}
	
	@GetMapping("{idMatch}")
	private String viewVirtualMatch(
			@PathVariable("idMatch") int idMatch,
			@RequestParam(required = false) String idStt,
			Model model,
			RedirectAttributes redirectAttributes) {
		
		List<VirtualMatchDTO> virtuals = virtualMatchService.getListVirtualMatchByIdMatch(idMatch);
		
		if(virtuals==null) {
			redirectAttributes.addFlashAttribute("timeFail", true);
			return "redirect:/match?idStt="+idStt+"&date=today";
		}
		if(virtuals.isEmpty()) {
			virtuals = null;
		}
		model.addAttribute("virtuals", virtuals);
		model.addAttribute("idMatch", idMatch);
		
		return "player/virtual_match";
	}
	
	@GetMapping("/new")
	private String newVirtualMatch(@RequestParam int idMatch, HttpSession session, RedirectAttributes rediresAttributes) {
		
		if(session.getAttribute("email")==null) {
			return "redirect:/login";
		}
		String result = virtualMatchService.newVirtualMatchInId(idMatch, session.getAttribute("email").toString());
		if(result.equals("fail") || result.equals("timeFail") ) {
			rediresAttributes.addFlashAttribute("fail", true);
		}else {
			rediresAttributes.addFlashAttribute("success", true);
		}
		return "redirect:/virtual-match/"+idMatch;
	}
	
	@GetMapping("/join")
	private String joinVirtualMatch(@RequestParam(required = false, defaultValue  = "0") int idMatch, @RequestParam int idVirtual,HttpSession session, 
			RedirectAttributes redirectAttributes) {
		
		
		if(session.getAttribute("email")==null) {
			return "redirect:/login";
		}
		String result = virtualMatchService.joinVirtualMatchInId(idVirtual, session.getAttribute("email").toString());
		if(result.equals("joinFail")) {
			redirectAttributes.addFlashAttribute("joinFail", true);
		}else {
			redirectAttributes.addFlashAttribute("joinSuccess", true);
		}
		if(idMatch==0) {
			return "redirect:/notifi";
		}else {
			return "redirect:/virtual-match/"+idMatch;
		}
	}
	
	@GetMapping("/cancel")
	private String cancelVirtualMatch(
			@RequestParam(required = false) int idMatch, 
			@RequestParam int idVirtual,
			HttpSession session, 
			RedirectAttributes redirectAttributes) {
		
		if(session.getAttribute("email")==null) {
			return "redirect:/login";
		}
		String result = virtualMatchService.cancelVirtualMatchInId(idVirtual, session.getAttribute("email").toString());
		
		if(result.equals("cancelFail")) {
			redirectAttributes.addFlashAttribute("cancelFail", true);
		}else {
			redirectAttributes.addFlashAttribute("cancelSuccess", true);
		}
		return "redirect:/virtual-match/"+idMatch;
	}
	
	
	@GetMapping("/invite/{idVirtual}")
	public String sendRequest(
					@PathVariable(name = "idVirtual") int idVirtual,
					@RequestParam(required = false) String idMatch,
					@RequestParam(required = false, defaultValue = "all") String invite,
					HttpSession session,
					RedirectAttributes redirectAttributes) {
		
		if(session.getAttribute("email")==null) {
			return "redirect/login";
		}
		String rs = virtualMatchService.inviteRequestAll(session, idVirtual);
		if(rs.equals("inviteFail")){
			redirectAttributes.addFlashAttribute("inviteFail", true);
		}
		if(rs.equals("timeFail")) {
			redirectAttributes.addFlashAttribute("timeFail", true);

		}else {
			
			redirectAttributes.addFlashAttribute("inviteSuccess", true);
		}
		
		return "redirect:/virtual-match/"+idMatch;
	}
	
}
