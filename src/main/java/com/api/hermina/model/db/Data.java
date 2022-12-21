package com.api.hermina.model.db;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

@Entity
@Table(name = "data")
public class Data {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Size(max = 205)
    @Column(name = "firstname", length = 205)
    private String firstname;

    @Size(max = 205)
    @Column(name = "lastname", length = 205)
    private String lastname;

    @Column(name = "dateofbirth")
    private LocalDate dateofbirth;

    @Size(max = 45)
    @Column(name = "gender", length = 45)
    private String gender;

    @Size(max = 205)
    @Column(name = "email", length = 205)
    private String email;

    @Size(max = 205)
    @Column(name = "mobileno", length = 205)
    private String mobileno;

    @Lob
    @Column(name = "address")
    private String address;

    @Size(max = 205)
    @Column(name = "city", length = 205)
    private String city;

    @Size(max = 205)
    @Column(name = "state", length = 205)
    private String state;

    @Size(max = 205)
    @Column(name = "country", length = 205)
    private String country;

    @Size(max = 205)
    @Column(name = "pin", length = 205)
    private String pin;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public LocalDate getDateofbirth() {
        return dateofbirth;
    }

    public void setDateofbirth(LocalDate dateofbirth) {
        this.dateofbirth = dateofbirth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobileno() {
        return mobileno;
    }

    public void setMobileno(String mobileno) {
        this.mobileno = mobileno;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

}