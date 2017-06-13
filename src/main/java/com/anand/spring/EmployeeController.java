package com.anand.spring;

import com.anand.spring.model.Employee;
import com.anand.spring.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.LinkedHashMap;
import java.util.Map;

@Controller
public class EmployeeController {
   private EmployeeService employeeService;
   
   private Map<String, String> cities = new LinkedHashMap<>();
   
   public Map<String, String> getCities() {
      return cities;
   }
   
   private EmployeeService getEmployeeService() {
      return employeeService;
   }
   
   @Autowired()
   @Qualifier(value = "employeeService")
   public void setEmployeeService(EmployeeService employeeService) {
      this.employeeService = employeeService;
   }
   
   @RequestMapping(value = {"/", "/home"})
   public String homePage(Model model) {
      model.addAttribute("employee", new Employee());
      model.addAttribute("cityList", getCitiesList());
      return "input_form";
   }
   
   @RequestMapping(value = "/register", method = RequestMethod.POST)
   public String registerEmployee(@ModelAttribute("employee") Employee employee) {
      if (employee.getId() == 0) {
         getEmployeeService().addEmployee(employee);
      } else {
         getEmployeeService().updateEmployee(employee);
      }
      return "redirect:/list";
   }
   
   @RequestMapping(value = "/list")
   public String listEmployees(ModelMap modelMap) {
      modelMap.addAttribute("employeesList", getEmployeeService().listEmployees());
      return "employees_list";
   }
   
   @RequestMapping(value = "/edit/{id}")
   public String editEmployee(@PathVariable("id") int id, Model model) {
      model.addAttribute("employee", getEmployeeService().getEmployeeById(id));
      model.addAttribute("cityList", getCitiesList());
      return "input_form";
   }
   
   @RequestMapping(value = "/delete/{id}")
   public String deleteEmployee(@PathVariable("id") int id) {
      try {
         if (getEmployeeService().deleteEmployeeById(id)) {
            return "redirect:/list";
         }
      } catch (Exception e) {
         return "redirect:/home";
      }
      return "redirect:/home";
   }
   
   public Map<String, String> getCitiesList() {
      Map<String, String> cities = getCities();
      cities.put("Not Selected", "Select your city");
      cities.put("Noida", "Noida");
      cities.put("Bengaluru", "Bengaluru");
      cities.put("Delhi", "Delhi");
      cities.put("Tokyo", "Tokyo");
      return cities;
   }
   
}