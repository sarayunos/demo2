package com.benefit.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "employeebenefit")

public class BenefitModel {
	

    private long id;
    private Double optical;
    private Double gym;
    private Double medical;
	
    
    public BenefitModel() {
    	  
    }
 
    public BenefitModel(Double optical, Double gym, Double medical) {
         this.optical = optical;
         this.gym = gym;
         this.medical = medical;
    }
 
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
        public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
 
    @Column(name = "optical")
    public Double getOptical() {
        return optical;
    }
    public void setOptical(Double optical) {
        this.optical = optical;
    }
 
    @Column(name = "gym")
    public Double getGym() {
        return optical;
    }
    public void setGym(Double gym) {
        this.gym = gym;
    }
 
    @Column(name = "medical")
    public Double getMedical() {
        return medical;
    }
    public void setMedical(Double medical) {
        this.medical = medical;
    }

    @Override
    public String toString() {
        return "Employee Benefit [id=" + id + ", Optical=" + optical + ", Gym=" + gym + ", Medical=" + medical
       + "]";
    }

}
