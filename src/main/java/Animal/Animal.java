package Animal;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Animal {
	@Id
	private Long ID;
	private int age;
	private double weight;
	private double size;
	
	//Standardkonstruktor
	public Animal() {
	}
	
	//Konstruktor mit Parameter
	public Animal (Long ID, int age, double weight, double size) {
		
		this.ID = ID;
		this.age = age;
		this.weight = weight;
		this.size = size;
		
	}
	//Getter und Setter
	public long getID() {
		return ID;
	}

	public void setID(long ID) {
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
}
