package com.benefit.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.benefit.model.BenefitModel;


@Repository
public interface BenefitRepository extends JpaRepository<BenefitModel, Long> {


	
	
}


