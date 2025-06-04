package com.wildlife.observation;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Observation {
	
	@Id
	private Long id;
	
	private String time;
	private String date;
	
	
	//Getter und Setter
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
}
