package com.fball.service.imp;

import java.util.List;
import java.util.StringJoiner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fball.dao.DataMatchPlayerDAO;
import com.fball.dao.MatchDAO;
import com.fball.dao.VirtualMatchDAO;
import com.fball.dto.DataMatchPlayerDTO;
import com.fball.dto.DateDTO;
import com.fball.dto.MatchSTTClub;
import com.fball.dto.VirtualMatchDTO;
import com.fball.service.VirtualMatchService;
import com.fball.utils.DateTimeUtils;
import com.fball.utils.NotificationUtils;
import com.fball.utils.Utils;

@Service
public class VirtualMatchServiceImp implements VirtualMatchService {
	
	@Autowired
	private VirtualMatchDAO virtualMatchDAO;
	
	@Autowired
	private MatchDAO matchDAO;
	
	@Autowired
	private DataMatchPlayerDAO dataDAO;
	
	@Override
	public String newVirtualMatchInId(int idMatch, String email) {
		
		// check time
		MatchSTTClub match = matchDAO.getMatchSTTClubByIdMatch(idMatch);
		DateDTO date = new DateDTO();
		date.setDate(match.getDate());
		date.setMonth(match.getMonth());
		date.setYear(match.getYear());
		List<DataMatchPlayerDTO> datas = dataDAO.getListDataMatchInDate(date, email);
		String checkData = Utils.checkDataMatchPlayerInDate(datas, match);
		if(checkData.equals("fail")) {
			return "timeFail";
		}
		String rs = virtualMatchDAO.newVirtualMatchInId(idMatch, email);
		// // add history player
		if(rs.equals("success")) {
			
				DataMatchPlayerDTO data = new DataMatchPlayerDTO();
				data.setEmailPlayer(email);
				data.setIdMatch(match.getId());
				data.setTimeStart(match.getTimeStart());
				data.setTimeEnd(match.getTimeEnd());
				data.setDate(match.getDate());
				data.setMonth(match.getMonth());
				data.setYear(match.getYear());
				dataDAO.addDataMatchPlayer(data);
		}
		
		return "success";
	}

	@Override
	public String joinVirtualMatchInId(int id, String email) {
		
		VirtualMatchDTO virtual = virtualMatchDAO.getVirtualMatchById(id);
		
		// check time
		MatchSTTClub match = matchDAO.getMatchSTTClubByIdMatch(virtual.getIdMatch());
		DateDTO date = new DateDTO();
		date.setDate(match.getDate());
		date.setMonth(match.getMonth());
		date.setYear(match.getYear());
		List<DataMatchPlayerDTO> datas = dataDAO.getListDataMatchInDate(date, email);
		String checkData = Utils.checkDataMatchPlayerInDate(datas, match);
		if(checkData.equals("fail")) {
			return "joinFail";
		}
		
		// add email
		StringJoiner b = new StringJoiner(",");
		b.add(virtual.getList());
		b.add(email);
		virtual.setList(b.toString());
		String result = virtualMatchDAO.updateVirtualMatchbyId(virtual);

		// add match player
		if(result.equals("success")) {
			DataMatchPlayerDTO data = new DataMatchPlayerDTO();
			data.setEmailPlayer(email);
			data.setIdMatch(match.getId());
			data.setTimeStart(match.getTimeStart());
			data.setTimeEnd(match.getTimeEnd());
			data.setState(match.getState());
			data.setDate(match.getDate());
			data.setMonth(match.getMonth());
			data.setYear(match.getYear());
			dataDAO.addDataMatchPlayer(data);
		}
		
		// create match if equals 10 player and delete virtual match
		if(b.toString().split(",").length==10) {
			
			match.setRs(b.toString());
			match.setState(1);
// notification
			NotificationUtils.sendMessageSuccessToPlayer(b.toString());
			matchDAO.updateMatch(match);
			virtualMatchDAO.deleteVirtualMatch(id);
			
			List<VirtualMatchDTO> deleteList = virtualMatchDAO.getListVirtualMatchByIdMatch(virtual.getIdMatch());
			for(VirtualMatchDTO i : deleteList) {
				NotificationUtils.sendMessageFailToPlayer(i.getList());
				virtualMatchDAO.deleteVirtualMatch(i.getId());
			}
			
			return "createMatchSuccess";
		}
		
		return "success";
	}

	@Override
	public String cancelVirtualMatchInId(int id, String email) {
		
		VirtualMatchDTO virtual = virtualMatchDAO.getVirtualMatchById(id);
		
		StringJoiner str = new StringJoiner(",");
		String[] b = virtual.getList().split(",");
		
		// check email
		for(String i : b) {
			if(!i.equals(email)) {
				return "cancelFail";
			}
		}
		
		// delete virtual match if equals == 1
		if(b.length==1) {
			virtualMatchDAO.deleteVirtualMatch(id);
		}else {
		
		// delete email
			for(String j : b) {
				if(j.equals(email)) {
					
				}else {
					str.add(j);
				}
			}
			virtual.setList(str.toString());
			virtualMatchDAO.updateVirtualMatchbyId(virtual);
		}
		
		// delete match player
		MatchSTTClub match = matchDAO.getMatchSTTClubByIdMatch(virtual.getIdMatch());
		DataMatchPlayerDTO data = new DataMatchPlayerDTO();
		data.setEmailPlayer(email);
		data.setIdMatch(match.getId());
		data.setTimeStart(match.getTimeStart());
		data.setTimeEnd(match.getTimeEnd());
		data.setDate(match.getDate());
		data.setMonth(match.getMonth());
		data.setYear(match.getYear());
		dataDAO.deleteDataMatchPlayer(data);
		
// notification
		NotificationUtils.sendMessageSuccessToPlayer(email);
		
		return "success";
	}

	@Override
	public List<VirtualMatchDTO> getListVirtualMatchByIdMatch(int idMatch) {
		
		MatchSTTClub match = matchDAO.getMatchSTTClubByIdMatch(idMatch);
		String a = DateTimeUtils.checkTimeStart(match);
		
		if(a.equals("fail")) {
			return null;
		}
		
		List<VirtualMatchDTO> virtuals = virtualMatchDAO.getListVirtualMatchByIdMatch(idMatch);
		
		return virtuals;
	}

}
