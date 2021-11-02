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

import com.fball.dao.FriendDAO;
import com.fball.dto.CardPlayer;
import com.fball.dto.Contact;
import com.fball.utils.SetParamaterForDAOUtils;

@Repository
public class FriendDAOImp implements FriendDAO {

	@Autowired
	private DataSource dataSource;

	@Override
	public List<Contact> getCreateContacted(String email) {
		List<Contact> creates = new ArrayList<>();
		String sql = "select * from contact \n" 
				+ "where agree_contact = ?";
		try (Connection conn = dataSource.getConnection(); PreparedStatement ps = conn.prepareStatement(sql);) {
			SetParamaterForDAOUtils.setParamater(ps, email);
			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					Contact create = new Contact();
					create.setId(rs.getInt("id"));
					create.setCreateContact(rs.getString("create_contact"));
					create.setAgreeContact(rs.getString("agree_contact"));
					create.setState(rs.getInt("state"));
					create.setCreateTime(rs.getString("create_time"));
					creates.add(create);
				}
			}
			return creates;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public CardPlayer getCardPlayer(String email) {
		CardPlayer player = new CardPlayer();
		String sql = "select * from player " + "where email = ?";
		try (Connection conn = dataSource.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
			SetParamaterForDAOUtils.setParamater(ps, email);
			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					player.setNamePlayer(rs.getString("name_player"));
					player.setPosition(rs.getString("position"));
					player.setEmail(rs.getString("email"));
					player.setId(rs.getInt("id"));

				}
				return player;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<CardPlayer> getAllPlayer(String email) {
		List<CardPlayer> teams = new ArrayList<>();
		String sql = "select * from player \n"
				+ "where "
				+ "player.email !=  ? ;";
		try (
				Connection conn = dataSource.getConnection(); 
				PreparedStatement ps = conn.prepareStatement(sql)) {
			SetParamaterForDAOUtils.setParamater(ps, email);
			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					CardPlayer player = new CardPlayer();
					player.setNamePlayer(rs.getString("name_player"));
					player.setPosition(rs.getString("position"));
					player.setEmail(rs.getString("email"));
					player.setId(rs.getInt("id"));
					teams.add(player);
				}
				return teams;
				}
			}catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();

		}
		return null;	
	}

	@Override
	public List<Contact> getAgreeContact(String email) {
		List<Contact> agrees = new ArrayList<>();
		String sql = "select * from contact \n"
				+ "where create_contact = ?";
		try(
				Connection conn = dataSource.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql);) {
			SetParamaterForDAOUtils.setParamater(ps, email);
			try(ResultSet rs = ps.executeQuery()){
				while(rs.next()) {
					Contact agree = new Contact();
					agree.setId(rs.getInt("id"));
					agree.setCreateContact(rs.getString("create_contact"));
					agree.setAgreeContact(rs.getString("agree_contact"));
					agree.setState(rs.getInt("state"));
					agree.setCreateTime(rs.getString("create_time"));
					agrees.add(agree);
				}
			}
			return agrees;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String getEmailPlayerById(int id) {
		
		String sql = "select email from player "
				+ "where id = ?";
		String result ="";
		try(Connection conn= dataSource.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql)) {
			SetParamaterForDAOUtils.setParamater(ps, id);
			try(ResultSet rs = ps.executeQuery()){
				while(rs.next()) {
					result = rs.getString("email"); 
				}
				return result;
			} 
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;	
	}

	@Override
	public String playerContact(String email, HttpSession session, int state) {
		String sql = "insert into contact(create_contact,agree_contact, state) "
				+ "values(?,?,?) ";
		try(
				Connection conn = dataSource.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql)) {
			SetParamaterForDAOUtils.setParamater(ps, session.getAttribute("email"), email, state);
			ps.executeUpdate();
			return "success";
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String stateContact(String email, HttpSession session, int state) {
		String sql = "Update contact Set state = ? \n "
				+ "where create_contact = ? "
				+ "and agree_contact = ?";
		try (
				Connection conn = dataSource.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql)){
			SetParamaterForDAOUtils.setParamater(ps, state, email, session.getAttribute("email"));
			ps.executeUpdate();
			
			return "success";
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
