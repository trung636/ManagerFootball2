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

import com.fball.dao.ManagerDAO;
import com.fball.dto.MatchSTTClub;
import com.fball.dto.STTClubDTO;
import com.fball.utils.SetParamaterForDAOUtils;

@Repository
public class ManagerDAOImp implements ManagerDAO {

	@Autowired
	private DataSource dataSource;
	

	@Override
	public String addSTTClub(STTClubDTO sttClubDTO) {
		String sql = "insert into stt_club(email, name, enable) \n"
				+ "values (?,?,?)";
		
		try(
				Connection conn = dataSource.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql)){
			SetParamaterForDAOUtils.setParamater(ps, 
							sttClubDTO.getEmail(), 
							sttClubDTO.getName(), 
							sttClubDTO.getEnable());
			ps.executeUpdate();
			return "success";
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return "fail";
	}

	@Override
	public List<STTClubDTO> getAllListSTTClub() {
		String sql = "select * from stt_club";
		List<STTClubDTO> clubs = new ArrayList<>();
		try(Connection conn = dataSource.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql)){
			
			try(ResultSet rs = ps.executeQuery()){
				while(rs.next()) {
					STTClubDTO club = new STTClubDTO();
					club.setId(rs.getInt("id"));
					club.setName(rs.getString("name"));
					club.setEmail(rs.getString("email"));
					club.setEnable(rs.getInt("enable"));
					club.setIdClub(rs.getInt("id_club"));
					club.setTime_update(rs.getString("updated_date"));
					clubs.add(club);
				}
				return clubs;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
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
	public List<STTClubDTO> getSTTClubById(int idClub) {
		
		String sql = "select * from stt_club \n"
				+ "where id_club = ? ";
		List<STTClubDTO> clubs = new ArrayList<>();
		try(Connection conn = dataSource.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql)){
			SetParamaterForDAOUtils.setParamater(ps, idClub);
			try(ResultSet rs = ps.executeQuery()){
				while(rs.next()) {
					STTClubDTO club = new STTClubDTO();
					club.setId(rs.getInt("id"));
					club.setName(rs.getString("name"));
					club.setEmail(rs.getString("email"));
					club.setEnable(rs.getInt("enable"));
					club.setIdClub(rs.getInt("id_club"));
					club.setTime_update(rs.getString("updated_date"));
					clubs.add(club);
				}
				return clubs;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
