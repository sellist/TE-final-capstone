package com.techelevator.model.dto;

import com.techelevator.model.Address;
import com.techelevator.model.Attributes;
import com.techelevator.model.Property;

public class NewPropertyDto {

    private int landlordId;
    private Integer renterId;
    private String neighborhood;
    private String description;
    private String type;
    private Address address;
    private Attributes attributes;
    private int monthlyRent;

    public NewPropertyDto() {}

    public NewPropertyDto(int landlordId, String neighborhood, String description,
                          String type, Address address, Attributes attributes, int monthlyRent) {
        this.landlordId = landlordId;
        this.renterId = null;
        this.neighborhood = neighborhood;
        this.description = description;
        this.type = type;
        this.address = address;
        this.attributes = attributes;
        this.monthlyRent = monthlyRent;
    }

    // for testing
    public NewPropertyDto(Property property) {
        this.landlordId = property.getLandlordId();
        this.renterId = null;
        this.neighborhood = property.getNeighborhood();
        this.description = property.getDescription();
        this.type = property.getType();
        this.address = property.getAddress();
        this.attributes = property.getAttributes();
        this.monthlyRent = property.getMonthlyRent();
    }

    public int getLandlordId() {
        return landlordId;
    }

    public void setLandlordId(int landlordId) {
        this.landlordId = landlordId;
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

    public Integer getRenterId() {
        return renterId;
    }

    public void setRenterId(Integer renterId) {
        this.renterId = renterId;
    }

    public void setMonthlyRent(int monthlyRent) {
        this.monthlyRent = monthlyRent;
    }
}
