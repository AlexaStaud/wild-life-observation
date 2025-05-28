package Location;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Location {
	
	@Id
	private Long lNr;
	private String city;
	private String description;
	
	public Location() {} //leerer Konstruktor
	
	public Location(Long lNr, String city, String description) {
		this.lNr = lNr;
		this.city = city;
		this.description = description;
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

}
