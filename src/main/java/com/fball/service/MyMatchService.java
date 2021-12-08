package com.fball.service;

import java.util.List;

import com.fball.dto.DataMatchPlayerDTO;

public interface MyMatchService {

	List<DataMatchPlayerDTO> getMyMatch(String email);

}
