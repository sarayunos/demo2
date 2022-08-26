package com.benefit.service;

import java.util.HashMap;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.benefit.exception.ResourceNotFoundException;
import com.benefit.model.BenefitModel;
import com.benefit.repositories.BenefitRepository;


@Service
public class BenefitService {
	@Autowired
	private BenefitRepository benefitRepository;

	@Transactional
	public BenefitModel createBenefit(BenefitModel benefit) {
		return benefitRepository.save(benefit);
	}
	
	@Transactional
	public Map<String, Boolean> deleteBenefit(Long employeeId)
			throws ResourceNotFoundException {
		BenefitModel benefit = benefitRepository.findById(employeeId)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));
		benefitRepository.delete(benefit);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
		
}