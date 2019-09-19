package com.example.demo.controller;

import com.example.demo.dao.EmployeeDAO;
import com.example.demo.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/company")
@CrossOrigin(origins = "http://localhost")
public class EmployeeController {

    @Autowired
    EmployeeDAO employeeDAO;

    /* to save an employee*/
    @PostMapping(value = "/employees", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Employee createEmployee(@Valid @RequestBody Employee emp) {
        //System.out.println(emp);
        emp.setCreatedAt(new Date());
        return employeeDAO.save(emp);
    }

    /*get all employees*/
    @GetMapping("/employees")
    public List<Employee> getAllEmployees(){
        //List<Employee> employeeList = new ArrayList<Employee>();
        //employeeList.add(new Employee("Miloni", "Web Dev", "Web Applications", new Date()));
        return employeeDAO.findAll();
        //return employeeList;
    }

    /*get employee by empid*/

    @GetMapping("/employees/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable(value="id") Long empid){

        Employee emp=employeeDAO.getOne(empid);
        System.out.println(emp);

        if(emp==null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(emp);

    }

    /*update an employee by empid*/
    @PutMapping("/employees/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable(value="id") Long empid,@Valid @RequestBody Employee empDetails){

        Employee emp=employeeDAO.getOne(empid);
        if(emp==null) {
            return ResponseEntity.notFound().build();
        }

        emp.setName(empDetails.getName());
        emp.setDesignation(empDetails.getDesignation());
        emp.setExpertise(empDetails.getExpertise());

        Employee updateEmployee=employeeDAO.save(emp);
        return ResponseEntity.ok().body(updateEmployee);

    }

    /*Delete an employee*/
    @DeleteMapping("/employees/{id}")
    public ResponseEntity<Employee> deleteEmployee(@PathVariable(value="id") Long empid){

        Employee emp=employeeDAO.getOne(empid);
        if(emp==null) {
            return ResponseEntity.notFound().build();
        }
        employeeDAO.delete(emp);

        return ResponseEntity.ok().build();


    }

//    /*Delete an employee*/
//    @RequestMapping("/js/{jsFileName}")
//    public String getJs(@PathVariable(value="jsFileName") String jsFileName){
//        //return "js/" + jsFileName + ".js";
//        return "index";
//    }
}
