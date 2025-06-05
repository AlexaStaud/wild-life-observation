package com.wildlife.animal;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/animal")
public class AnimalController {
	
	@Autowired //Einbinden von AnimalService
	AnimalService animalService;
	
	
	//Liste aller Animals
	@GetMapping
	public List<Animal> alleAnimals() {
		return animalService.getAnimalList();
	}
	
	//Ein Animal ausgeben
	@GetMapping("/{id}") 
	public Animal getAnimal(@PathVariable("id") long id) {
		return animalService.getAnimal(id);
	}
	
	//Ein Animal hinzufügen
	@PostMapping
	public void addAnimal(@RequestBody Animal animal) {
		animalService.addAnimal(animal);
	}
	
	//Animal bearbeiten
	@PutMapping("/{id}")
	public void updateAnimal(@PathVariable("id") long id, @RequestBody Animal animal) {
		animalService.updateAnimal(id, animal);
	}
	
	//Animal löschen
	@DeleteMapping("/{id}")
	public void deleteAnimal(@PathVariable("id") long id) {
		animalService.deleteAnimal(id);
	}
}
