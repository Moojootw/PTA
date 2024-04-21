package sisims.controller;

import java.util.List;
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
import sisims.controller.model.SisimsData.CategoryData;
import sisims.controller.model.SisimsData.EmployeeData;
import sisims.controller.model.SisimsData.ItemData;
import sisims.controller.model.SisimsData.TransactionData;
import sisims.controller.model.SisimsData.TransactionItemsData;
import sisims.service.*;




//The REST controller for HTTP requests
@RestController
@RequestMapping("/sisims")
@Slf4j
public class SisimsController {
	
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
	public ItemData createNewItem(@RequestBody ItemData itemData) {
	    log.info("Creating a new item: {}", itemData);
	    return itemService.createNewItem(itemData);
	}
	
//	Retrieve an item details (GET in /item with an /id)
	@GetMapping("/item/{id}")
	@ResponseStatus(code = HttpStatus.OK)
	public ItemData getItemById(@PathVariable Long id) {
	    log.info("Getting item information with ID: {}", id);
	    return itemService.getItemWithId(id);
	}
	
//	Update an item in the system (PUT /item with /id)
	@PutMapping("/item/{id}")
	@ResponseStatus(code = HttpStatus.OK)
	public ItemData updateItem(@PathVariable Long id, @RequestBody ItemData itemData) {
	    log.info("Updating item with ID: {}", id);
	    return itemService.updateItemWithId(id, itemData);
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
	public EmployeeData createNewItem (@RequestBody EmployeeData employeeData) {
		log.info("Creating a new employee {}", employeeData);
		return employeeService.createNewEmployee(employeeData);
	}
	
//	Retrieve employee roaster (GET in /employee)
	@GetMapping("/employee/{id}")
	@ResponseStatus(code = HttpStatus.OK)
	public EmployeeData getEmployeebyId(@PathVariable Long id) {
		log.info("Getting employee information with ID {}", id);
		return employeeService.getEmployeeWithId(id);
	}
	
//	Update an employee’s details (PUT in /employee with /id)
	@PutMapping("/employee/{id}")
	@ResponseStatus(code = HttpStatus.OK)
    public EmployeeData updateEmployee(@PathVariable Long id, @RequestBody EmployeeData employeeData) {
        log.info("Updating employee details with ID {}", id);
        return employeeService.updateEmployeeWithID(id, employeeData);
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
	@GetMapping("/transactionItems/{transactionId}")
    public List<TransactionItemsData> getTransactionItemsByTransactionId(@PathVariable Long transactionId) {
        return transactionItemsService.getTransactionItemsByTransactionId(transactionId);
    }
	
//	Delete (Remove) an item from a transaction (DELETE in /transaction_items with /item_id)
	@DeleteMapping("/transactionItems/{transactionId}/{itemId}")
    public void removeTransactionItem(@PathVariable Long transactionId, @PathVariable Long itemId) {
        transactionItemsService.deleteTransactionItemsWithId(transactionId, itemId);
    }
	
//	transaction table features:
	
	
//	Update a transaction (PUT in /transaction with /id)
	@PutMapping("/transaction/{id}")
	@ResponseStatus(code = HttpStatus.OK)
	public TransactionData updateATransactionWithId(@PathVariable Long id, @RequestBody TransactionData transactionData) {
	    log.info("Updating a transaction with ID {}: {}", id, transactionData);
	    return transactionService.updateTransactionWithId(id, transactionData);
	}
	
//	Retrieve a transaction (GET in /transaction with /id)
	@GetMapping("/transaction/{id}")
	@ResponseStatus(code = HttpStatus.OK)
	public TransactionData getTransactionWithId(@PathVariable Long id) {
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
	public CategoryData createNewCategory(@RequestBody CategoryData categoryData) {
		log.info("Creating new category: {}", categoryData);
		return categoryService.saveACategory(categoryData);
	}
	
//	Retrieve information about a category (GET in /category with /id)
	@GetMapping("/category/{id}")
	@ResponseStatus(code = HttpStatus.OK)
	public CategoryData getCatagoryDetails(@PathVariable Long id) {
		log.info("Requesting details on a category with an ID of {}", id);
		return categoryService.getCategoryWithId(id);
	}
		
//	Update a category with items (POST in /category with /id)
	@PutMapping("/category/{id}")
	@ResponseStatus(code = HttpStatus.OK)
	public CategoryData updateCategoryInformation(@PathVariable Long id, @RequestBody CategoryData categoryData) {
		log.info("Updating the details of category with ID of {}", id);
		return categoryService.updateCategoryWithID(id, categoryData);
	}
		
//	Delete a category (DELETE in /category)
	@DeleteMapping("/category/{id}")
	@ResponseStatus(code = HttpStatus.OK)
	public void deleteACategory(@PathVariable Long id) {
		log.info("Deleting category with ID: {}", id);
		categoryService.deleteCategoryWithId(id);
	}
	
	@RestController
	public static class SanityTests {
		@GetMapping("/") //this is just a quick test to see if there is a connection
		public String homeTest() {
			return "Yes this is infact working, good luck!";
			}
	}
}

