package com.wildlife.location;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LocationService {
	
	@Autowired
	LocationRepository locationRepository;
	
	public List<Location> getLocationList() {
		
		ArrayList<Location> locationList = new ArrayList<>();
		Iterator<Location> it = locationRepository.findAll().iterator();
		while(it.hasNext()) {
			locationList.add(it.next());
		}
		return locationList;
	}
	
	public Location getLocation(long lNr) {
		return locationRepository.findById(lNr).orElse(null);
	}
	
	public void addLocation(Location location) {
		locationRepository.save(location);
	}
	
	public void updateLocation(long lNr, Location location) {
		Location bestehendeLocation = locationRepository.findById(lNr).orElse(null);
		
		bestehendeLocation.setCity(location.getCity());
		bestehendeLocation.setDescription(location.getDescription());
		
		locationRepository.save(bestehendeLocation);
	}
	
	public void deleteLocation(long lNr) {
		locationRepository.deleteById(lNr);
	}
	
		
}
