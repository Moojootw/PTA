package pet.store.service;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pet.store.controller.model.PetStoreData;
import pet.store.controller.model.PetStoreData.PetStoreCustomer;
import pet.store.controller.model.PetStoreData.PetStoreEmployee;
import pet.store.dao.CustomerDao;
import pet.store.dao.EmployeeDao;
import pet.store.dao.PetStoreDao;
import pet.store.entity.Customer;
import pet.store.entity.Employee;
import pet.store.entity.PetStore;


@Service
public class PetStoreService {
	
	//auto wire annotations for each table transaction go here
	
	@Autowired
	private PetStoreDao petStoreDao;
	@Autowired
    private EmployeeDao employeeDao;
	@Autowired
    private CustomerDao customerDao;
	
	//////////////////////////////////////////////////////////////////
	// methods relating to creating or updating a pet store
	
	private PetStore findOrCreatePetStore(Long petStoreId) {
        if (Objects.isNull(petStoreId)) {
            return new PetStore();
        } else {
            return findPetStoreById(petStoreId);
        }
    }
	
	@Transactional(readOnly = false)
	public PetStoreData savePetStore(PetStoreData petStoreData) {
		PetStore petStore = findOrCreatePetStore(petStoreData.getPetStoreId());
		copyPetStoreFields(petStore, petStoreData);
		PetStore savedPetStore = petStoreDao.save(petStore);
		return convertToPetStoreData(savedPetStore);
	}
	
	private PetStore findPetStoreById(Long petStoreId) {
		return petStoreDao.findById(petStoreId)
				.orElseThrow(() -> new NoSuchElementException("Pet store ID:" + petStoreId + " not found"));
	}
	
	private PetStoreData convertToPetStoreData(PetStore petStore) {
	    PetStoreData petStoreData = new PetStoreData();
	    // petStoreData.setPetStoreId(petStore.getPetStoreId());
	    petStoreData.setPetStoreName(petStore.getPetStoreName());
	    petStoreData.setPetStoreAddress(petStore.getPetStoreAddress());
	    petStoreData.setPetStoreCity(petStore.getPetStoreCity());
	    petStoreData.setPetStoreState(petStore.getPetStoreState());
	    petStoreData.setPetStoreZip(petStore.getPetStoreZip());
	    petStoreData.setPetStorePhone(petStore.getPetStorePhone());
	    return petStoreData;
	}
	
	private void copyPetStoreFields(PetStore petStore, PetStoreData petStoreData) {
		// petStore.setPetStoreId(petStoreData.getPetStoreId());
	    petStore.setPetStoreName(petStoreData.getPetStoreName());
	    petStore.setPetStoreAddress(petStoreData.getPetStoreAddress());
	    petStore.setPetStoreCity(petStoreData.getPetStoreCity());
	    petStore.setPetStoreState(petStoreData.getPetStoreState());
	    petStore.setPetStoreZip(petStoreData.getPetStoreZip());
	    petStore.setPetStorePhone(petStoreData.getPetStorePhone());
	}
	//////////////////////////////////////////////////////////////////////////////////////////////////////////
	// methods for listing out every pet store
	
	
	
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////
	// methods for creating or updating an employee at a pet store
	
	
	@Transactional(readOnly = false)
	public PetStoreEmployee saveEmployee(Long petStoreId, PetStoreEmployee petStoreEmployee) {
		PetStore petStore = findPetStoreById(petStoreId);
		Employee employee = findOrCreateEmployee(petStoreId, petStoreEmployee.getEmployeeId());
		copyEmployeeFields(employee, petStoreEmployee);
		employee.setPetStore(petStore);
		petStore.getEmployees().add(employee);
		Employee dbEmployee = employeeDao.save(employee);
		return new PetStoreEmployee(dbEmployee);
	}
	
	private Employee findOrCreateEmployee(Long petStoreId,Long employeeId) {
		 if (Objects.isNull(employeeId)) {
	            return new Employee();
	        } else {
	            return findEmployeeById(petStoreId, employeeId);
	        }
	}

	private Employee findEmployeeById(Long petStoreId, Long employeeId) {
		 Employee employee = employeeDao.findById(employeeId).orElseThrow(() -> new NoSuchElementException("Employee with ID:"+ employeeId + " not found"));
		 if (petStoreId.equals(employee.getPetStore().getPetStoreId())) {
			 return employee;
		 }else {
			 throw new IllegalArgumentException("Employee ID:" + employeeId + " is not associated with " + petStoreId);
		 }
	}
	
	private void copyEmployeeFields(Employee employee, PetStoreEmployee petStoreEmployee) {
		// employee.setEmployeeId(petStoreEmployee.getEmployeeId());
		employee.setEmployeeFirstName(petStoreEmployee.getEmployeeFirstName());
		employee.setEmployeeLastName(petStoreEmployee.getEmployeeLastName());
		employee.setEmployeePhone(petStoreEmployee.getEmployeePhone());
		employee.setEmployeeJobTitle(petStoreEmployee.getEmployeeJobTitle());
	}

	
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// methods for adding a customer to a pet store
	
	@Transactional(readOnly = false)
	public PetStoreCustomer saveCustomer(Long petStoreId, PetStoreCustomer petStoreCustomer) {
		PetStore petStore = findPetStoreById(petStoreId);
		Customer customer = findOrCreateCustomer(petStore.getPetStoreId(), petStoreCustomer.getCustomerId());
		copyCustomerFields(customer, petStoreCustomer);
		customer.getPetStore().add(petStore);
		petStore.getCustomer().add(customer);
		Customer dbCustomer = customerDao.save(customer);
		return new PetStoreCustomer(dbCustomer);
	}

	private Customer findOrCreateCustomer(Long petStoreId, Long customerId) {
		if(Objects.isNull(customerId)) {
			return new Customer();
		}else {
			return findCustomerById(petStoreId, customerId);
		}
		
	}

	private Customer findCustomerById(Long petStoreId, Long customerId) {
		Customer customer = customerDao.findById(customerId).orElseThrow(() -> new NoSuchElementException("Customer with ID:" + customerId + " not found" ));
		customer.getPetStore().stream().anyMatch(store -> store.getPetStoreId().equals(petStoreId)); // iterates through the pet stores to find the pet store id that the customer is a part of 
		return customer;
	}
	
	private void copyCustomerFields(Customer customer, PetStoreCustomer petStoreCustomer) {
		//customer.setCustomerId(petStoreCustomer.getCustomerId());
		customer.setCustomerFirstName(petStoreCustomer.getCustomerFirstName());
		customer.setCustomerLastName(petStoreCustomer.getCustomerFirstName());
		customer.setCustomerEmail(petStoreCustomer.getCustomerEmail());
	}
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// methods for retrieving pet store information
	
	@Transactional(readOnly = true)
	public List<PetStoreData> retrieveAllPetStores() {
		
		List<PetStore> petStores = petStoreDao.findAll();
		List<PetStoreData> result = new LinkedList<>();
		
		for(PetStore petStore : petStores) {
			PetStoreData psd = new PetStoreData(petStore);
			psd.getCustomers().clear();
			psd.getEmployees().clear();
			result.add(psd);	
		}
		return result;
	}

	@Transactional(readOnly = true)
	public PetStore retrieveAPetStoreById(Long petStoreId) {
		return petStoreDao.findById(petStoreId).orElseThrow(() -> new NoSuchElementException("Pet store with ID:" + petStoreId + " not found" ));
	}
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// method for deleting a pet store

	@Transactional(readOnly = false)
	public void deleteAPetStoreById(Long petStoreId) {
		PetStore petStore = petStoreDao.findById(petStoreId).orElseThrow(() -> new NoSuchElementException("Pet store with ID:" + petStoreId + " not found" ));
		petStoreDao.delete(petStore);
	}
	
	
	
}
