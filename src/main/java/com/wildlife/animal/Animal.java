package com.wildlife.animal;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.wildlife.genus.Genus;
import com.wildlife.observation.Observation;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Animal {
	
	@Id 	
	@GeneratedValue(strategy = GenerationType.IDENTITY) //Die id wird automatisch erzeugt
	private Long id;
	private int age;
	private double weight;
	private double size;
	private String gender;
	
	//Beziehung zu Genus
	@ManyToOne
	@JoinColumn //erzeugt Fremdschlüssel
	private Genus genus;
	
	//Beziehung zu Observation/Location
	@OneToMany(mappedBy = "animal")
	@JsonIgnore //verhindert Endlos-Rekursion
	private List<Observation> observations;
	
	
	//Standardkonstruktor
	public Animal() {
	}
	
	//Konstruktor mit Parameter
	public Animal (Long id, int age, double weight, double size, String gender, Genus genus, List<Observation> observations) {
		
		this.id = id;
		this.age = age;
		this.weight = weight;
		this.size = size;
		this.gender = gender;
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
	

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
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
