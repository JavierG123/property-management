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
    private String ownerName;
    @Column(name = "EMAIL", nullable = false)
    private String ownerEmail;
    private Double price;
    private String address;

    public PropertyEntity(Long id, String title, String description, String ownerName, String ownerEmail, Double price, String address) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.ownerName = ownerName;
        this.ownerEmail = ownerEmail;
        this.price = price;
        this.address = address;
    }

    public PropertyEntity() {
    }

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

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getOwnerEmail() {
        return ownerEmail;
    }

    public void setOwnerEmail(String ownerEmail) {
        this.ownerEmail = ownerEmail;
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
}
