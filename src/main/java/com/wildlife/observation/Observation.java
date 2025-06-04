package com.wildlife.observation;

import com.wildlife.location.Location;
import Animal.Animal;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Observation {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) //Datenbank erzeugt Wert für id automatisch
	
	private Long id;
	
	private String time;
	private String date;
	
	//Beziehungen
	@ManyToOne
	private Animal animal;
	
	@ManyToOne
	private Location location;
	
	//Getter und Setter
	public Animal getAnimal() {
		return animal;
	}
	public void setAnimal(Animal animal) {
		this.animal = animal;
	}
	public Location getLocation() {
		return location;
	}
	public void setLocation(Location location) {
		this.location = location;
	}
	
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
