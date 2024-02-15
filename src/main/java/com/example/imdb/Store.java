package com.example.imdb;

import jakarta.persistence.*;

@Entity
@Table(name="store")
public class Store {

    @Id
    @Column(name="store_id", unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int storeID;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name="manager_staff_id", nullable = false)
    private Staff managerStaff;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name="address_id", nullable = false)
    private Address address;

    public int getStoreID() {
        return storeID;
    }

    public void setStoreID(int storeID) {
        this.storeID = storeID;
    }

    public Address getAddress() {
        return address;
    }

    public Staff getManagerStaff() {
        return managerStaff;
    }

    public void setManagerStaff(Staff managerStaff) {
        this.managerStaff = managerStaff;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
