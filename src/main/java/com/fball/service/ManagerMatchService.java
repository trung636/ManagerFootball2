package com.fball.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.fball.dto.MatchSTTClub;

public interface ManagerMatchService {

	List<MatchSTTClub> getMatchSTTClubById(int id);

	String bookMatch(int idMatch, HttpSession session);

}
