package com.fball.dao;

import java.util.List;

import com.fball.dto.DateDTO;
import com.fball.dto.MatchSTTClub;

public interface FindMatchDAO {

	List<MatchSTTClub> getListMatchByTime(DateDTO date);

}
