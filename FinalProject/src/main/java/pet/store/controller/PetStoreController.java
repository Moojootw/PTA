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
import sisims.entity.Category;
import sisims.entity.Employee;
import sisims.entity.Item;
import sisims.entity.Transaction;
import sisims.entity.TransactionItems;
import sisims.service.CategoryService;
import sisims.service.EmployeeService;
import sisims.service.ItemService;
import sisims.service.TransactionItemsService;
import sisims.service.TransactionService;

//The REST controller for HTTP requests
@RestController
@RequestMapping("/sisims")
@Slf4j
public class PetStoreController {
	
	@Autowired
	private ItemService itemService;
	
	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private TransactionItemsService transactionItemsService;
	
	@Autowired
	private TransactionService transactionService;
	
	@Autowired
	private CategoryService categoryService;
	
//	Create an item (POST in /item)
	@PostMapping("/item")
	@ResponseStatus(code = HttpStatus.CREATED)
	public Item createNewItem (@RequestBody Item item) {
		log.info("Creating a new item {}", item);
		return itemService.createNewItem(item);
	}
	
//	Retrieve an item details (GET in /item with an /id)
	@GetMapping("/item/{id}")
	@ResponseStatus(code = HttpStatus.OK)
	public Item getItembyId(@PathVariable Long id) {
		log.info("Getting item information with ID {}", id);
		return itemService.getItemWithId(id);
	}
	
//	Update an item in the system (PUT /item with /id)
	@PutMapping("item/{id}")
	@ResponseStatus(code = HttpStatus.OK)
	public Item updateItemWithId(@PathVariable Long id, @RequestBody Item item) {
		item.setItemId(id);
		log.info("Updating item {} with ID {}", item , id);
		return itemService.updateItemWithId(item);
	}

//	Delete an item (DELETE /item with /id)
	@DeleteMapping("/item/{id}")
	@ResponseStatus(code = HttpStatus.OK)
	public void removeItemFromInventoryWithId(@PathVariable Long id) {
		log.info("Removing item from inventory with Id {}", id);
		itemService.deleteItemWithId(id);
	}
	
	
//	employee table features:
	
	
//	Create (add) a new employee (POST in /employee)
	@PostMapping("/employee")
	@ResponseStatus(code = HttpStatus.CREATED)
	public Employee createNewItem (@RequestBody Employee employee) {
		log.info("Creating a new employee {}", employee);
		return employeeService.createNewEmployee(employee);
	}
	
//	Retrieve employee roaster (GET in /employee)
	@GetMapping("/employee/{id}")
	@ResponseStatus(code = HttpStatus.OK)
	public Employee getEmployeebyId(@PathVariable Long id) {
		log.info("Getting employee information with ID {}", id);
		return employeeService.getEmployeeWithId(id);
	}
	
//	Update an employee’s details (PUT in /employee with /id)
	@PutMapping("/employee/{id}")
	@ResponseStatus(code = HttpStatus.OK)
	public Employee updateEmployeeWithId(@PathVariable Long id, @RequestBody Employee employee) {
		log.info("Updating employee details with ID {}", id);
		return employeeService.updateEmployeeWithId(id);
	}
	
//	Delete (Remove) an employee (Delete /employee with /id)
	@DeleteMapping("/employee/{id}")
	@ResponseStatus(code = HttpStatus.OK)
	public void removeEmployeeWithId(@PathVariable Long id) {
		log.info("Removing employee with Id {}", id);
		employeeService.deleteEmployeeWithId(id);
	}
	
//	transaction_items table feature:
	
	
//	Retrieve details on a transaction (GET in /transaction_items)
	@GetMapping("/transactionItems/{id}")
	@ResponseStatus(code = HttpStatus.OK)
	public TransactionItems getTransactionItemsbyId(@PathVariable Long id) {
		log.info("Retriving transaction ID {}", id);
		return transactionItemsService.getTransactionItemsWithId(id);
	}
	
//	Delete (Remove) an item from a transaction (DELETE in /transaction_items with /item_id)
	@DeleteMapping("/transactionItems/{id}")
	@ResponseStatus(code = HttpStatus.OK)
	public void removeTransactionItemsWithId(@PathVariable Long id) {
		log.info("Removing item from transaction with Id {}", id);
		transactionItemsService.deleteTransactionItemsWithId(id);
	}
	
//	transaction table features:
	
	
//	Update a transaction (PUT in /transaction with /id)
	@PutMapping("/transaction/{id}")
	@ResponseStatus(code = HttpStatus.OK)
	public Transaction updateATransactionWithId(@PathVariable Long id, @RequestBody Transaction transaction) {
		log.info("Updating a transaction with ID {}: {}", id, transaction);
		return transactionService.updateATransactionWithId(transaction);
	}
//	Retrieve a transaction (GET in /transaction with /id)
	@GetMapping("/transaction/{id}")
	@ResponseStatus(code = HttpStatus.OK)
	public Transaction getTransactionWithId(@PathVariable Long id) {
		log.info("Getting transaction information with ID {}", id);
		return transactionService.getTransactionWithId(id);
	}
	

//	Delete a transaction (DELETE in /transaction with/id)
	@DeleteMapping("/transaction/{id}")
	@ResponseStatus(code = HttpStatus.OK)
	public void deleteTransactionWithId(@PathVariable Long id) {
		log.info("Deleting transaction ID: {}", id);
		transactionService.deleteTransaction(id);
	}
		
		
//	category table features:
		
		
//	Create a new category (POST in /category)
	@PostMapping("/category")
	@ResponseStatus(code = HttpStatus.CREATED)
	public Category createNewCategory(@RequestBody Category category) {
		log.info("Creating new category");
		return categoryService.saveCategory(category);
	}
	
//	Retrieve information about a category (GET in /category with /id)
	@GetMapping("/category/{id}")
	@ResponseStatus(code = HttpStatus.OK)
	public Category getCatagoryDetails(@PathVariable Long id) {
		log.info("Requesting details on a category with an ID of {}", id);
		return categoryService.getCategoryWithId(id);
	}
		
//	Update a category with items (POST in /category with /id)
	@PutMapping("/category/{id}")
	@ResponseStatus(code = HttpStatus.OK)
	public Category updateCategoryInformation(@PathVariable Long id, @RequestBody Category category) {
		log.info("Updating the details of category with ID of {}", id);
		return categoryService.updateCategoryWithID(id);
	}
		
//	Delete a category (DELETE in /category)
	@DeleteMapping("/category/{id}")
	@ResponseStatus(code = HttpStatus.OK)
	public void deleteACategory(@PathVariable Long id) {
		log.info("Deleting category with ID: {}", id);
		categoryService.deleteCategoryWithId(id);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
//	@Autowired
//	private PetStoreService petStoreService;
//// 																					|
////	The following are sorted by Mapping and ordered alphabetically by method name	V
//	
//	@DeleteMapping("{petStoreId}")
//	@ResponseStatus(code = HttpStatus.NO_CONTENT) //204
//	public Map<String, String> deleteAPetStoreById(@PathVariable Long petStoreId) {
//		petStoreService.deleteAPetStoreById(petStoreId);
//		return new HashMap<String, String>() { 
//			private static final long serialVersionUID = 1L; //not needed but just in case
//			{put("Pet store with ID:",+ petStoreId + " deleted");
//			}}; //end of return
//	}
//	
//	@PostMapping("{petStoreId}/customer")
//	@ResponseStatus(code = HttpStatus.CREATED) //201
//	public PetStoreCustomer createACustomer(@PathVariable Long petStoreId, @RequestBody PetStoreCustomer petStoreCustomer) {
//		log.info("Received request at pet Store {} to create a customer with data {}", petStoreId, petStoreCustomer);
//		return petStoreService.saveCustomer(petStoreId, petStoreCustomer);
//	}
//	
//	@PostMapping("{petStoreId}/employee")
//	@ResponseStatus(code = HttpStatus.CREATED) //201
//	public PetStoreEmployee createAnEmployee(@PathVariable Long petStoreId, @RequestBody PetStoreEmployee petStoreEmployee) {
//		log.info("Received request at pet Store {} to create an employee with data {}", petStoreId, petStoreEmployee);
//		return petStoreService.saveEmployee(petStoreId, petStoreEmployee);
//	}
//	
//	@PostMapping
//	@ResponseStatus(code = HttpStatus.CREATED) //201
//	public PetStoreData findOrCreatePetStore(@RequestBody PetStoreData petStoreData) {
//		log.info("Received request for create/update pet store {}", petStoreData);
//		return petStoreService.savePetStore(petStoreData);
//	}
//	
//	@PutMapping("/{petStoreId}") //201
//	public PetStoreData postAPetStore(@PathVariable Long petStoreId, 
//			@RequestBody PetStoreData petstoreData) {
//		petstoreData.setPetStoreId(petStoreId);
//		log.info("Received request to update a pet store {} with the data {}", petStoreId);
//		return petStoreService.savePetStore(petstoreData);
//	}
//	
//	@GetMapping("/{petStoreId}")
//	@ResponseStatus(code = HttpStatus.OK) //200
//	public PetStore getAPetStoreById(@PathVariable Long petStoreId) {
//		return petStoreService.retrieveAPetStoreById(petStoreId);
//	}
//	
//	@GetMapping
//	@ResponseStatus(code = HttpStatus.OK) //200
//	public List<PetStoreData> getListOfPetStore() {
//		return petStoreService.retrieveAllPetStores();
//		
//	}
//	
//	@RestController
//	public static class SanityTests {
//		@GetMapping("/") //this is just a quick test to see if there is a connection
//		public String homeTest() {
//			return "Yes this is infact working, good luck!";
//			}
		}
