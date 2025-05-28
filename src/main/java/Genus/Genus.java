package Genus;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Genus {
	
	@Id
	private Long id;
	private String designation;
	private String latinDesignation;
	
	//Standart Konstruktor
	public Genus() {
		
	}
	
	//Konstruktor
	public Genus(String designation, String latinDesignation) {
		this.designation = designation;
		this.latinDesignation = latinDesignation;
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
	
	
	


}
