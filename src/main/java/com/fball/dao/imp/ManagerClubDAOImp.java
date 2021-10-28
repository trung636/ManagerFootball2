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

import com.fball.dao.ManagerClubDAO;
import com.fball.dto.ClubDTO;
import com.fball.dto.STTClubDTO;
import com.fball.utils.SetParamaterForDAOUtils;

@Repository
public class ManagerClubDAOImp implements ManagerClubDAO {

	@Autowired
	private DataSource dataSource;
	
	@Override
	public ClubDTO getClubByEmail(String email) {
		String sql = "select * from club \n"
				+ "where email = ?";
		try(
				Connection conn = dataSource.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql)){
			SetParamaterForDAOUtils.setParamater(ps, email);
			try(ResultSet rs = ps.executeQuery()){
				while(rs.next()) {
					ClubDTO club = new ClubDTO();
					club.setNameClub(rs.getString("name_club"));
					club.setEmail(rs.getString("email"));
					club.setAddress(rs.getString("address"));
					club.setWard(rs.getString("ward"));
					club.setDistrict(rs.getString("district"));
					club.setCity(rs.getString("city"));
					club.setTimeStart(rs.getString("time_start"));
					club.setTimeEnd(rs.getString("time_end"));
					return club;
				}
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<STTClubDTO> getListSTTClubById(int idClub) {
		List<STTClubDTO> clubs = new ArrayList<>();
		String sql ="select * from stt_club \n"
				+ "where id_club = ?";
		try(
				Connection conn = dataSource.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql)){
			SetParamaterForDAOUtils.setParamater(ps, idClub);
			try(ResultSet rs = ps.executeQuery()){
				while(rs.next()) {
					STTClubDTO sttClub = new STTClubDTO();
					sttClub.setId(rs.getInt("id"));
					sttClub.setIdClub(rs.getInt("id_club"));
					sttClub.setEmail(rs.getString("email"));
					sttClub.setName(rs.getString("name"));
					sttClub.setEnable(rs.getInt("enable"));
					sttClub.setTime_update(rs.getString("updated_date"));
					clubs.add(sttClub);
				}
			}
			return clubs;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

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

}
