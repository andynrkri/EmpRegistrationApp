package com.anand.spring.dao;

import com.anand.spring.model.Employee;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;

//implementation of Dao Layer
public class EmployeeDaoImpl implements EmployeeDao {
   private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeDaoImpl.class);
   private SessionFactory sessionFactory;
   
   public SessionFactory getSessionFactory() {
      return sessionFactory;
   }
   
   public void setSessionFactory(SessionFactory sessionFactory) {
      this.sessionFactory = sessionFactory;
   }
   
   //here was the error. You need to get the current open session, not to open new session.
   public Session getSession() {
      return getSessionFactory().getCurrentSession();
   }
   
   @Override
   public void addEmployee(Employee employee) {
      getSession().persist(employee);
      LOGGER.info("Employee " + employee + " added.");
   }
   
   @Override
   public void updateEmployee(Employee employee) {
      getSession().update(employee);
      LOGGER.info("Employee " + employee + " updated.");
   }
   
   @Override
   public Employee getEmployeeById(int id) {
      Employee employee = (Employee) getSession().load(Employee.class, id);
      LOGGER.info("Got Employee " + employee);
      return employee;
   }
   
   @SuppressWarnings({"unchecked", "JpaQlInspection"})
   @Override
   public List<Employee> listEmployees() {
      List<Employee> employeeList = getSession().createQuery("from Employee").list();
      for (Employee employee : employeeList) {
         LOGGER.info("Employee list " + employee);
      }
      return employeeList;
   }
   
   @Override
   public boolean deleteEmployeeById(int id) {
      Employee employee = (Employee) getSession().load(Employee.class, id);
      if (employee != null) {
         getSession().delete(employee);
         LOGGER.info("deleted " + employee);
         return true;
      } else {
         return false;
      }
      
   }
   
   @Override
   public boolean checkUsername(String userName) {
      Employee employee;
      try {
         Criteria criteria = getSession().createCriteria(Employee.class);
         employee = (Employee) criteria.add(Restrictions.eq("userName", userName)).uniqueResult();
      } catch (Exception e) {
         return false;
      }
      return employee != null;
   }

   @Override
   public byte[] getResumeById(int id) {
      Employee employee;
      try {
         Criteria criteria = getSession().createCriteria(Employee.class);
         employee = (Employee) criteria.add(Restrictions.eq("id", id)).uniqueResult();
         return employee.getFile();
      } catch (Exception e) {
         return null;
      }
   }
}
