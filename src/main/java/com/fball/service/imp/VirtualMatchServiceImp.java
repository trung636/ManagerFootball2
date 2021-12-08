package com.fball.service.imp;

import java.util.List;
import java.util.StringJoiner;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fball.dao.DataMatchPlayerDAO;
import com.fball.dao.MatchDAO;
import com.fball.dao.NotifiDAO;
import com.fball.dao.VirtualMatchDAO;
import com.fball.dto.CardPlayer;
import com.fball.dto.DataMatchPlayerDTO;
import com.fball.dto.DateDTO;
import com.fball.dto.MatchSTTClub;
import com.fball.dto.NotifiDTO;
import com.fball.dto.VirtualMatchDTO;
import com.fball.service.FriendService;
import com.fball.service.VirtualMatchService;
import com.fball.utils.DateTimeUtils;
import com.fball.utils.Utils;

@Service
public class VirtualMatchServiceImp implements VirtualMatchService {
	
	@Autowired
	private VirtualMatchDAO virtualMatchDAO;
	
	@Autowired
	private MatchDAO matchDAO;
	
	@Autowired
	private DataMatchPlayerDAO dataDAO;
	
	@Autowired
	private NotifiDAO notifiDAO;
	
	@Autowired
	private FriendService friendService;
	
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
				data.setIdVirtual(0);
				data.setState(0);
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
			data.setState(0);
			data.setIdVirtual(id);
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
			String[] list= b.toString().split(",");
			for (String i : list) {
				NotifiDTO notifi = new NotifiDTO();
				notifi.setEmail(i);
				notifi.setEvent("4");
				notifi.setHandleEvent("");
				notifi.setMessage("Create Success");
				notifiDAO.addNotifi(notifi);
		}
		
			virtualMatchDAO.deleteVirtualMatch(id);
			matchDAO.updateMatch(match);
			
//		delete virtual match and notifi
		List<VirtualMatchDTO> vis = getListVirtualMatchByIdMatch(match.getId());
		for(VirtualMatchDTO vi : vis) {
			deleteVirtualMatch(vi);
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
			}else {
				NotifiDTO notifi = new NotifiDTO();
				notifi.setEmail(i);
				notifi.setEvent("4");
				notifi.setHandleEvent("");
				notifi.setMessage(email +" just exited");
				notifiDAO.addNotifi(notifi);
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
		data.setIdVirtual(id);
		data.setDate(match.getDate());
		data.setMonth(match.getMonth());
		data.setYear(match.getYear());
		dataDAO.deleteDataMatchPlayer(data);
		
// notification
		
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

	@Override
	public String deleteVirtualMatch(VirtualMatchDTO v) {
		
		virtualMatchDAO.deleteVirtualMatch(v.getId());
		
		String[] list = v.getList().split(",");
		for(String i :list) {
			NotifiDTO notifi = new NotifiDTO();
			notifi.setEmail(i);
			notifi.setEvent("4");
			notifi.setHandleEvent("");
			notifi.setMessage("Virtual Match "+ v.getIdMatch()+" Canceled");
			notifiDAO.addNotifi(notifi);
		}
		
		return "success";
	}

	@Override
	public String inviteRequestAll(HttpSession session, int idVirtual) {
		
		VirtualMatchDTO virtual = virtualMatchDAO.getVirtualMatchById(idVirtual);
		
		MatchSTTClub match = matchDAO.getMatchSTTClubByIdMatch(virtual.getIdMatch());
		DateDTO date = new DateDTO();
		date.setDate(match.getDate());
		date.setMonth(match.getMonth());
		date.setYear(match.getYear());
		String checkData = DateTimeUtils.checkTimeStart(match);
		if(checkData.equals("fail")) {
			return "timeFail";
		}
		String[] str = virtual.getList().split(",");
		boolean b = false;
		for(String j : str) {
			if(j.equals(session.getAttribute("email").toString())) {
				b = true;
			}
		}
		if(b==false) {
			return "inviteFail";
		}
		List<CardPlayer> list = friendService.getMyFriend(session.getAttribute("email").toString());
		
		for (CardPlayer i : list) {
			NotifiDTO notifi = new NotifiDTO();
			notifi.setEmail(i.getEmail());
			notifi.setEvent("3");
			notifi.setHandleEvent(Integer.toString(idVirtual ));
			notifi.setMessage(session.getAttribute("namePlayer").toString() + " invite play football! Press to accept ");
			notifiDAO.addNotifi(notifi);
		}

		return "success";
	}

}
