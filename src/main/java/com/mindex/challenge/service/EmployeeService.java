package com.mindex.challenge.service;

import com.mindex.challenge.data.Employee;
import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.exception.NotFoundException;

public interface EmployeeService {
    Employee create(Employee employee);
    Employee read(String id) throws NotFoundException;
    Employee update(Employee employee);
    /**
     * Counts the total number of employees reporting to the employee identified by <code>employeeId</code> 
     * @param employeeId target employee
     * @return total number of employees reporting to the target employee
     * @throws NotFoundException when <code>employeeId</code> doesn't exist
     */
    ReportingStructure countReports(String employeeId) throws NotFoundException;
}
