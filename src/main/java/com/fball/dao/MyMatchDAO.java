package com.fball.dao;

import java.util.List;

import com.fball.dto.DataMatchPlayerDTO;

public interface MyMatchDAO {

	List<DataMatchPlayerDTO> getMyMatch(String email);

}
