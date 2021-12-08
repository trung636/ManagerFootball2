package com.fball.dao.imp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fball.dao.FindMatchDAO;
import com.fball.dto.DateDTO;
import com.fball.dto.MatchSTTClub;
import com.fball.utils.SetParamaterForDAOUtils;

@Repository
public class FindMatchDAOImp implements FindMatchDAO{
	
	@Autowired
	private DataSource dataSource;


	@Override
	public List<MatchSTTClub> getListMatchByTime(DateDTO date) {
		String sql = "select * from match_stt_club \n"
				+ "where \n"
				+ "date = ? and \n"
				+ "month = ? and \n"
				+ "year = ? \n"
				+ "order by time_start";
		List<MatchSTTClub> matchClubs = new ArrayList<>();
		try(
				Connection conn =dataSource.getConnection();
				PreparedStatement ps =conn.prepareStatement(sql)){
			SetParamaterForDAOUtils.setParamater(ps, 
								date.getDate(), 
								date.getMonth(), 
								date.getYear());
				try(ResultSet rs =ps.executeQuery()){
					while(rs.next()) {
						MatchSTTClub matchClub = new MatchSTTClub();
						matchClub.setId(rs.getInt("id"));
						matchClub.setIdSTTClub(rs.getInt("id_stt_club"));
						matchClub.setTimeStart(rs.getString("time_start"));
						matchClub.setTimeEnd(rs.getString("time_end"));
						matchClub.setRs(rs.getString("list_player"));
						matchClub.setState(rs.getInt("state"));
						matchClub.setDate(rs.getInt("date"));
						matchClub.setMonth(rs.getInt("month"));
						matchClub.setYear(rs.getInt("year"));
						matchClubs.add(matchClub);
					}
					return matchClubs;
				}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
