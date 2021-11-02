package com.fball.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fball.dto.CardPlayer;
import com.fball.service.FriendService;
import com.fball.service.imp.FriendServiceImp;

@Controller
@RequestMapping("/friend")
public class FriendController {

	@Autowired
	private FriendService friendService;

	@GetMapping
	public String friend(Model model, HttpSession session,
			@RequestParam(name = "friend", required = false, defaultValue = "myFriend") String friend) {

		if (friend.equals("myFriend")) {
			List<CardPlayer> list = friendService.getMyFriend(session.getAttribute("email").toString());
			model.addAttribute("list", list);
			model.addAttribute("title", "My Friend");
		}
		if (friend.equals("newFriend")) {
			List<CardPlayer> list = friendService.findNewFriend(session.getAttribute("email").toString());
			model.addAttribute("list", list);
			model.addAttribute("title", "New Friend");
		}
		if (friend.equals("acceptFriend")) {
			List<CardPlayer> list = friendService.getAcceptFriend(session.getAttribute("email").toString());
			model.addAttribute("list", list);
			model.addAttribute("title", "Contact");
		}
		model.addAttribute("friend", friend);
		return "team";
	}

	@GetMapping("/contact-player/{id}")
	public String playerContact(@PathVariable int id, HttpSession session, RedirectAttributes redirectAttributes) {

		if (session.getAttribute("email") == null) {
			return "redirect:/login";
		}
		friendService.playerContact(id, session, FriendServiceImp.Wait);
		redirectAttributes.addFlashAttribute("contactSuccess", true);

		return "redirect:/friend";
	}

	@GetMapping("/accept-contact/{id}")
	public String acceptContact(@ModelAttribute("id") int id, HttpSession session,
			RedirectAttributes redirectAttributes) {
		if (session.getAttribute("email") == null) {
			return "redirect:/login";
		}

		friendService.stateContact(id, session, FriendServiceImp.Enable);
		redirectAttributes.addFlashAttribute("acceptSuccess", true);

		return "redirect:/friend";
	}

	@GetMapping("/delete-contact/{id}")
	public String cancelContact(@PathVariable("id") int id, HttpSession session,
			RedirectAttributes redirectAttributes) {

		if (session.getAttribute("email") == null) {
			return "redirect:/login";
		}

		friendService.stateContact(id, session, FriendServiceImp.Unable);
		redirectAttributes.addFlashAttribute("cancelSuccess", true);

		return "redirect:/friend";
	}

	@GetMapping("/block-contact/{id}")
	public String blockContact(@PathVariable int id, HttpSession session, RedirectAttributes redirectAttributes) {
		
		if (session.getAttribute("email") == null) {
			return "redirect:/login";
		}
		friendService.stateContact(id, session, FriendServiceImp.Block);
		redirectAttributes.addFlashAttribute("blockSuccess", true);
		return "redirect:/friend";
	}
}
