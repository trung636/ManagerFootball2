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

import com.fball.dao.MyMatchDAO;
import com.fball.dto.DataMatchPlayerDTO;
import com.fball.utils.SetParamaterForDAOUtils;

@Repository
public class MyMatchDAOImp implements MyMatchDAO {
	
	@Autowired
	private DataSource dataSource;

	@Override
	public List<DataMatchPlayerDTO> getMyMatch(String email) {
		String sql ="select * from data_match_player where "
				+ "email_player = ? "
				+ "order by id desc";
		List<DataMatchPlayerDTO> datas = new ArrayList<>();
		try(
				Connection conn = dataSource.getConnection();
				
				PreparedStatement ps = conn.prepareStatement(sql)){
			SetParamaterForDAOUtils.setParamater(ps, 
								email);
			try(ResultSet rs = ps.executeQuery()){
				while(rs.next()) {
					DataMatchPlayerDTO data = new DataMatchPlayerDTO();
					data.setDate(rs.getInt("date"));
					data.setMonth(rs.getInt("month"));
					data.setYear(rs.getInt("year"));
					data.setEmailPlayer(email);
					data.setTimeStart(rs.getString("time_start"));
					data.setTimeEnd(rs.getString("time_end"));
					data.setIdMatch(rs.getInt("id_match"));
					data.setState(rs.getInt("state"));
					datas.add(data);
				}
			return datas;
			}	
		} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return null;
	}

}
