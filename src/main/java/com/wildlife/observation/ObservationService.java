package com.wildlife.observation;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ObservationService {
	
	@Autowired
	ObservationRepository observationRepository;
	
	
	//Alle Beobachtungen aufrufen
	public List<Observation> getObservationList() {
		
		ArrayList<Observation> observationList = new ArrayList<>();
		
		Iterator<Observation> it = observationRepository.findAll().iterator();
		while(it.hasNext()) {
			observationList.add(it.next());	
		}
		return observationList;

	}
	//Beobachtung mit ID aufrufen
	public Observation getObservation(Long id) {
		return observationRepository.findById(id).orElse(null);
	}
	
	
	//Neue Beobachtung speichern
	public void addObservation(Observation observation) {
		observationRepository.save(observation);
	}
	
	//Beobachtung aktualisieren
	public void updateObservation(Long id, Observation updateObservation) {
		observationRepository.save(updateObservation);
	}
	
	
	//Beobachtung löschen
	public void delete(Long id) {
		observationRepository.deleteById(id);
	}
	
	
	

}
