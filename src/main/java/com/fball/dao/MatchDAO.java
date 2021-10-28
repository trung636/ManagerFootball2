package com.fball.dao;

import java.util.List;

import com.fball.dto.DateDTO;
import com.fball.dto.MatchSTTClub;

public interface MatchDAO {


	List<MatchSTTClub> getListMatchSTTClubByIdStt(int idStt, DateDTO dateDTO);

	MatchSTTClub getMatchSTTClubByIdMatch(int idMatch);

	String updateMatch(MatchSTTClub match);

	List<MatchSTTClub> getAllMatch(DateDTO date);

}
