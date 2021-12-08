package com.fball.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.fball.dto.DateDTO;
import com.fball.dto.VirtualMatchDTO;

public interface VirtualMatchService {


	String newVirtualMatchInId(int idVirtual, String string);

	String joinVirtualMatchInId(int id, String string);

	String cancelVirtualMatchInId(int id, String string);

	List<VirtualMatchDTO> getListVirtualMatchByIdMatch(int idMatch);

	String deleteVirtualMatch(VirtualMatchDTO i);

	String inviteRequestAll(HttpSession session, int idVirtual);
	
}
