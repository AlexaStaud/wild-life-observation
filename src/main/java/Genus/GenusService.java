package Genus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GenusService {
	
	@Autowired
	GenusRepository genusRepository;

}
