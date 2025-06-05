package com.wildlife.location;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LocationService {
	
	//eingliedern von LocationRepository
	@Autowired
	LocationRepository locationRepository;
	
	//Liste aller Locations
	public List<Location> getLocationList() {
		
		ArrayList<Location> locationList = new ArrayList<>();
		Iterator<Location> it = locationRepository.findAll().iterator();
		while(it.hasNext()) {
			locationList.add(it.next());
		}
		return locationList;
	}
	
	//eine Location zurückgeben
	public Location getLocation(Long lNr) {
		return locationRepository.findById(lNr).orElse(null);
	}
	
	//eine Location hinzufügen
	public void addLocation(Location location) {
		locationRepository.save(location);
	}
	
	//eine Location bearbeiten
	public void updateLocation(Long lNr, Location location) {
		Location bestehendeLocation = locationRepository.findById(lNr).orElse(null);
		
		bestehendeLocation.setCity(location.getCity());
		bestehendeLocation.setDescription(location.getDescription());
		
		locationRepository.save(bestehendeLocation);
	}
	
	//eine Location löschen
	public void deleteLocation(Long lNr) {
		locationRepository.deleteById(lNr);
	}
	
		
}
