package com.mindex.challenge.service;

import com.mindex.challenge.data.Employee;
import com.mindex.challenge.data.ReportingStructure;

public interface EmployeeService {
    Employee create(Employee employee);
    Employee read(String id);
    Employee update(Employee employee);
    /**
     * Counts the total number of employees reporting to the employee identified by <code>employeeId</code> 
     * @param employeeId target employee
     * @return total number of employees reporting to the target employee
     */
    ReportingStructure countReports(String employeeId);
}
