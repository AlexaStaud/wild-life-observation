package com.wildlife.animal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AnimalController {
	
	@Autowired
	AnimalService animalService;
	
}
