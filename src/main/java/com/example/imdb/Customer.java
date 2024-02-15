package com.example.imdb;

import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;

@Entity
@Table(name="customer")
public class Customer {

    @Id
    @Column(name="customer_id", unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int customerID;

//    @JsonView(Views.Public.class)
    @Column(name="first_name")
    private String firstName;

//    @JsonView(Views.Public.class)
    @Column(name="last_name")
    private String lastName;

//    @JsonView(Views.Public.class)
    @Column(name="email")
    private String email;

    @Column(name="active")
    private Integer active;

//    @JsonView(Views.Public.class)
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name="address_id", nullable = false)
    private Address address;

    public void setAddress(Address address) {
        this.address = address;
    }

    public Address getAddress() {
        return address;
    }

    public Integer getActive() {
        return active;
    }

    public void setActive(Integer active) {
        this.active = active;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
