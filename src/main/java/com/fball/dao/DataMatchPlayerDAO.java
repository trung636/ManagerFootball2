package com.fball.dao;

import java.util.List;

import com.fball.dto.DataMatchPlayerDTO;
import com.fball.dto.DateDTO;

public interface DataMatchPlayerDAO {

	String addDataMatchPlayer(DataMatchPlayerDTO data);

	List<DataMatchPlayerDTO> getListDataMatchInDate(DateDTO date, String email);

	String deleteDataMatchPlayer(DataMatchPlayerDTO data);

}
