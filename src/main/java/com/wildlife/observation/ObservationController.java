package com.wildlife.observation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@CrossOrigin(origins = "*") //damit Webclient von localhost zugreifen darf
@RestController
@RequestMapping("/observation")

public class ObservationController {
	
	@Autowired
	ObservationService observationService;
	
	//GET um alle Beobachtungen abzurufen
	@GetMapping
	public List<Observation> getAllObservation(){
		return observationService.getObservationList();
	}
	
	//GET um Beobachtung mit ID abzurufen
	@GetMapping("/{id}") 
	public Observation getObservationById(@PathVariable Long id) {
		return observationService.getObservation(id);
	}
	//Post um neue Beobachtung anzulegen
	@GetMapping
	public void createObservation(@RequestBody Observation observation) {
		observationService.addObservation(observation);
	}
	//PUT /observation/{id} um Beobachtung zu bearbeiten/aktualisieren
	@PutMapping("/{id}")
	public void updateObservation(@PathVariable Long id, @RequestBody Observation observation) {
		observationService.updateObservation(id, observation);
	}
	//DELETE /observation({id} um Beobachtung zu löschen
	@DeleteMapping("/{id}")
	public void deleteObservation(@PathVariable Long id) {
		observationService.deleteObservation(id);
	}
	
	

}
