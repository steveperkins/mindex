package com.mindex.challenge.data;

/**
 * Captures an {@link Employee}'s total number of reports  
 *
 */
public class ReportingStructure {
    private Employee employee;
    private int numberOfReports;
    
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	public int getNumberOfReports() {
		return numberOfReports;
	}
	public void setNumberOfReports(int numberOfReports) {
		this.numberOfReports = numberOfReports;
	}
    
}
