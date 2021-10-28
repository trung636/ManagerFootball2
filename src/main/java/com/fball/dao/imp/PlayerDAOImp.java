package com.fball.dao.imp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fball.dao.PlayerDAO;
import com.fball.dto.AccountDTO;
import com.fball.dto.PlayerDTO;
import com.fball.utils.SetParamaterForDAOUtils;

@Repository
public class PlayerDAOImp implements PlayerDAO {

	@Autowired
	private DataSource dataSource;
	
	@Override
	public String checkAccountPlayer(AccountDTO accountDTO, HttpSession session) {
		String sql = "select * from player \n"
				+ "where email = ?";
		try (Connection conn = dataSource.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql)){
			SetParamaterForDAOUtils.setParamater(ps, accountDTO.getEmail());
			try(ResultSet rs = ps.executeQuery()){
				while(rs.next()) {
					if(!accountDTO.getPassword().equals(rs.getString("password"))) {
						return "fail";
					}
					session.setMaxInactiveInterval(86400);
					session.setAttribute("id",rs.getInt("id") );
					session.setAttribute("namePlayer",rs.getString("name_player"));
					session.setAttribute("email",rs.getString("email"));
					session.setAttribute("address",rs.getString("address"));
					session.setAttribute("ward",rs.getString("ward"));
					session.setAttribute("district",rs.getString("district"));
					session.setAttribute("city",rs.getString("city"));
					session.setAttribute("position",rs.getString("position"));
					session.setAttribute("timeCreate", rs.getString("time_create"));
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
	public PlayerDTO getPlayerByEmail(String email) {
		String sql = "select * from player \n"
				+ "where email = ?";
		try(
				Connection conn = dataSource.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql)){
			SetParamaterForDAOUtils.setParamater(ps, email);
			try(ResultSet rs = ps.executeQuery()){
				while(rs.next()) {
					PlayerDTO player = new PlayerDTO();
					player.setId(rs.getInt("id"));
					player.setEmail(rs.getString("email"));
					player.setNamePlayer(rs.getString("name_player"));
					player.setPassword(rs.getString("password"));
					player.setPosition(rs.getString("position"));
					player.setAddress(rs.getString("address"));
					player.setWard(rs.getString("ward"));
					player.setDistrict(rs.getString("district"));
					player.setCity(rs.getString("city"));
					return player;
				}
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String updateProfile(String email, PlayerDTO playerDTO) {
		String sql ="update player set \n"
				+ "name_player = ? ,\n"
				+ "position = ? ,\n"
				+ "ward = ? ,\n"
				+ "district = ? \n"
				+ "where email = ? ";
		try(
				Connection conn = dataSource.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql)){
			SetParamaterForDAOUtils.setParamater(ps, 
						playerDTO.getNamePlayer(), 
						playerDTO.getPosition(),
						playerDTO.getWard(),
						playerDTO.getDistrict(), 
						email);
			ps.executeUpdate();
			return "success";
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "fail";
	}

	@Override
	public String updatePassword(PlayerDTO player) {
		
		String sql = "update player set \n"
				+ "password = ? \n"
				+ "where email = ?";
		try(
				Connection conn = dataSource.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql)){
			SetParamaterForDAOUtils.setParamater(ps, 
							player.getPassword(), 
							player.getEmail());
			ps.executeUpdate();
			return "success";
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "fail";
	}

	@Override
	public String addPlayer(PlayerDTO playerDTO) {
	
		String sql = "insert into player(name_player, email, password, role_player , position, address, ward, district, city) \n"
				+ "values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
		try(Connection conn = dataSource.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql)){
			SetParamaterForDAOUtils.setParamater(ps,
					playerDTO.getNamePlayer(),
					playerDTO.getEmail(),
					playerDTO.getPassword(),
					1,
					playerDTO.getPosition(),
					playerDTO.getAddress(),
					playerDTO.getWard(),
					playerDTO.getDistrict(),
					playerDTO.getCity()
					);
			ps.executeUpdate();
			return "success";
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "fail";
		
	}

}
