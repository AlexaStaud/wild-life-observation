package com.wildlife.genus;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@CrossOrigin(origins = "*") //damit Webclient von localhost zugreifen darf
@RestController
@RequestMapping("/genus")

public class GenusController {
	
	@Autowired
	GenusService genusService;
	
	//Auflistungen
	//GET /genus um Liste aller Gattungen abzurufen
	@GetMapping
	public List<Genus> getAllGenus(){
		return genusService.getGenusList();
	}
	
	//GET um Gattung mit bestimmter ID abzurufen
	@GetMapping("/{id}")
	public Genus getGenusById(@PathVariable("id") Long id) {
		return genusService.getGenus(id);
		
	}
	
	//POST um neue Gattung anzulegen
	@PostMapping
	public void createGenus(@RequestBody Genus genus) {
		genusService.addGenus(genus);
	}
	
	//PUT /genus/{id} um Gattung aktualisieren
	@PutMapping("/{id}")
	public void updateGenus(@PathVariable("id") Long id, @RequestBody Genus genus) {
		genusService.updateGenus(id, genus);
	}
	
	//DELETE /genus/{id} um Gattung zu löschen
	@DeleteMapping("/{id}")
	public void deleteGenus(@PathVariable("id") Long id) {
		genusService.deleteGenus(id);
	}

}
