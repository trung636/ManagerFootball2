package com.fball.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fball.dao.MyMatchDAO;
import com.fball.dto.DataMatchPlayerDTO;
import com.fball.service.MyMatchService;

@Service
public class MyMatchServiceImp implements MyMatchService {

	@Autowired
	private MyMatchDAO myMatchDAO;

	@Override
	public List<DataMatchPlayerDTO> getMyMatch(String email) {
		List<DataMatchPlayerDTO> match = myMatchDAO.getMyMatch(email);
		return match;
	}

}
