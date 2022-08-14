package com.mindex.challenge.controller;

import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.service.CompensationService;
import com.mindex.challenge.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class EmployeeController {
    private static final Logger LOG = LoggerFactory.getLogger(EmployeeController.class);

    @Autowired
    private EmployeeService employeeService;
    
    @Autowired
    private CompensationService compensationService;

    @PostMapping("/employee")
    public Employee create(@RequestBody Employee employee) {
        LOG.debug("Received employee create request for [{}]", employee);

        return employeeService.create(employee);
    }

    @GetMapping("/employee/{id}")
    public Employee read(@PathVariable String id) {
        LOG.debug("Received employee create request for id [{}]", id);

        return employeeService.read(id);
    }

    @PutMapping("/employee/{id}")
    public Employee update(@PathVariable String id, @RequestBody Employee employee) {
        LOG.debug("Received employee create request for id [{}] and employee [{}]", id, employee);

        employee.setEmployeeId(id);
        return employeeService.update(employee);
    }
    
    @GetMapping("/employee/{id}/reports")
    public ReportingStructure getReportCount(@PathVariable String id) {
        LOG.debug("Received employee report count request for id [{}]", id);

        return employeeService.countReports(id);
    }
    
    @PostMapping("/employee/{id}/compensation")
    public Compensation create(@PathVariable String id, @RequestBody Compensation compensation) {
        LOG.debug("Received compensation create request for [{}]", compensation);
        if (null == compensation.getEmployee()) {
        	Employee employee = new Employee();
        	employee.setEmployeeId(id);
        	compensation.setEmployee(employee);
        }

        return compensationService.create(compensation);
    }
    
    @GetMapping("/employee/{id}/compensation")
    public Compensation readCompensation(@PathVariable String id) {
    	LOG.debug("Received compensation read request for employee ID [{}]", id);
    	
    	return compensationService.read(id);
    }
}
