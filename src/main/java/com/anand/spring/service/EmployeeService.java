package com.anand.spring.service;

import com.anand.spring.model.Employee;

import java.util.List;

public interface EmployeeService {
   void addEmployee(Employee employee);
   
   void updateEmployee(Employee employee);
   
   Employee getEmployeeById(int id);
   
   List<Employee> listEmployees();
   
   boolean deleteEmployeeById(int id);
   
   boolean checkUsername(String userName);
   
   byte[] getResumeById(int id);
}
