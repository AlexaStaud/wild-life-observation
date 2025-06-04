package Animal;

import com.wildlife.genus.Genus;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

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
	
	//Standardkonstruktor
	public Animal() {
	}
	
	//Konstruktor mit Parameter
	public Animal (Long ID, int age, double weight, double size, Genus genus) {
		
		this.ID = ID;
		this.age = age;
		this.weight = weight;
		this.size = size;
		this.genus = genus;
		
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
	
	
	
}
