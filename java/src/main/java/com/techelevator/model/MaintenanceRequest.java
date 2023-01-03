package com.techelevator.model;

import java.sql.Date;
import java.sql.Timestamp;

public class MaintenanceRequest {

    private int requestId;
    private Integer workerId;
    private int propertyId;
    private String title;
    private String description;
    private String category;
    private Date requestDate;
    private Date completedDate;

    public int getRequestId() {
        return requestId;
    }

    public void setRequestId(int requestId) {
        this.requestId = requestId;
    }

    public Integer getWorkerId() {
        return workerId;
    }

    public void setWorkerId(Integer workerId) {
        this.workerId = workerId;
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

    public Date getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(Date requestDate) {
        this.requestDate = requestDate;
    }

    public Date getCompletedDate() {
        return completedDate;
    }

    public void setCompletedDate(Date completedDate) {
        this.completedDate = completedDate;
    }

    public boolean isCompleted() {
        return this.getCompletedDate() != null;
    }

    @Override
    public String toString() {
        return "MaintenanceRequest{" +
                "requestId=" + requestId +
                ", workerId=" + workerId +
                ", propertyId=" + propertyId +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", category='" + category + '\'' +
                ", requestDate=" + requestDate +
                ", completedDate=" + completedDate +
                '}';
    }
}
