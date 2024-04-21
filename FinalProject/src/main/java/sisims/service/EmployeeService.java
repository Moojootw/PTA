package sisims.service;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sisims.controller.model.SisimsData.EmployeeData;
import sisims.dao.EmployeeDao;
import sisims.entity.Employee;



@Service
public class EmployeeService {
    
    @Autowired
    private EmployeeDao employeeDao;
   
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
    
    @Transactional(readOnly = false)
    public void deleteEmployeeWithId(Long employeeId) {
        Employee employee = employeeDao.findById(employeeId)
            .orElseThrow(() -> new NoSuchElementException("Employee with ID:" + employeeId + " not found"));
        employeeDao.delete(employee);
    }
}

