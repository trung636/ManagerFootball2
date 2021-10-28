package com.fball.dto;

public class STTClubDTO {

	private int id;
	private int idClub;
	private String name;
	private String email;
	private int enable;
	private String time_update;
	
	public int getIdClub() {
		return idClub;
	}

	public void setIdClub(int idClub) {
		this.idClub = idClub;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getEnable() {
		return enable;
	}

	public void setEnable(int enable) {
		this.enable = enable;
	}

	public String getTime_update() {
		return time_update;
	}

	public void setTime_update(String time_update) {
		this.time_update = time_update;
	}

}
