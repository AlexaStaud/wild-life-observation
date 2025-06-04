package com.wildlife.animal;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.wildlife.genus.Genus;
import com.wildlife.observation.Observation;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Animal {
	@Id
	private Long id;
	private int age;
	private double weight;
	private double size;
	
	//Beziehung zu Genus
	@ManyToOne
	@JoinColumn
	private Genus genus;
	
	//Beziehung zu Observation/Location
	@OneToMany(mappedBy = "animal")
	@JsonIgnore
	private List<Observation> observations;
	
	
	//Standardkonstruktor
	public Animal() {
	}
	
	//Konstruktor mit Parameter
	public Animal (Long id, int age, double weight, double size, Genus genus, List<Observation> observations) {
		
		this.id = id;
		this.age = age;
		this.weight = weight;
		this.size = size;
		this.genus = genus;
		this.observations = observations;
		
	}
	//Getter und Setter
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public double getSize() {
		return size;
	}

	public void setSize(double size) {
		this.size = size;
	}

	public Genus getGenus() {
		return genus;
	}

	public void setGenus(Genus genus) {
		this.genus = genus;
	}

	public List<Observation> getObservations() {
		return observations;
	}

	public void setObservations(List<Observation> observations) {
		this.observations = observations;
	}
	
	
	
}
