package com.wildlife.observation;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ObservationService {
	
	//Eingliedern des ObservationRepository
	@Autowired
	ObservationRepository observationRepository;
	
	
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
		observationRepository.save(observation); //speichert Beobachtung
	}
	
	//Beobachtung aktualisieren
	public void updateObservation(Long id, Observation updateObservation) {
		updateObservation.setId(id); //setzt id, damit es als Änderung und nicht als neue Beobachtung behandelt wird
		observationRepository.save(updateObservation);
	}
	
	
	//Beobachtung löschen
	public void deleteObservation(Long id) {
		observationRepository.deleteById(id);
	}
	
	
	

}
