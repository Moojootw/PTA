package pet.store.controller.model;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import lombok.Data;
import lombok.NoArgsConstructor;
import sisims.entity.Customer;
import sisims.entity.Employee;
import sisims.entity.PetStore;

@Data
@NoArgsConstructor
public class PetStoreData {
	//Database entries for a physical pet store
	private Long petStoreId;
	private String petStoreName;
	private String petStoreAddress;
	private String petStoreCity;
	private String petStoreState;
	private String petStoreZip;
	private String petStorePhone;
	
	//these sets help contain employees and customers for each pet store
	private Set<PetStoreCustomer> customers = new HashSet<>();
	private Set<PetStoreEmployee> employees = new HashSet<>();
	
	public PetStoreData(PetStore petStore) {
		//creates a pet store entity into a data transfer object
		petStoreId = petStore.getPetStoreId();
		petStoreName = petStore.getPetStoreName();
		petStoreAddress = petStore.getPetStoreAddress();
		petStoreCity = petStore.getPetStoreCity();
		petStoreState = petStore.getPetStoreState();
		petStoreZip = petStore.getPetStoreZip();
		petStorePhone = petStore.getPetStorePhone();
	
		customers = petStore.getCustomer().stream()
                .map(PetStoreCustomer::new)
                .collect(Collectors.toSet());

		employees = petStore.getEmployees().stream()
                .map(PetStoreEmployee::new)
                .collect(Collectors.toSet());
	        }
	@Data
	@NoArgsConstructor
	public static class PetStoreCustomer {
		//fields for a customer
		private Long customerId;
		private String customerFirstName;
		private String customerLastName;
		private String customerEmail;
		
		public PetStoreCustomer(Customer customer) {
			// convert to a DTO
	        customerId = customer.getCustomerId();
	        customerFirstName = customer.getCustomerFirstName();
	        customerLastName = customer.getCustomerLastName();
	        customerEmail = customer.getCustomerEmail();
	    }
	}
		@Data
		@NoArgsConstructor
	public static class PetStoreEmployee {
			//fields for an employee
		private Long employeeId;
		private String employeeFirstName;
		private String employeeLastName;
		private String employeePhone;
		private String employeeJobTitle;
		
		public PetStoreEmployee (Employee employee) {
			//Employee DTO
			employeeId = employee.getEmployeeId();
			employeeFirstName = employee.getEmployeeFirstName();
			employeeLastName = employee.getEmployeeLastName();
			employeePhone = employee.getEmployeePhone();
			employeeJobTitle = employee.getEmployeeJobTitle();
		}
	}
	
}

