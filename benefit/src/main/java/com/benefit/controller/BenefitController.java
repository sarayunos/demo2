package com.benefit.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.benefit.exception.ResourceNotFoundException;
import com.benefit.model.BenefitModel;
import com.benefit.repositories.BenefitRepository;
import com.benefit.service.BenefitService;





@RestController
@RequestMapping
public class BenefitController {
	
	Logger logger = LoggerFactory.getLogger(BenefitController.class);
	
	@Autowired
	private BenefitRepository benefitRepository;
	
	@Autowired
	private BenefitService benefitService;

	

	@GetMapping("/benefit")
    public List<BenefitModel> getAllEmployees() {
//    	logger.info("Get all employee");
        return benefitRepository.findAll();
    }
	
    @GetMapping("/benefit/{id}")
	public Optional<BenefitModel> getEmployeeById(@PathVariable(value = "id") Long employeeId) {
		return benefitRepository.findById(employeeId);
	}
    
    @PostMapping("/benefit")
	public BenefitModel createEmployee(@Valid @RequestBody BenefitModel employeeid) {
		return benefitService.createBenefit(employeeid);
		
	}
    @DeleteMapping("/benefit/{id}")
	public Map<String, Boolean> deleteEmployee(@PathVariable(value = "id") Long employeeId)
			throws ResourceNotFoundException {
		logger.info("Employee with id {} has been deleted", employeeId);
		return benefitService.deleteBenefit(employeeId);
	}

}