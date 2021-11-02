package com.fball.service.imp;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fball.dao.FriendDAO;
import com.fball.dto.CardPlayer;
import com.fball.dto.Contact;
import com.fball.service.FriendService;

@Service
public class FriendServiceImp implements FriendService {
	
	public static final int Wait = 1;
	public static final int Enable= 2;
	public static final int Unable= 3;
	public static final int Block =4;
	
	@Autowired
	private FriendDAO friendDAO;

	@Override
	public List<CardPlayer> getMyFriend(String email) {
		
		List<CardPlayer> teams = new ArrayList<>();
		
//		check your request == if(request == true){do not}
		List<Contact> create = friendDAO.getCreateContacted(email);
		if (create != null) {
			for (Contact cr : create) {
				if (cr.getState() == Enable) {
					teams.add(friendDAO.getCardPlayer(cr.getCreateContact()));
				} else {
					System.out.println("sai cr rồi nha!!!");
				}
			}
		}
//		check my request == if(request == true){do not}
		List<Contact> agree = friendDAO.getAgreeContact(email);
		if (create != null) {
			for (Contact cr : agree) {
				if (cr.getState() == Enable) {
					teams.add(friendDAO.getCardPlayer(cr.getAgreeContact()));
				} else {
					System.out.println("sai cr rồi nha!!!");
				}
			}
		}
		
		if(teams.isEmpty()){
			return null;
		}
		return teams;
		
	}

	@Override
	public List<CardPlayer> findNewFriend(String email) {
		
		List<CardPlayer> teams = friendDAO.getAllPlayer(email);

		List<Contact> agree = friendDAO.getAgreeContact(email);
		if (agree != null) {
			for (Contact i : agree) {
				for (int j = teams.size() - 1; j >= 0; j--) {
					if (i.getAgreeContact().equals(teams.get(j).getEmail())) {
						teams.remove(j);
					} else {
						System.out.println();
					}
				}

			}
		}

		List<Contact> create = friendDAO.getCreateContacted(email);
		if (create != null) {
			for (Contact i : create) {
				for (int j = teams.size() - 1; j >= 0; j--) {
					if (i.getCreateContact().equals(teams.get(j).getEmail())) {
						teams.remove(j);
					} else {
						System.out.println();
					}
				}

			}
		}
		if(teams.isEmpty()) {
			return null;
		}
		return teams;
	}

	@Override
	public List<CardPlayer> getAcceptFriend(String email) {
		
		List<CardPlayer> teams = new ArrayList<>();
		
		List<Contact> create = friendDAO.getCreateContacted(email);
		
 		if(create != null) {
			for(Contact cr : create) {
				if(cr.getState() == Wait) {
					teams.add(friendDAO.getCardPlayer(cr.getCreateContact()));
				}else {
					System.out.println("sai rồi nha!!!");	
				}
			}
		}
		if(teams.isEmpty()){
			return null;
		}
		return teams;
	}

	@Override
	public String stateContact(int id, HttpSession session, int state) {
		String email = friendDAO.getEmailPlayerById(id);
		if (email == null || email.equals("")) {
			return "fail";
		}
		String result = friendDAO.stateContact(email, session, state);
		if (result == null) {
			return "fail";
		}
		return result;
	}

	@Override
	public String playerContact(int id, HttpSession session, int state) {
		String email = friendDAO.getEmailPlayerById(id);
		if (email == null || email.equals("")) {
			return "fail";
		}
		String result = friendDAO.playerContact(email, session, state);
		
		return result;
	}

}
