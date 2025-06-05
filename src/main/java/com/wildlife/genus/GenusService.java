package com.wildlife.genus;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GenusService {
	
	
	//Eingliedern von GenusRepository
	@Autowired
	GenusRepository genusRepository;

	//Alle Gattungen abrufen
	public List<Genus> getGenusList() {
		
		ArrayList<Genus>genusList = new ArrayList<>();
		Iterator<Genus> it = genusRepository.findAll().iterator();
		while(it.hasNext()) {
			genusList.add(it.next());
		}
		return genusList;
	}
	//Eine Gattung mit ID abrufen
	public Genus getGenus(Long id) {
		return genusRepository.findById(id).orElse(null);
	}
	
	//Neue Gattung speichern
	public void addGenus(Genus genus) {
		genusRepository.save(genus);
	}
	
	//Gattung aktualisieren
    public void updateGenus(Long id, Genus updatedGenus) {
        //setzt id
        updatedGenus.setId(id);
        genusRepository.save(updatedGenus);
    }
	
	   //Gattung löschen
    public void deleteGenus(Long id) {
        genusRepository.deleteById(id);
    }

}
