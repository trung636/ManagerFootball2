package com.fball.dao;

import java.util.List;

import com.fball.dto.VirtualMatchDTO;

public interface VirtualMatchDAO {

	List<VirtualMatchDTO> getListVirtualMatchByIdMatch(int id);

	String newVirtualMatchInId(int idVirtual, String email);

	VirtualMatchDTO getVirtualMatchById(int id);

	String updateVirtualMatchbyId(VirtualMatchDTO virtual);

	String deleteVirtualMatch(int id);
	

}
