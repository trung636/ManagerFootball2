package com.fball.cron;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fball.dto.DateDTO;
import com.fball.dto.MatchSTTClub;
import com.fball.dto.STTClubDTO;
import com.fball.utils.SetParamaterForDAOUtils;

@Repository
public class CronDAOImp implements CronDAO {
	
	@Autowired
	private DataSource dataSource;

	@Override
	public String setCron(STTClubDTO i, DateDTO date) {
		
		return "fail";
	}

	@Override
	public String updateMatchSTTCLub(MatchSTTClub match) {
		String sql = "insert into match_stt_club( "
				+ "id_stt_club, "
				+ "time_start, "
				+ "time_end,"
				+ "list_player,"
				+ "state,"
				+ "date,"
				+ "month,"
				+ "year) \n"
				+ "values(?,?,?,?,?,?,?,?)";
		try(Connection conn = dataSource.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql)){
			SetParamaterForDAOUtils.setParamater(ps, 
					match.getIdSTTClub(),
					match.getTimeStart(),
					match.getTimeEnd(),
					match.getRs(),
					match.getState(),
					match.getDate(),
					match.getMonth(),
					match.getYear());
			ps.executeUpdate();
			return "success";
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return	"fail";
		}

}
