package sisims.entity;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data
@JsonIdentityInfo( //added after an infinite recursion
		generator = ObjectIdGenerators.PropertyGenerator.class,
		property = "petStoreId")
public class PetStore {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	//create the fields for the pet_store table
	private Long petStoreId;
	private String petStoreName;
	private String petStoreAddress;
	private String petStoreCity;
	private String petStoreState;
	private String petStoreZip;
	private String petStorePhone;
	
	@ManyToMany(cascade = CascadeType.PERSIST)
	//create another table named pet_store_customer with the fields:
	@JoinTable(name = "pet_store_customer", joinColumns = 
	@JoinColumn(name = "pet_store_id"), inverseJoinColumns = 
	@JoinColumn(name = "customer_id"))
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	private Set<Customer> customer = new HashSet <>();
	
	@OneToMany(mappedBy = "petStore", cascade = CascadeType.ALL, orphanRemoval = true)
	
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	private Set<Employee> employees = new HashSet <>();
}
