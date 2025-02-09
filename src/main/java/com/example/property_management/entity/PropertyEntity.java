package com.example.property_management.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "PROPERTY_TABLE")
public class PropertyEntity {

    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "PROPERTY_TITLE", nullable = false)
    private String title;
    private String description;
    private Double price;
    private String address;
    @ManyToOne(fetch = FetchType.LAZY) // it will not fetch the user data while fetching property
    @JoinColumn(name = "USER_ID", nullable = false)
    private UserEntity userEntity;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    public PropertyEntity(Long id, String title, String description, Double price, String address, UserEntity userEntity) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.price = price;
        this.address = address;
        this.userEntity = userEntity;
    }

    public PropertyEntity() {
        // Empty constructor
    }
}
