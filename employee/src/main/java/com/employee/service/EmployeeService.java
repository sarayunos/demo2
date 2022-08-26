package com.employee.service;

import java.util.HashMap;
import java.util.Map;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.employee.exception.ResourceNotFoundException;
import com.employee.model.Employee;
import com.employee.repositories.EmployeeRepository;
@Service
public class EmployeeService {
	@Autowired
	private EmployeeRepository employeeRepository;

	@Transactional
	public Employee createEmployee(Employee employee) {
		
		return employeeRepository.save(employee);
	}
	
	@Transactional
	public Employee updatedEmployee(Long employeeId,Employee employeeDetails) throws ResourceNotFoundException {
		Employee employee = employeeRepository.findById(employeeId)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id : " + employeeId));
		employee.setEmailId(employeeDetails.getEmailId());
		employee.setLastName(employeeDetails.getLastName());
		employee.setFirstName(employeeDetails.getFirstName());
		final Employee updatedEmployee = employeeRepository.save(employee);
		return updatedEmployee;
		
	}
	@Transactional
	public Map<String, Boolean> deleteEmployee(Long employeeId)
			throws ResourceNotFoundException {
		Employee employee = employeeRepository.findById(employeeId)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));

		employeeRepository.delete(employee);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
		
	
}
