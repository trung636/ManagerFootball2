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

import com.fball.dao.NotifiDAO;
import com.fball.dto.NotifiDTO;
import com.fball.utils.SetParamaterForDAOUtils;

@Repository
public class NotifiDAOImp implements NotifiDAO {
	
	@Autowired
	private DataSource dataSource;

	@Override
	public List<NotifiDTO> getAllNotifi(String email) {
		List<NotifiDTO> list = 	new ArrayList<>();
		String sql = "select * from notifi \n "
				+ "where email = ? \n"
				+ "order by id desc";
		try(
				Connection conn = dataSource.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql)){
			SetParamaterForDAOUtils.setParamater(ps, email);
			try(ResultSet rs = ps.executeQuery()){
				while(rs.next()) {
					NotifiDTO notifi = new NotifiDTO();
					notifi.setId(rs.getInt("id"));
					notifi.setEmail(email);
					notifi.setMessage(rs.getString("message"));
					notifi.setEvent(rs.getString("event"));
					notifi.setHandleEvent(rs.getString("handle_event"));
					notifi.setTime(rs.getString("time"));
					notifi.setDate(rs.getInt("date"));
					notifi.setMonth(rs.getInt("month"));
					notifi.setYear(rs.getInt("year"));
					notifi.setTimeUpdate(rs.getString("updated_time"));
					list.add(notifi);
				}
				return list;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String addNotifi(NotifiDTO notifi) {
		String sql = "insert into notifi(email, event, handle_event,message) "
				+ "values(?,?,?,?)";
		try(
				Connection conn = dataSource.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql)){
			SetParamaterForDAOUtils.setParamater(ps, 
								notifi.getEmail(), 
								notifi.getEvent(),
								notifi.getHandleEvent(),
								notifi.getMessage());
			ps.executeUpdate();
			return "success";
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "fail";
	}

}
