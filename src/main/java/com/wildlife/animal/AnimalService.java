package com.wildlife.animal;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AnimalService {

	@Autowired //Einbinden/Verknüpfen von AnimalRepository
	AnimalRepository animalRepository;
	
	//Liste aller Animals ausgeben
	public List<Animal> getAnimalList() {
		
		ArrayList<Animal> animalList = new ArrayList<>();
		Iterator<Animal> it = animalRepository.findAll().iterator();
		while(it.hasNext()) {
			animalList.add(it.next());
		}
		return animalList;
	}
	//Animal zurückgeben anahand id
	public Animal getAnimal(long id) {
		return animalRepository.findById(id).orElse(null);
	}
	//Animal hinzufügen
	public void addAnimal(Animal animal) {
		animalRepository.save(animal);
	}
	//Animal bearbeiten
	public void updateAnimal(long id, Animal animal) {
		Animal bestehendeAnimal = animalRepository.findById(id).orElse(null); //holt das bestehende Tier aus der Datenbank
		
		//Überschreibt - wenn vorhanden - die Werte mit den neuen Daten 
		bestehendeAnimal.setAge(animal.getAge());
		bestehendeAnimal.setWeight(animal.getWeight());
		bestehendeAnimal.setSize(animal.getSize());
		bestehendeAnimal.setGender(animal.getGender());
		animalRepository.save(bestehendeAnimal);	//sichert das aktualisierte Tier wieder in der Datenbank
	}
	
	//Animal löschen anhand id
	public void deleteAnimal(long id) {
		animalRepository.deleteById(id);
	}
	
}
