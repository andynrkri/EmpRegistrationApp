package com.anand.spring;

import com.anand.spring.model.Employee;
import com.anand.spring.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.support.ByteArrayMultipartFileEditor;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

@Controller
public class EmployeeController {
   private EmployeeService employeeService;
   private EmployeeService getEmployeeService() {
      return employeeService;
   }
   
   private Map<String, String> cities = new LinkedHashMap<>();
   private Map<String, String> getCities() {
      return cities;
   }
   //TODO Study about @InitBinder
   @InitBinder
   protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws ServletException {
      binder.registerCustomEditor(byte[].class, new ByteArrayMultipartFileEditor());
   }
   
   @Autowired()
   @Qualifier(value = "employeeService")
   public void setEmployeeService(EmployeeService employeeService) {
      this.employeeService = employeeService;
   }
   
   @RequestMapping(value = {"/", "/home"})
   public String homePage(ModelMap modelMap) {
      modelMap.addAttribute("employee", new Employee());
      modelMap.addAttribute("cityList", getCitiesList());
      return "input_form";
   }
   
   @RequestMapping(value = "/register", method = RequestMethod.POST)
   public String registerEmployee(@Valid @ModelAttribute("employee") Employee employee, BindingResult bindingResult) {
      if (bindingResult.hasErrors()) {
         return "redirect:/home";
      }
      if (employee.getId() == 0) {
         getEmployeeService().addEmployee(employee);
      } else {
         getEmployeeService().updateEmployee(employee);
      }
      // forward will send the same POST data to the list. Make sure the /list implementing method doesn't interpret this.
       // Also, in the case you want to prevent multiple POST request by client, user redirect!! I've used forward coz
       // I read it's a tad bit faster than the redirect.
      return "forward:/list";
   }
   
   @RequestMapping(value = "/list")
   public String listEmployees(ModelMap modelMap) {
      modelMap.addAttribute("employeesList", getEmployeeService().listEmployees());
      return "employees_list";
   }
   
   @RequestMapping(value = "/edit/{id}")
   public String editEmployee(@PathVariable("id") int id, ModelMap modelMap) {
      modelMap.addAttribute("employee", getEmployeeService().getEmployeeById(id));
      modelMap.addAttribute("cityList", getCitiesList());
      return "input_form";
   }
   // forward will send the same get request to list.
   @RequestMapping(value = "/delete/{id}")
   public String deleteEmployee(@PathVariable("id") int id) {
      try {
         if (getEmployeeService().deleteEmployeeById(id)) {
            return "forward:/list";
         }
      } catch (Exception e) {
         return "redirect:/home";
      }
      return "redirect:/home";
   }

   @RequestMapping("/resume/{id}")
   @ResponseBody
   public String getResume(@PathVariable("id") int id) throws IOException {
      String newFilePath = "C:\\Users\\anand\\Documents\\id"+id+"Resume"+".docx";
      byte[] bytes = getEmployeeService().getResumeById(id);
      File someFile = new File(newFilePath);
      FileOutputStream fos = new FileOutputStream(someFile);
      fos.write(bytes);
      fos.flush();
      fos.close();
      return "This resume is available at " + newFilePath;
   }

   @RequestMapping("/error")
   public String errorPage() {
      return "error_page";
   }
   
   private Map<String, String> getCitiesList() {
      Map<String, String> cities = getCities();
      cities.put("Not Selected", "Select your city");
      cities.put("Noida", "Noida");
      cities.put("Bengaluru", "Bengaluru");
      cities.put("Delhi", "Delhi");
      cities.put("Tokyo", "Tokyo");
      return cities;
   }
   
}