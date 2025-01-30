package com.example.property_management.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

//This annotations will ignore unknown and will not include null
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDTO {

    private Long id;
    private String ownerName;
    @NotNull(message = "Owner Email can`t be null")
    @NotEmpty(message = "Owner Email can`t be empty")
    @Size(min=1, max = 50, message = "Owner Email should be between 1 and 50 characters")
    private String ownerEmail;
    private String phone;
    @NotNull(message = "Password can`t be null")
    @NotEmpty(message = "Password can`t be empty")
    private String password;

    private String houseNumber;
    private String street;
    private String city;
    private String postalCode;
    private String country;

    public UserDTO() {
        // To create an empty UserDTO
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public @NotNull(message = "Owner Email can`t be null") @NotEmpty(message = "Owner Email can`t be empty") @Size(min = 1, max = 50, message = "Owner Email should be between 1 and 50 characters") String getOwnerEmail() {
        return ownerEmail;
    }

    public void setOwnerEmail(@NotNull(message = "Owner Email can`t be null") @NotEmpty(message = "Owner Email can`t be empty") @Size(min = 1, max = 50, message = "Owner Email should be between 1 and 50 characters") String ownerEmail) {
        this.ownerEmail = ownerEmail;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public @NotNull(message = "Password can`t be null") @NotEmpty(message = "Password can`t be empty") String getPassword() {
        return password;
    }

    public void setPassword(@NotNull(message = "Password can`t be null") @NotEmpty(message = "Password can`t be empty") String password) {
        this.password = password;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
