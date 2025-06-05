package com.wildlife.location;

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
@RequestMapping("/locations")
public class LocationController {
	
	//einbinden von LocationService
	@Autowired
	LocationService locationService;
	
	//liste aller Locations
	@GetMapping
	public List<Location> alleLocations(){
		return locationService.getLocationList();
	}
	
	//eine Location ausgeben
	@GetMapping("/{lNr}")
	public Location getLocation(@PathVariable("lNr") long lNr) {
		return locationService.getLocation(lNr);
	}
	
	//eine Location hinzufügen
	@PostMapping
	public void addLocation(@RequestBody Location location) {
		locationService.addLocation(location);
	}
	
	//eine Location bearbeiten
	@PutMapping("/{lNr}")
	public void updateLocation(@PathVariable("lNr") long lNr, @RequestBody Location location) {
		locationService.updateLocation(lNr, location);
	}
	
	
	//eine Location löschen
	@DeleteMapping("/{lNr}")
	public void deleteLocation(@PathVariable("lNr") long lNr) {
		locationService.deleteLocation(lNr);
	}
	
}
