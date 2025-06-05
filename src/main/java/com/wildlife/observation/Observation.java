package com.wildlife.observation;

import com.wildlife.animal.Animal;
import com.wildlife.location.Location;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
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
	@JoinColumn //erzeugt Fremdschlüsselspalte 
	private Animal animal;
	
	@ManyToOne 
	@JoinColumn //erzeugt Fremdschlüsselspalte 
	private Location location;
	
	//Standart-Konstruktor
	public Observation() {}
	
	//Konstruktor 
	public Observation(Long id, String time, String date, Animal animal, Location location) {
		this.id = id;
		this.time = time;
		this.date = date;
		this.animal = animal;
		this.location = location;
	}
	
	//Getter und Setter automatisch erzeugt
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
