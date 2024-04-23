package sisims.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import sisims.entity.Employee;
import sisims.entity.Transaction;

public interface TransactionDao extends JpaRepository<Transaction, Long> {
	//Custom
	//This is used when deleting an employee to make sure that the transactions the employee performed dont get removed. see EmployeeService deleteEmployeeWithId(Long employeeId)
	List<Transaction> findByEmployee(Employee employee);

}
