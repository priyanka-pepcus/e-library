package com.pepcus.crud.controller;

import java.util.List;
import java.util.Optional;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;

import static com.pepcus.crud.specifications.EmployeeSpecsUtil.*;
import static org.springframework.data.jpa.domain.Specification.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pepcus.crud.entity.Address;
import com.pepcus.crud.entity.Employee;
import com.pepcus.crud.repository.EmployeeRepository;
import com.pepcus.crud.service.EmployeeService;

/*
 * @authour Priyanka Gupta
 * @since 15/04/2022
 * 
 * @oneToMany annotation in address 
 * @Valid
 * @Transactional on save method
 * @throw custom exception if id not found on deleteById, updateById, getById methods
 * @paging and @sorting on findAll method
 * @actuator dependency in pom.xml to check health of this application from @heartbeat application
 * @JpaSpecificationExecutor for creating specifications from @Employee and @Address  Table
 * @Formula
 * @Temporal(TemporalType.DATE) 
 * CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true
 * 
*/
@RestController
@RequestMapping("/employees")
public class EmployeeController {

  @Autowired
  private EmployeeService employeeService;
  @Autowired
  private EmployeeRepository employeeRepository;

  @PostMapping
  public Employee saveEmployee(@Valid @RequestBody Employee employee) {
    System.out.println("get name......."+employee.getFirstName());
    System.out.println("get name......."+employee.getAddressList().get(0).getCity());
    return employeeService.saveEmployee(employee);
  }

  @GetMapping
  public List<Employee> findAllemployee(@RequestParam(defaultValue = "0") Integer pageNo,
      @RequestParam(defaultValue = "10") Integer pageSize, @RequestParam(defaultValue = "id") String sortBy) {
    return employeeService.findAllEmployees(pageNo, pageSize, sortBy);
  }

  @GetMapping("/{id}")
  public Optional<Employee> findEmployeeById(@PathVariable int id) {
    return employeeService.getEmployeeById(id);
  }

  @PutMapping("/{id}")
  public Employee updateEmployeeById(@PathVariable(name = "id") int id, @RequestBody Employee employee) {

    return employeeService.updateEmployeeById(id, employee);
  }

  @DeleteMapping("/{id}")
  public String deleteEmployeeById(@PathVariable int id) {
    return employeeService.deleteEmployeeById(id);
  }

  @GetMapping("/findByNameAgeSpec")
  public List<Employee> findByNameAge(@RequestParam("name") String name, @RequestParam("age") int age) {
    return employeeRepository.findAll(where(getEmployeesByNameSpec(name).and(getEmployeesByAgeSpec(age))));
  }

  @GetMapping("/findByNamePincode")
  public List<Employee> findByNamePincode(@RequestParam("firstName") String firstName,
      @RequestParam("pincode") String pincode) {
    return employeeRepository.findAll(where(getEmployeesByNameSpec(firstName).and(getEmployeesByPincodeSpec(pincode))));
  }
}
