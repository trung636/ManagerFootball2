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

import com.fball.dao.DataMatchPlayerDAO;
import com.fball.dto.DataMatchPlayerDTO;
import com.fball.dto.DateDTO;
import com.fball.utils.SetParamaterForDAOUtils;

@Repository
public class DataMatchPlayerDAOImp implements DataMatchPlayerDAO {
	
	@Autowired
	private DataSource dataSource;

	@Override
	public String addDataMatchPlayer(DataMatchPlayerDTO data) {
		String sql = "insert into data_match_player( "
							+ "email_player, "
							+ "id_match, "
							+ "time_start, "
							+ "time_end,"
							+ "state,"
							+ "id_virtual, "
							+ "date, "
							+ "month, "
							+ "year"
							+ ") \n"
							+ "values (?,?,?,?,?,?,?,?,?)";
		try(
				Connection conn = dataSource.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql)) {
			SetParamaterForDAOUtils.setParamater(ps,
							data.getEmailPlayer(),
							data.getIdMatch(),
							data.getTimeStart(),
							data.getTimeEnd(),
							data.getState(),
							data.getIdVirtual(),
							data.getDate(),
							data.getMonth(),
							data.getYear());
			ps.executeUpdate();
			return "success";
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		return "fail";
	}
	
	@Override
	public List<DataMatchPlayerDTO> getListDataMatchInDate(DateDTO date, String email) {
		String sql ="select * from data_match_player where "
									+ "email_player = ? and \n"
									+ "date = ? and \n"
									+ "month = ? and \n"
									+ "year = ? ";
		List<DataMatchPlayerDTO> datas = new ArrayList<>();
		try(
				Connection conn = dataSource.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql)){
			SetParamaterForDAOUtils.setParamater(ps, 
									email,
									date.getDate(),
									date.getMonth(),
									date.getYear());
			try(ResultSet rs = ps.executeQuery()){
				while(rs.next()) {
					DataMatchPlayerDTO data = new DataMatchPlayerDTO();
					data.setDate(date.getDate());
					data.setMonth(date.getMonth());
					data.setYear(date.getYear());
					data.setEmailPlayer(email);
					data.setTimeStart(rs.getString("time_start"));
					data.setTimeEnd(rs.getString("time_end"));
					data.setIdMatch(rs.getInt("id_match"));
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

	@Override
	public String deleteDataMatchPlayer(DataMatchPlayerDTO data) {
		String sql = "delete from data_match_player where "
				+ "email_player = ? and \n"
				+ "id_match = ? and \n "
				+ "time_start = ? and \n"
				+ "time_end = ? and \n"
				+ "date = ? and \n"
				+ "month = ? and \n"
				+ "year = ?";
		try(
			Connection conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql)) {
		SetParamaterForDAOUtils.setParamater(ps,
						data.getEmailPlayer(),
						data.getIdMatch(),
						data.getTimeStart(),
						data.getTimeEnd(),
						data.getDate(),
						data.getMonth(),
						data.getYear());
		ps.executeUpdate();
		return "success";
		} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		} 
		
		return "fail";
	}

}
