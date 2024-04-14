package pet.store.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
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
import pet.store.controller.model.PetStoreData.PetStoreCustomer;
import pet.store.controller.model.PetStoreData.PetStoreEmployee;
import pet.store.entity.PetStore;
import pet.store.service.PetStoreService;

//The REST controller for HTTP requests
@RestController
@RequestMapping("/pet_store")
@Slf4j
public class PetStoreController {
	
	
	@Autowired
	private PetStoreService petStoreService;
// 																					|
//	The following are sorted by Mapping and ordered alphabetically by method name	V
	
	@DeleteMapping("{petStoreId}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT) //204
	public Map<String, String> deleteAPetStoreById(@PathVariable Long petStoreId) {
		petStoreService.deleteAPetStoreById(petStoreId);
		return new HashMap<String, String>() { 
			private static final long serialVersionUID = 1L; //not needed but just in case
			{put("Pet store with ID:",+ petStoreId + " deleted");
			}}; //end of return
	}
	
	@PostMapping("{petStoreId}/customer")
	@ResponseStatus(code = HttpStatus.CREATED) //201
	public PetStoreCustomer createACustomer(@PathVariable Long petStoreId, @RequestBody PetStoreCustomer petStoreCustomer) {
		log.info("Recived request at pet Store {} to create a customer with data {}", petStoreId, petStoreCustomer);
		return petStoreService.saveCustomer(petStoreId, petStoreCustomer);
	}
	
	@PostMapping("{petStoreId}/employee")
	@ResponseStatus(code = HttpStatus.CREATED) //201
	public PetStoreEmployee createAnEmployee(@PathVariable Long petStoreId, @RequestBody PetStoreEmployee petStoreEmployee) {
		log.info("Recived request at pet Store {} to create an employee with data {}", petStoreId, petStoreEmployee);
		return petStoreService.saveEmployee(petStoreId, petStoreEmployee);
	}
	
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED) //201
	public PetStoreData findOrCreatePetStore(@RequestBody PetStoreData petStoreData) {
		log.info("Received request for create/update pet store {}", petStoreData);
		return petStoreService.savePetStore(petStoreData);
	}
	
	@PutMapping("/{petStoreId}") //201
	public PetStoreData postAPetStore(@PathVariable Long petStoreId, 
			@RequestBody PetStoreData petstoreData) {
		petstoreData.setPetStoreId(petStoreId);
		log.info("Received request to update a pet store {} with the data {}", petStoreId);
		return petStoreService.savePetStore(petstoreData);
	}
	
	@GetMapping("/{petStoreId}")
	@ResponseStatus(code = HttpStatus.OK) //200
	public PetStore getAPetStoreById(@PathVariable Long petStoreId) {
		return petStoreService.retrieveAPetStoreById(petStoreId);
	}
	
	@GetMapping
	@ResponseStatus(code = HttpStatus.OK) //200
	public List<PetStoreData> getListOfPetStore() {
		return petStoreService.retrieveAllPetStores();
		
	}
	
	@RestController
	public static class SanityTests {
		@GetMapping("/") //this is just a quick test to see if there is a connection
		public String homeTest() {
			return "Yes this is infact working, good luck!";
			}
		}
	}
