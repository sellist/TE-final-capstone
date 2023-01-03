package com.techelevator.model;

import java.util.Set;

public class Property {

    private int propertyId;
    private int landlordId;
    private Integer renterId;

    private String neighborhood;
    private String description;
    private String type;

    private Address address;
    private Attributes attributes;
    private int monthlyRent;

    private Set<String> photoPaths;

    public Property() {

    }

    public Property(int propertyId, int landlordId, Integer renterId, String neighborhood, String description,
                    String type, Address address, Attributes attributes, int monthlyRent) {
        this.propertyId = propertyId;
        this.landlordId = landlordId;
        this.renterId = renterId;
        this.neighborhood = neighborhood;
        this.description = description;
        this.type = type;
        this.address = address;
        this.attributes = attributes;
        this.monthlyRent = monthlyRent;
    }

    public int getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(int propertyId) {
        this.propertyId = propertyId;
    }

    public int getLandlordId() {
        return landlordId;
    }

    public void setLandlordId(int landlordId) {
        this.landlordId = landlordId;
    }

    public int getRenterId() {
        return renterId;
    }

    public void setRenterId(int renterId) {
        this.renterId = renterId;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Attributes getAttributes() {
        return attributes;
    }

    public void setAttributes(Attributes attributes) {
        this.attributes = attributes;
    }

    public int getMonthlyRent() {
        return monthlyRent;
    }

    public void setMonthlyRent(int monthlyRent) {
        this.monthlyRent = monthlyRent;
    }

    public Set<String> getPhotoPaths() {
        return photoPaths;
    }

    public void setPhotoPaths(Set<String> photoPaths) {
        this.photoPaths = photoPaths;
    }

    @Override
    public String toString() {
        return "Property{" +
                "propertyId=" + propertyId +
                ", landlordId=" + landlordId +
                ", renterId=" + renterId +
                ", neighborhood='" + neighborhood + '\'' +
                ", description='" + description + '\'' +
                ", type='" + type + '\'' +
                ", address=" + address +
                ", attributes=" + attributes +
                ", monthlyRent=" + monthlyRent +
                '}';
    }
}




