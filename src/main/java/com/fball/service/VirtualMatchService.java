package com.fball.service;

import java.util.List;

import com.fball.dto.VirtualMatchDTO;

public interface VirtualMatchService {


	String newVirtualMatchInId(int idVirtual, String string);

	String joinVirtualMatchInId(int id, String string);

	String cancelVirtualMatchInId(int id, String string);

	List<VirtualMatchDTO> getListVirtualMatchByIdMatch(int idMatch);

}
