package de.thoughtgang.cloud.customer;

public class Customer {

     private String id;
     private String salutation;
     private String lastName;
     private String firstName;
     private String phoneNumber;
     private String email;

     public String getId() {
          return id;
     }

     public void setId(String id) {
          this.id = id;
     }

     public String getSalutation() {
          return salutation;
     }

     public void setSalutation(String salutation) {
          this.salutation = salutation;
     }

     public String getLastName() {
          return lastName;
     }

     public void setLastName(String lastName) {
          this.lastName = lastName;
     }

     public String getFirstName() {
          return firstName;
     }

     public void setFirstName(String firstName) {
          this.firstName = firstName;
     }

     public String getPhoneNumber() {
          return phoneNumber;
     }

     public void setPhoneNumber(String phoneNumber) {
          this.phoneNumber = phoneNumber;
     }

     public String getEmail() {
          return email;
     }

     public void setEmail(String email) {
          this.email = email;
     }
}
