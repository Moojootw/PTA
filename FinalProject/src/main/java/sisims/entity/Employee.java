package sisims.entity;

import java.util.List;

//import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class Employee {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long employeeId;
	private String employeeFirstName;
	private String employeeLastName;
	private String employeePhone;
	private String employeeJobTitle;
	
	@OneToMany(mappedBy = "employee")//, cascade = CascadeType.REMOVE)//, orphanRemoval = true) 	//Im leaving these here because there is a chance these will be needed in the future.
	private List<Transaction> transactions;
}
