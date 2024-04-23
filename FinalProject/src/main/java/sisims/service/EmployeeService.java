package sisims.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sisims.controller.model.SisimsData.EmployeeData;
import sisims.dao.EmployeeDao;
import sisims.dao.TransactionDao;
import sisims.entity.Employee;
import sisims.entity.Transaction;



@Service
public class EmployeeService {
    
    @Autowired
    private EmployeeDao employeeDao;
    
    @Autowired
    private TransactionDao transactionDao;
    
  //this was pretty much stolen from petstore
    
    @Transactional(readOnly = false)
    public EmployeeData createNewEmployee(EmployeeData employeeData) {
        Employee employee = new Employee();
        copyEmployeeFields(employee, employeeData);
        employee = employeeDao.save(employee);
        return convertToEmployeeData(employee);
    }

    private void copyEmployeeFields(Employee employee, EmployeeData employeeData) {
        employee.setEmployeeFirstName(employeeData.getEmployeeFirstName());
        employee.setEmployeeLastName(employeeData.getEmployeeLastName());
        employee.setEmployeePhone(employeeData.getEmployeePhone());
        employee.setEmployeeJobTitle(employeeData.getEmployeeJobTitle());
    }
    
    private EmployeeData convertToEmployeeData(Employee employee) {
        return new EmployeeData(employee);
    }
    
    @Transactional(readOnly = true)
    public EmployeeData getEmployeeWithId(Long employeeId) {
    	 Employee employee = employeeDao.findById(employeeId).orElseThrow(() -> new NoSuchElementException("Employee with ID: " + employeeId + " not found"));
        return convertToEmployeeData(employee); 
    }
    
    @Transactional(readOnly = false)
    public EmployeeData updateEmployeeWithID(Long employeeId, EmployeeData employeeData) {
        Employee employee = employeeDao.findById(employeeId).orElseThrow(() -> new RuntimeException("Employee not found"));
        copyEmployeeFields(employee, employeeData);
        employee = employeeDao.save(employee);
        return convertToEmployeeData(employee);
    }
    
    @Transactional
    //Custom
    //set the transaction that the employee was deleted to null
    public void deleteEmployeeWithId(Long employeeId) {
        Employee employee = employeeDao.findById(employeeId)
            .orElseThrow(() -> new NoSuchElementException("Employee with ID:" + employeeId + " not found"));
        List<Transaction> transactions = transactionDao.findByEmployee(employee);
        transactions.forEach(transaction -> {
            transaction.setEmployee(null);
            transactionDao.save(transaction);
        });
        employeeDao.delete(employee);
    }
}

