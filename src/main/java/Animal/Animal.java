package Animal;

import java.util.List;

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
	private Long ID;
	private int age;
	private double weight;
	private double size;
	
	//Beziehung zu Genus
	@ManyToOne
	@JoinColumn
	private Genus genus;
	
	//Beziehung zu Observation/Location
	@OneToMany(mappedBy = "animal")
	private List<Observation> observations;
	
	
	//Standardkonstruktor
	public Animal() {
	}
	
	//Konstruktor mit Parameter
	public Animal (Long ID, int age, double weight, double size, Genus genus, List<Observation> observations) {
		
		this.ID = ID;
		this.age = age;
		this.weight = weight;
		this.size = size;
		this.genus = genus;
		this.observations = observations;
		
	}
	//Getter und Setter
	public Long getID() {
		return ID;
	}

	public void setID(Long ID) {
		this.ID = ID;
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
