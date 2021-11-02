package com.fball.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.fball.dto.CardPlayer;

public interface FriendService {

	List<CardPlayer> getMyFriend(String email);

	List<CardPlayer> findNewFriend(String email);

	List<CardPlayer> getAcceptFriend(String email);

	String stateContact(int id, HttpSession session, int state);

	String playerContact(int id, HttpSession session, int wait);

}
