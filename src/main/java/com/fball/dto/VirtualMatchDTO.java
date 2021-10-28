package com.fball.dto;

import java.util.List;

public class VirtualMatchDTO {

	private int id;
	private int idMatch;
	private String list;
	private List<CardPlayer> listPlayer;
	private String updatedDate;

	public String getList() {
		return list;
	}

	public void setList(String list) {
		this.list = list;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdMatch() {
		return idMatch;
	}

	public void setIdMatch(int idMatch) {
		this.idMatch = idMatch;
	}

	public List<CardPlayer> getListPlayer() {
		return listPlayer;
	}

	public void setListPlayer(List<CardPlayer> listPlayer) {
		this.listPlayer = listPlayer;
	}

	public String getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(String updatedDate) {
		this.updatedDate = updatedDate;
	}

}
