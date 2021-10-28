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

import com.fball.dao.VirtualMatchDAO;
import com.fball.dto.VirtualMatchDTO;
import com.fball.utils.SetParamaterForDAOUtils;

@Repository
public class VirtualMatchDAOImp implements VirtualMatchDAO {
	
	@Autowired
	private DataSource dataSource;
	
	@Override
	public List<VirtualMatchDTO> getListVirtualMatchByIdMatch(int id) {

		List<VirtualMatchDTO> virtuals = new ArrayList<>();
		String sql = "select * from virtual_match \n"
				+ "where id_match = ?";
		try(Connection conn = dataSource.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql)){
			SetParamaterForDAOUtils.setParamater(ps, id);
			try(ResultSet rs = ps.executeQuery()){
				while(rs.next()) {
					VirtualMatchDTO virtual = new VirtualMatchDTO();
					virtual.setId(rs.getInt("id"));
					virtual.setIdMatch(rs.getInt("id_match"));
					virtual.setList(rs.getString("list_player"));
					virtual.setUpdatedDate(rs.getString("updated_date"));
					virtuals.add(virtual);
				}
				return virtuals;
			}
		} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		return null;
		
	}

	@Override
	public String newVirtualMatchInId(int idVirtual, String email) {
		String sql = "insert into virtual_match(id_match, list_player) \n"
				+ "values (?,?)";
		try(Connection conn = dataSource.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql)){
			SetParamaterForDAOUtils.setParamater(ps, idVirtual, email);
			System.out.println(ps);
			ps.executeUpdate();
			return "success";
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "fail";
	}

	@Override
	public VirtualMatchDTO getVirtualMatchById(int id) {
		
		String sql = "select * from virtual_match \n"
				+ "where id = ?";
		try(Connection conn = dataSource.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql)){
			SetParamaterForDAOUtils.setParamater(ps, id);
			try(ResultSet rs = ps.executeQuery()){
				while(rs.next()) {
					VirtualMatchDTO virtual = new VirtualMatchDTO();
					virtual.setId(rs.getInt("id"));
					virtual.setIdMatch(rs.getInt("id_match"));
					virtual.setList(rs.getString("list_player"));
					virtual.setUpdatedDate(rs.getString("updated_date"));
					return virtual;
				}
			}
		} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
		}
		return null;
	}

	@Override
	public String updateVirtualMatchbyId(VirtualMatchDTO virtual) {
		String sql = "update virtual_match set \n"
				+ "list_player = ? \n"
				+ "where id = ? ";
		try(Connection conn = dataSource.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql)){
			SetParamaterForDAOUtils.setParamater(ps, virtual.getList(), virtual.getId());
			System.out.println(sql);
			ps.executeUpdate();
			return "success";
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "fail";
	}

	@Override
	public String deleteVirtualMatch(int id) {
		String sql = "delete from virtual_match \n"
				+ "where id = ? ";
		try(Connection conn = dataSource.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql)){
			SetParamaterForDAOUtils.setParamater(ps, id);
			ps.executeUpdate();
			return "success";
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "fail";
	}

}
