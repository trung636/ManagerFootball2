package com.fball.cron;

import com.fball.dto.DateDTO;
import com.fball.dto.MatchSTTClub;
import com.fball.dto.STTClubDTO;

public interface CronDAO {

	String setCron(STTClubDTO i, DateDTO date);
	String updateMatchSTTCLub(MatchSTTClub match);

}
