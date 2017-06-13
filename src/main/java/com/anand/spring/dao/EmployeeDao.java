package com.anand.spring.dao;

import com.anand.spring.model.Employee;

import java.util.List;

public interface EmployeeDao {
   void addEmployee(Employee employee);
   
   void updateEmployee(Employee employee);
   
   Employee getEmployeeById(int id);
   
   List<Employee> listEmployees();
   
   boolean deleteEmployeeById(int id);
   
   boolean checkUsername(String userName);
}
