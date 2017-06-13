package com.anand.spring.model;

import javax.persistence.*;

@Entity
public class Employee {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private int id;
   @Column(unique = true)
   private String userName;
   private String password;
   private String name;
   private String gender;
   private Integer salary;
   private String city;
   
   public String getUserName() {
      return userName;
   }
   
   public void setUserName(String userName) {
      this.userName = userName;
   }
   
   public String getPassword() {
      return password;
   }
   
   public void setPassword(String password) {
      this.password = password;
   }
   
   public int getId() {
      return id;
   }
   
   public void setId(int id) {
      this.id = id;
   }
   
   public String getName() {
      return name;
   }
   
   public void setName(String name) {
      this.name = name;
   }
   
   public String getGender() {
      return gender;
   }
   
   public void setGender(String gender) {
      this.gender = gender;
   }
   
   public Integer getSalary() {
      return salary;
   }
   
   public void setSalary(Integer salary) {
      this.salary = salary;
   }
   
   public String getCity() {
      return city;
   }
   
   public void setCity(String city) {
      this.city = city;
   }
   
   @Override
   public String toString() {
      return "#toString() of Employee with id " + getId() + ", name " + getName() + ", gender " + getGender()
              + ", " + "salary " + getSalary() + ", city " + getCity();
   }
}
