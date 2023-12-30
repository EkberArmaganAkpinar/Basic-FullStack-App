package com.armagan.backend.controller;

import com.armagan.backend.exception.ResourceNotFoundException;
import com.armagan.backend.model.Employee;
import com.armagan.backend.repository.EmployeeRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/")
public class EmployeeController {

    private final  EmployeeRepository employeeRepository;
    EmployeeController(EmployeeRepository employeeRepository){
        this.employeeRepository = employeeRepository;
    }

    @GetMapping("/employees")
    public List<Employee> getAllEmployees(){
        return employeeRepository.findAll();
    }

    @PostMapping("/employee")
    public Employee getEmployees(@RequestBody String id){
        return employeeRepository.findById(Long.valueOf(id)).orElseThrow(()-> new ResourceNotFoundException("Not Found Employee's Variable" + id ));
    }

    @PostMapping("/employees")
    public Employee createEmployee(@RequestBody Employee employee){
        return employeeRepository.save(employee);
    }

    @PutMapping("/employees/update")
    public void updateEmployee(@RequestBody Employee employee){
        final Employee employeeOld = employeeRepository.findById(employee.getId()).orElseThrow(()-> new ResourceNotFoundException("Not Found Employee's Variable" + employee.getId() ));
        employeeOld.setEmailId(employee.getEmailId());
        employeeOld.setLastName(employee.getLastName());
        employeeOld.setFirstName(employee.getFirstName());
        employeeRepository.save(employeeOld);
    }
    @PostMapping ("/employees/delete")
    public void deleteEmployee(@RequestBody String id){
        employeeRepository.deleteById(Long.valueOf(id));
    }
}
