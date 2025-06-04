package com.wildlife.genus;

import java.util.List;


import com.wildlife.animal.Animal;



import com.fasterxml.jackson.annotation.JsonIgnore;


import Animal.Animal;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Genus {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String designation;
	private String latinDesignation;
	

	
	//Beziehungen ein Genus kann mehrere Animals haben
	@OneToMany(mappedBy = "genus") //Rückbeziehungs aus Animal.java
	@JsonIgnore //verhindert Endlosschleifen/Rekursion 
	

	private List<Animal> animals;
	
	
	//Standart Konstruktor
	public Genus() {
		
	}
	
	//Konstruktor
	public Genus(String designation, String latinDesignation, List<Animal> animals) {
		this.designation = designation;
		this.latinDesignation = latinDesignation;
		this.animals = animals;
	}

	//Getter & Setter
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getLatinDesignation() {
		return latinDesignation;
	}

	public void setLatinDesignation(String latinDesignation) {
		this.latinDesignation = latinDesignation;
	}

	public List<Animal> getAnimals() {
		return animals;
	}

	public void setAnimals(List<Animal> animals) {
		this.animals = animals;
	}
	
	
	
	

}
