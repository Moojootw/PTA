package pet.store.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import sisims.entity.Employee;

public interface EmployeeDao extends JpaRepository<Employee, Long> {

}
