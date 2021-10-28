package com.fball.service.imp;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fball.dao.DataMatchPlayerDAO;
import com.fball.dao.MatchDAO;
import com.fball.dto.DataMatchPlayerDTO;
import com.fball.dto.DateDTO;
import com.fball.dto.MatchSTTClub;
import com.fball.service.MatchService;
import com.fball.utils.DateTimeUtils;
import com.fball.utils.NotificationUtils;
import com.fball.utils.Utils;

@Service
public class MatchServiceImp implements MatchService {
	
	@Autowired
	private MatchDAO matchDAO;
	
	@Autowired
	private DataMatchPlayerDAO dataDAO;
	

	@Override
	public List<MatchSTTClub> getListMatchSTTClubByIdStt(int idStt, DateDTO dateDTO) {
		List<MatchSTTClub> rs = matchDAO.getListMatchSTTClubByIdStt(idStt, dateDTO);
		if(rs.isEmpty()) {
			return null;
		}
		return rs;
	}

	@Override
	public String bookMatch(int idMatch, HttpSession session) {
		//		check state
		MatchSTTClub match = matchDAO.getMatchSTTClubByIdMatch(idMatch);
		if(match.getState()==1) {
			return "timeFail";
		}
		
		//	check match player
		DateDTO date = new DateDTO();
		date.setDate(match.getDate());
		date.setMonth(match.getMonth());
		date.setYear(match.getYear());
		List<DataMatchPlayerDTO> datas = dataDAO.getListDataMatchInDate(date,session.getAttribute("email").toString());
		String checkData = Utils.checkDataMatchPlayerInDate(datas, match);
		if(checkData.equals("fail")) {
			return "timeFail";
		}
//		add match
		match.setRs(session.getAttribute("email").toString());
		match.setState(1);
		String rs = matchDAO.updateMatch(match);
		
		// add	match players
		if(rs.equals("success")) {
			
			DataMatchPlayerDTO data = new DataMatchPlayerDTO();
			data.setEmailPlayer(session.getAttribute("email").toString());
			data.setIdMatch(match.getId());
			data.setTimeStart(match.getTimeStart());
			data.setState(match.getYear());
			data.setTimeEnd(match.getTimeEnd());
			data.setDate(match.getDate());
			data.setMonth(match.getMonth());
			data.setYear(match.getYear());
			dataDAO.addDataMatchPlayer(data);
			
		}
		
		NotificationUtils.sendMessageSuccessToPlayer(session.getAttribute("email").toString());
		
		return "success";
	}

	@Override
	public List<MatchSTTClub> getAllMatch(DateDTO date) {
		List<MatchSTTClub> rs = matchDAO.getAllMatch(date);
		if(rs==null) {
			return null;
		}
		if(rs.isEmpty()) {
			return null;
		}
		return rs;
	}

	@Override
	public String cancelMatch(int idMatch, HttpSession session) {
		MatchSTTClub match = matchDAO.getMatchSTTClubByIdMatch(idMatch);
		if(match==null) {
			return "accountFail";
		}
		if(!match.getRs().equals(session.getAttribute("email").toString())) {
			return "accountFail";
		}
//	check time
		String checkTimeCancel = DateTimeUtils.checkTimeCancel(match);
		if(checkTimeCancel.equals("fail")){
			return "timeFail";
		}
//	add
		match.setRs("");
		match.setState(0);
		String rs = matchDAO.updateMatch(match);

//	delete
		if(rs.equals("success")) {
			DataMatchPlayerDTO data = new DataMatchPlayerDTO();
			data.setEmailPlayer(session.getAttribute("email").toString());
			data.setIdMatch(match.getId());
			data.setTimeStart(match.getTimeStart());
			data.setTimeEnd(match.getTimeEnd());
			data.setDate(match.getDate());
			data.setMonth(match.getMonth());
			data.setYear(match.getYear());
			dataDAO.deleteDataMatchPlayer(data);
		}
//	notifi		
		NotificationUtils.sendMessageSuccessToPlayer(session.getAttribute("email").toString());
		
		return "success";
	}

}
