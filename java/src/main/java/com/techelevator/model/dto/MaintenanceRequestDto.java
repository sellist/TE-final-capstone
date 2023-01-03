package com.techelevator.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MaintenanceRequestDto {

    @JsonProperty("propertyId")
    private int propertyId;
    @JsonProperty("title")
    private String title;
    @JsonProperty("description")
    private String description;
    @JsonProperty("category")
    private String category;

    public MaintenanceRequestDto(int propertyId, String title, String description, String category) {
        this.propertyId = propertyId;
        this.title = title;
        this.description = description;
        this.category = category;
    }

    public MaintenanceRequestDto() {
    }

    public int getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(int propertyId) {
        this.propertyId = propertyId;
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
