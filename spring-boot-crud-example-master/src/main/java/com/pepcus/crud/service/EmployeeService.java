package com.pepcus.crud.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.MethodArgumentNotValidException;

import com.pepcus.crud.entity.Employee;
import com.pepcus.crud.exceptionhandler.ResourceNotFoundException;
import com.pepcus.crud.repository.EmployeeRepository;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepository repository;

	@Transactional(rollbackFor = MethodArgumentNotValidException.class)
	public Employee saveEmployee(Employee employee) {
		return repository.save(employee);

	}

	public Optional<Employee> getEmployeeById(int id) {
		Optional<Employee> employee = repository.findById(id);
		// @throw CustomException if id is null or fails validation
		if (!employee.isPresent()) {
			throw new ResourceNotFoundException("Not found Employee with id = " + id);
		} else {
			// @emp to check lazy or eager loading
			Employee emp = employee.get();
			System.out.println("getting emp id.........." + emp.getId());
			System.out.println("getting emp age.........." + emp.getAge());
			return employee;
		}
	}

	// By default, records are ordered in DESCENDING order. To choose ASCENDING
	// order, used .ascending() method.
	public List<Employee> findAllEmployees(Integer pageNo, Integer pageSize, String sortBy) {
		List<Order> orders = new ArrayList<Order>();
		Order order1 = new Order(Sort.Direction.DESC, "name");
		orders.add(order1);
		Order order2 = new Order(Sort.Direction.ASC, "age");
		orders.add(order2);
		Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy).ascending());
		Page<Employee> pagedResult = repository.findAll(paging);

		if (pagedResult.hasContent()) {
			return pagedResult.getContent();
		} else {
			return new ArrayList<Employee>();
		}
	}

	public String deleteEmployeeById(int id) {
		Optional<Employee> existingEmployee = repository.findById(id);
		// @throw CustomException if id is null or fails validation
		if (!existingEmployee.isPresent()) {
			throw new ResourceNotFoundException("Not found Employee with id = " + id);
		} else {
			repository.deleteById(id);
			return "Employee removed !!";
		}
	}

	public Employee updateEmployeeById(int id, Employee employee) {
		Optional<Employee> existingEmployeeOptional = repository.findById(id);
		// @throw CustomException if id is null or fails validation
		if (!existingEmployeeOptional.isPresent()) {
			throw new ResourceNotFoundException("Not found Employee with id = " + id);
		}
		Employee existingEmployee = existingEmployeeOptional.get();
		existingEmployee.setFirstName(employee.getFirstName());
		existingEmployee.setLastName(employee.getLastName());
		existingEmployee.setMiddleName(employee.getMiddleName());
		existingEmployee.setBirthDate(employee.getBirthDate());
		existingEmployee.setAge(employee.getAge());
		existingEmployee.setAddressList(employee.getAddressList());

		return repository.save(existingEmployee);
	}

}
