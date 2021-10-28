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

import com.fball.dao.ManagerMatchDAO;
import com.fball.dto.MatchSTTClub;
import com.fball.utils.SetParamaterForDAOUtils;

@Repository
public class ManagerMatchDAOImp implements ManagerMatchDAO {

	@Autowired
	private DataSource dataSource;
	

	@Override
	public String bookMatch(MatchSTTClub match) {
		String sql =  "update match_stt_club set \n"
				+ " list_player = ?, \n"
				+ "	state = ? \n"
				+ "where id = ?";
		try(
				Connection conn = dataSource.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql)){
			SetParamaterForDAOUtils.setParamater(ps, 
							match.getRs(),
							1, 
							match.getId());
			ps.executeUpdate();
			return "success";
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "fail";
		
	}

	@Override
	public List<MatchSTTClub> getMatchSTTClubById(int id) {
		String sql = "select * from match_stt_club \n"
				+ "where id_stt_club = ?";
		List<MatchSTTClub> matchClubs = new ArrayList<>();
		try(
				Connection conn =dataSource.getConnection();
				PreparedStatement ps =conn.prepareStatement(sql)){
			SetParamaterForDAOUtils.setParamater(ps, id);
			System.out.println(sql);
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

	@Override
	public MatchSTTClub getMatchSTTClubByIdMatch(int idMatch) {
		
		String sql = "select * from match_stt_club \n"
				+ "where id = ?";
		try(Connection conn = dataSource.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql)){
			SetParamaterForDAOUtils.setParamater(ps, idMatch);
			try(ResultSet rs = ps.executeQuery()){
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
					return matchClub;
				}
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}


}
