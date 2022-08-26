package com.employee.model;

public class EmployeeBenefit {
	
		private long id;
	    private String firstName;
	    private Double optical;
	    private Double gym;
	    private Double medical;
	    
		public long getId() {
			return id;
		}
		public void setId(long id) {
			this.id = id;
		}
		public Double getMedical() {
			return medical;
		}
		public void setMedical(Double medical) {
			this.medical = medical;
		}
		public String getFirstName() {
			return firstName;
		}
		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}
		public Double getOptical() {
			return optical;
		}
		public void setOptical(Double optical) {
			this.optical = optical;
		}
		public Double getGym() {
			return gym;
		}
		public void setGym(Double gym) {
			this.gym = gym;
		}
		
	   
}
