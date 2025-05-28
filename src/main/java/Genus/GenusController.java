package Genus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GenusController {
	
	@Autowired
	GenusService genusService;

}
