package com.wildlife.location;

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
@RequestMapping("/locations")
public class LocationController {
	
	@Autowired
	LocationService locationService;
	
	@GetMapping
	public List<Location> alleLocations(){
		return locationService.getLocationList();
	}
	
	@GetMapping("/{lNr}")
	public Location getLocation(@PathVariable("lNr") long lNr) {
		return locationService.getLocation(lNr);
	}
	
	@PostMapping
	public void addLocation(@RequestBody Location location) {
		locationService.addLocation(location);
	}
	
	@PutMapping("/{lNr}")
	public void updateLocation(@PathVariable("lNr") long lNr, @RequestBody Location location) {
		locationService.updateLocation(lNr, location);
	}
	
	@DeleteMapping("/{lNr}")
	public void deleteLocation(@PathVariable("lNr") long lNr) {
		locationService.deleteLocation(lNr);
	}
	
}
