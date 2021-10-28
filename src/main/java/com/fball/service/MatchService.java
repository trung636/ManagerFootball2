package com.fball.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.fball.dto.DateDTO;
import com.fball.dto.MatchSTTClub;

public interface MatchService {

	List<MatchSTTClub> getListMatchSTTClubByIdStt(int idStt, DateDTO dateDTO);

	String bookMatch(int idMatch, HttpSession session);

	List<MatchSTTClub> getAllMatch(DateDTO dateDTO);

	String cancelMatch(int idMatch, HttpSession session);

}
