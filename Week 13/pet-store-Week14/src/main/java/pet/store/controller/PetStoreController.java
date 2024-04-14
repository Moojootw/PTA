package pet.store.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import pet.store.controller.model.PetStoreData;
import pet.store.service.PetStoreService;

//The REST controller for HTTP requests
@RestController
@RequestMapping("/pet_store")
@Slf4j
public class PetStoreController {
	
	
	@Autowired
	private PetStoreService petStoreService;
	
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED) //create
	public PetStoreData findOrCreatePetStore(@RequestBody PetStoreData petStoreData) {
		log.info("Received request for create/update pet store {}", petStoreData);
		return petStoreService.savePetStore(petStoreData);
	}
	
	@PutMapping("/{petStoreId}") //"update"
	public PetStoreData postAPetStore(@PathVariable Long petStoreId, 
			@RequestBody PetStoreData petstoreData) {
		petstoreData.setPetStoreId(petStoreId);
		log.info("Received request to update a pet store {} with the data {}", petStoreId);
		return petStoreService.savePetStore(petstoreData);
	}
	
	@RestController
	public static class SanityTests {
		@GetMapping("/") //this is just a quick test to see if there is a connection
		public String homeTest() {
			return "Yes this is infact working, good luck!";
			}
		}
	}
