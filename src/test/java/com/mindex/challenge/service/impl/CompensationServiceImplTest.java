package com.mindex.challenge.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.data.Employee;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CompensationServiceImplTest {

    private String compensationCreateUrl;
    private String compensationReadUrl;
    private String employeeCreateUrl;
    
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Before
    public void setup() {
    	employeeCreateUrl = "http://localhost:" + port + "/employee";
    	compensationCreateUrl = "http://localhost:" + port + "/employee/{id}/compensation";
    	compensationReadUrl = "http://localhost:" + port + "/employee/{id}/compensation";
    }

    @Test
    public void testCreateReadUpdate() {
    	// Create an employee
    	Employee testEmployee = new Employee();
        testEmployee.setFirstName("John");
        testEmployee.setLastName("Doe");
        testEmployee.setDepartment("Engineering");
        testEmployee.setPosition("Developer");
        
        Employee createdEmployee = restTemplate.postForEntity(employeeCreateUrl, testEmployee, Employee.class).getBody();
        assertNotNull(createdEmployee.getEmployeeId());

        // Apply compensation to this employee
        Compensation compensation = new Compensation();
    	compensation.setEffectiveDate(LocalDate.now());
    	compensation.setSalary(BigDecimal.valueOf(100000));
        compensation.setEmployee(createdEmployee);

        // Create the compensation record
        Compensation createdCompensation = restTemplate.postForEntity(compensationCreateUrl, compensation, Compensation.class, createdEmployee.getEmployeeId()).getBody();
        assertNotNull(createdCompensation);

        // Verify the compensation can be retrieved by employee ID
        Compensation readCompensation = restTemplate.getForEntity(compensationReadUrl, Compensation.class, createdEmployee.getEmployeeId()).getBody();
        assertNotNull("Controller returned null compensation", readCompensation);
        assertNotNull("Controller returned null employee", readCompensation.getEmployee());
        assertEquals(createdEmployee.getEmployeeId(), readCompensation.getEmployee().getEmployeeId());
        assertCompensationEquivalence(createdCompensation, readCompensation);
    }

    
    private static void assertCompensationEquivalence(Compensation expected, Compensation actual) {
        assertEquals(expected.getEmployee().getEmployeeId(), actual.getEmployee().getEmployeeId());
        assertEquals(expected.getSalary(), actual.getSalary());
        assertEquals(expected.getEffectiveDate(), actual.getEffectiveDate());
    }
}
