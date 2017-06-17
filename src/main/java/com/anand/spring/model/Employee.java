package com.anand.spring.model;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Employee {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private int id;
   @Column(unique = true)
   @NotEmpty
   private String userName;
   @NotEmpty
   private String password;
   @NotEmpty
   private String name;
   @NotEmpty
   private String gender;
   @NotNull
   private Integer salary;
   //salary is being taken from a select tag and hence will never be empty
   private String city;
   @NotEmpty @Lob
   private byte[] file;

   public byte[] getFile() {
      return file;
   }

   public void setFile(byte[] file) {
      this.file = file;
   }

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
