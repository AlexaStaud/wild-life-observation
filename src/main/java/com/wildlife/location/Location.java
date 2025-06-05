package com.wildlife.location;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.wildlife.observation.Observation;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Location {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) //Die id (lNr) wird automatisch erzeugt
	private Long lNr;
	private String city;
	private String description;
	
	//Beziehung zu Observation (bzw. Animal)
	@OneToMany(mappedBy = "location")
	@JsonIgnore
	private List<Observation> observations;
	
	
	public Location() {} //leerer Konstruktor
	
	public Location(Long lNr, String city, String description, List<Observation> observations) {
		this.lNr = lNr;
		this.city = city;
		this.description = description;
		this.observations = observations;
	}
	
	
	//Getter und Setter

	public Long getlNr() {
		return lNr;
	}

	public void setlNr(Long lNr) {
		this.lNr = lNr;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Observation> getObservations() {
		return observations;
	}

	public void setObservations(List<Observation> observations) {
		this.observations = observations;
	}	

}
