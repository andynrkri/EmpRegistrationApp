package com.anand.spring.service;

import com.anand.spring.dao.EmployeeDao;
import com.anand.spring.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


public class EmployeeServiceImpl implements EmployeeService {
   @Autowired
   private EmployeeDao employeeDao;
   
   public EmployeeDao getEmployeeDao() {
      return employeeDao;
   }
   
   public void setEmployeeDao(EmployeeDao employeeDao) {
      this.employeeDao = employeeDao;
   }
   
   @Override
   @Transactional
   public void addEmployee(Employee employee) {
      getEmployeeDao().addEmployee(employee);
   }
   
   @Override
   @Transactional
   public void updateEmployee(Employee employee) {
      getEmployeeDao().updateEmployee(employee);
   }
   
   @Override
   @Transactional
   public Employee getEmployeeById(int id) {
      return getEmployeeDao().getEmployeeById(id);
   }
   
   @Override
   @Transactional
   public List<Employee> listEmployees() {
      return getEmployeeDao().listEmployees();
   }
   
   @Override
   @Transactional
   public boolean deleteEmployeeById(int id) {
      return getEmployeeDao().deleteEmployeeById(id);
   }
   
   @Override
   @Transactional
   public boolean checkUsername(String userName) {
      return getEmployeeDao().checkUsername(userName);
   }

   @Override
   @Transactional
   public byte[] getResumeById(int id) {
      return getEmployeeDao().getResumeById(id);
   }
}
