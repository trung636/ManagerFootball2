package com.fball.service;

import java.util.List;

import com.fball.dto.ClubDTO;
import com.fball.dto.STTClubDTO;

public interface ClubService {

	List<ClubDTO> getAllListClub();

	List<STTClubDTO> getListSTTClubById(int idClub);

}
