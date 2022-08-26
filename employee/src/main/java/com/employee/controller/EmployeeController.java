package com.employee.controller;

import java.io.IOException;
import java.net.*;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import com.employee.exception.ResourceNotFoundException;
import com.employee.model.Employee;
import com.employee.model.EmployeeBenefit;
import com.employee.repositories.EmployeeRepository;
import com.employee.service.EmployeeService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;



@RestController
@RequestMapping("/api/v1")
public class EmployeeController {

	Logger logger = LoggerFactory.getLogger(EmployeeController.class);

	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private EmployeeService employeeService;

	@GetMapping("/employees")
	public List<Employee> getAllEmployees() {
		logger.info("Get all employee");
		return employeeRepository.findAll();

	}

	@RequestMapping(value = "/listPageable", method = RequestMethod.GET)
	Page<Employee> employeesPageable(Pageable pageable) {
		return employeeRepository.findAll(pageable);

	}

	@GetMapping("/employeebenefit/{id}")
	public ResponseEntity<EmployeeBenefit> getEmployeeByEf(@PathVariable(value = "id") Long employeeId)
			throws ResourceNotFoundException {
		HttpRequest request = HttpRequest.newBuilder()
             .uri(URI.create("http://localhost:8081/benefit/"+ employeeId))
             .method("GET", HttpRequest.BodyPublishers.noBody())
             .build();
		HttpResponse<String> response = null;
        try {
            response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {

            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Employee employee = employeeRepository.findById(employeeId)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));
		logger.info("Get all employee by ID: {},{}", employeeId, employee.getEmailId());
		ObjectMapper objectMapper = new ObjectMapper();
		EmployeeBenefit eb = new EmployeeBenefit();
		try {
			eb = objectMapper.readValue(response.body(), EmployeeBenefit.class);
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		eb.setId(employee.getId());
		eb.setFirstName(employee.getFirstName());
		return ResponseEntity.ok().body(eb);
	}

	@GetMapping("/employees/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable(value = "id") Long employeeId)
			throws ResourceNotFoundException {
		Employee employee = employeeRepository.findById(employeeId)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));
		logger.info("Get  employee by ID: {} , {}", employeeId, employee.getEmailId());
		return ResponseEntity.ok().body(employee);
	}

	@PostMapping("/employees")
	public Employee createEmployee(@Valid @RequestBody Employee employeeid) {
		return employeeService.createEmployee(employeeid);
		
	}

	@PutMapping("/employees/{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable(value = "id") Long employeeId,
			@Valid @RequestBody Employee employeeDetails) throws ResourceNotFoundException {
		Employee eu = employeeService.updatedEmployee(employeeId, employeeDetails);
		logger.info("Employee with id {} has been updated", eu.getId());
		return ResponseEntity.ok(eu);

	}

	@DeleteMapping("/employees/{id}")
	public Map<String, Boolean> deleteEmployee(@PathVariable(value = "id") Long employeeId)
			throws ResourceNotFoundException {
		logger.info("Employee with id {} has been deleted", employeeId);
		return employeeService.deleteEmployee(employeeId);
	}
}