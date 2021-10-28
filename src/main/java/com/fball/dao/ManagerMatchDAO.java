package com.fball.dao;

import java.util.List;

import com.fball.dto.MatchSTTClub;

public interface ManagerMatchDAO {

	MatchSTTClub getMatchSTTClubByIdMatch(int idMatch);

	String bookMatch(MatchSTTClub match);

	List<MatchSTTClub> getMatchSTTClubById(int id);

}
