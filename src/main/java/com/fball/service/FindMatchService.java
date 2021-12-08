package com.fball.service;

import java.util.List;

import com.fball.dto.DateDTO;
import com.fball.dto.MatchSTTClub;

public interface FindMatchService {

	List<MatchSTTClub> getMatchByDateAndTime(DateDTO date, String time);

	List<MatchSTTClub> getListMatchByTime(String time, DateDTO date);

}
