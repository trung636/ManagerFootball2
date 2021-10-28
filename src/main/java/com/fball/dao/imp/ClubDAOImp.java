package com.fball.dao.imp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fball.dao.ClubDAO;
import com.fball.dto.AccountDTO;
import com.fball.dto.ClubDTO;
import com.fball.dto.STTClubDTO;
import com.fball.utils.SetParamaterForDAOUtils;

@Repository
public class ClubDAOImp implements ClubDAO {
	
	@Autowired
	private DataSource dataSource;

	@Override
	public String checkAccountClub(AccountDTO accountDTO, HttpSession session) {
		String sql = "select * from club "
				+ "where email = ?";
		try(Connection conn = dataSource.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql)){
			SetParamaterForDAOUtils.setParamater(ps, accountDTO.getEmail());
			try(ResultSet rs = ps.executeQuery()){
				while(rs.next()) {
					if(!accountDTO.getPassword().equals(rs.getString("password"))) {
						return "fail";
					}
					session.setMaxInactiveInterval(86400);
					session.setAttribute("idClub", rs.getInt("id"));
					session.setAttribute("nameClub", rs.getString("name_club"));
					session.setAttribute("email", rs.getString("email"));
					session.setAttribute("address", rs.getString("address"));
					session.setAttribute("ward", rs.getString("ward"));
					session.setAttribute("district", rs.getString("district"));
					session.setAttribute("city", rs.getString("city"));
					session.setAttribute("timeStart", rs.getString("time_start"));
					session.setAttribute("timeEnd", rs.getString("time_end"));
					session.setAttribute("timeUpdate", rs.getString("time_update"));
					return "success";
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "fail";
	}

	@Override
	public ClubDTO getClubByEmail(String email) {
		String sql = "select * from club \n"
				+ "where email = ? ";
		try(
				Connection conn = dataSource.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql)){
			SetParamaterForDAOUtils.setParamater(ps, email);
			try(ResultSet rs = ps.executeQuery()){
				while(rs.next()) {
					ClubDTO club = new ClubDTO();
					club.setId(rs.getInt("id"));
					club.setNameClub(rs.getString("name_club"));
					club.setEmail(rs.getString("email"));
					club.setAddress(rs.getString("address"));
					club.setWard(rs.getString("ward"));
					club.setDistrict(rs.getString("district"));
					club.setCity(rs.getString("city"));
					club.setTimeStart( rs.getString("time_start"));
					club.setTimeEnd(rs.getString("time_end"));
					club.setTimeUpdate(rs.getString("time_update"));
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
	public String addClub(ClubDTO clubDTO) {
		String sql = "insert into club( "
									+ "name_club, "
									+ "email, "
									+ "password, "
									+ "address, "
									+ "ward, "
									+ "district, "
									+ "city, "
									+ "time_start, "
									+ "time_end) \n"
									+ "values(?, ?, ?, ?, ?, ?, ?, ?, ?) ";
		try(Connection conn = dataSource.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql)){
			SetParamaterForDAOUtils.setParamater(ps,
					clubDTO.getNameClub(),
					clubDTO.getEmail(), 
					clubDTO.getPassword(), 
					clubDTO.getAddress(),
					clubDTO.getWard(), 
					clubDTO.getDistrict(),
					clubDTO.getCity(), 
					clubDTO.getTimeStart(),
					clubDTO.getTimeEnd());
			ps.executeUpdate();
			return "success";
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "fail";
	}

	@Override
	public List<ClubDTO> getAllListClub() {
		List<ClubDTO> clubs = new ArrayList<>();
		String sql = "select * from club";
		try(
				Connection conn = dataSource.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql)){
			try(ResultSet rs = ps.executeQuery()){
				while(rs.next()) {
					ClubDTO club = new ClubDTO();
					club.setId(rs.getInt("id"));
					club.setNameClub(rs.getString("name_club"));
					club.setEmail(rs.getString("email"));
					club.setAddress(rs.getString("address"));
					club.setWard(rs.getString("ward"));
					club.setDistrict(rs.getString("district"));
					club.setCity(rs.getString("city"));
					club.setTimeStart( rs.getString("time_start"));
					club.setTimeEnd(rs.getString("time_end"));
					club.setTimeUpdate(rs.getString("time_update"));
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
				return clubs;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

}
