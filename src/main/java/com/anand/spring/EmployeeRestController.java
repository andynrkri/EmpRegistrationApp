package com.anand.spring;

import com.anand.spring.model.Employee;
import com.anand.spring.service.EmployeeService;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/rest")
public class EmployeeRestController {
   private EmployeeService employeeService;
   
   public EmployeeService getEmployeeService() {
      return employeeService;
   }
   
   @Autowired()
   @Qualifier(value = "employeeService")
   public void setEmployeeService(EmployeeService employeeService) {
      this.employeeService = employeeService;
   }
   
   @RequestMapping("/queryusername")
   public String checkUsername(@RequestParam(value = "userName", defaultValue = "didnotget") String userName) {
      if (userName.equals("didnotget")) {
         return userName;
      }
      else if (getEmployeeService().checkUsername(userName)) {
         return "Yes";
      }
      else {
         return "No";
      }
   }

   @RequestMapping("/getuserlist")
   public List<Employee> testJsonAnnotation() {
      return getEmployeeService().listEmployees();
   }
}
