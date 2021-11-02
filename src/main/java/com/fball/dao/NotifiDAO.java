package com.fball.dao;

import java.util.List;

import com.fball.dto.NotifiDTO;

public interface NotifiDAO {

	List<NotifiDTO> getAllNotifi(String email);

}
