package com.fball.dao;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.fball.dto.CardPlayer;
import com.fball.dto.Contact;

public interface FriendDAO {

	List<Contact> getCreateContacted(String email);

	CardPlayer getCardPlayer(String createContact);

	List<CardPlayer> getAllPlayer(String email);

	List<Contact> getAgreeContact(String email);

	String getEmailPlayerById(int id);

	String playerContact(String email, HttpSession session, int wait);

	String stateContact(String email, HttpSession session, int state);

}
