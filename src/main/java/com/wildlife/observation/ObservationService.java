package com.wildlife.observation;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wildlife.animal.Animal;
import com.wildlife.animal.AnimalRepository;
import com.wildlife.genus.Genus;
import com.wildlife.genus.GenusRepository;
import com.wildlife.location.Location;
import com.wildlife.location.LocationRepository;

@Service
public class ObservationService {
	
	//Eingliedern des ObservationRepository
	@Autowired
	ObservationRepository observationRepository;
	
	@Autowired
	GenusRepository genusRepository;
	
	@Autowired
	AnimalRepository animalRepository;
	
	@Autowired
	LocationRepository locationRepository;
	
	
	//Alle Beobachtungen aufrufen
	public List<Observation> getObservationList() {
		
		ArrayList<Observation> observationList = new ArrayList<>();
		
		//Iterator über alle Einträge im Repository
		Iterator<Observation> it = observationRepository.findAll().iterator();
		while(it.hasNext()) {
			observationList.add(it.next());	 //Fügt Beobachtung zur Liste hinzu
		}
		return observationList; //Gibt Liste zurück

	}
	//Beobachtung mit ID aufrufen
	public Observation getObservation(Long id) {
		return observationRepository.findById(id).orElse(null); //Wenn Beobachtung gefunden wird die zurückgegeben, sonst null
	}
	
	
	//Neue Beobachtung speichern
	public void addObservation(Observation observation) {
		
		//Genus finden und an Animal "anhängen"
		Long genusId = observation.getAnimal().getGenus().getId(); 
		Genus genus = genusRepository.findById(genusId).orElse(null);
		observation.getAnimal().setGenus(genus);
		
		//Animal speichern (jetzt vollständig, weil verknüpft mit Genus) und an Observation "anhängen"
		Animal savedAnimal = animalRepository.save(observation.getAnimal());
		observation.setAnimal(savedAnimal);
		
		//Location finden und an Observation "anhängen"
		Long lNr = observation.getLocation().getlNr();
		Location location = locationRepository.findById(lNr).orElse(null);
		observation.setLocation(location);
		
		//Observation speichern
		observationRepository.save(observation);
	}
	
	//Beobachtung aktualisieren
	public void updateObservation(Long id, Observation updateObservation) {
		
		//Die existierende Observation in Variable speichern
		Observation existingObservation = observationRepository.findById(id).orElse(null);
		
		//existierendes Animal und neue Angaben werden in zwei Variablen abgespeichert
		Animal existingAnimal = existingObservation.getAnimal(); 
		Animal updateAnimal = updateObservation.getAnimal(); 
		
		//existingAnimal übernimmt alle neuen Angaben
		existingAnimal.setAge(updateAnimal.getAge());
		existingAnimal.setGender(updateAnimal.getGender());
		existingAnimal.setSize(updateAnimal.getSize());
		existingAnimal.setWeight(updateAnimal.getWeight());
		
		//existingAnimal verknüpft sich mit Genus aus den neuen Angaben		
		Long genusId = updateAnimal.getGenus().getId();
		Genus genus = genusRepository.findById(genusId).orElse(null);
		existingAnimal.setGenus(genus);
		
		//Location finden und an existingObservation "anhängen"
		Long lNr = updateObservation.getLocation().getlNr();
		Location location = locationRepository.findById(lNr).orElse(null);
		existingObservation.setLocation(location);
		
		//existierende, aktualisierte Observation speichern
		observationRepository.save(existingObservation);
	}
	
	
	//Beobachtung löschen
	public void deleteObservation(Long id) {
		
		//Observation finden
		Observation observation = observationRepository.findById(id).orElse(null);
		
		//zugehöriges Animal finden
		Animal animal = observation.getAnimal();
		
		//Observation und Animal löschen
		observationRepository.deleteById(id);
		animalRepository.delete(animal);
	}
	
	
	

}
