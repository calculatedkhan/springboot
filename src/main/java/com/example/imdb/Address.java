package com.example.imdb;

import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="address")
public class Address {

    @Id
    @Column(name="address_id", unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int addressID;

    @Column(name="address")
    private String address;

    @Column(name="district")
    private String district;

    @Column(name="postal_code")
    private int postalCode;

    @Column(name="city_id")
    private int cityID;

    @Column(name="phone")
    private long phone;

    public int getCityID() {
        return cityID;
    }

    public void setCityID(int cityID) {
        this.cityID = cityID;
    }

    public long getPhone() {
        return phone;
    }

    public void setPhone(long phone) {
        this.phone = phone;
    }

    public int getAddressID() {
        return addressID;
    }

    public void setAddressID(int addressID) {
        this.addressID = addressID;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public int getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(int postalCode) {
        this.postalCode = postalCode;
    }
}
