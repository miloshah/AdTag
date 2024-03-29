package com.example.demo.dao;

import com.example.demo.model.Employee;
import com.example.demo.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeDAO {

    @Autowired
    EmployeeRepository employeeRepository;

    /*to save an employee*/

    public Employee save(Employee emp) {
        return employeeRepository.save(emp);
    }


    /* search all employees*/

    public List<Employee> findAll(){
        return employeeRepository.findAll();
    }


    /*get an employee by id*/
    public Employee getOne(Long empid) {
        return employeeRepository.getOne(empid);
    }


    /*delete an employee*/

    public void delete(Employee emp) {
        employeeRepository.delete(emp);
    }


}
