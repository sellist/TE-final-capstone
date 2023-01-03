package com.techelevator.model;

public class Attributes {

    private int bedrooms;
    private double bathrooms;
    private int area;
    private String heatingType;
    private boolean hasAC;
    private boolean isSmoking;
    private boolean isPetsAllowed;
    private String parking;

    public Attributes(int bedrooms, double bathrooms, int area, String heatingType,
                      boolean hasAC, boolean isSmoking, boolean isPetsAllowed, String parking) {
        this.bedrooms = bedrooms;
        this.bathrooms = bathrooms;
        this.area = area;
        this.heatingType = heatingType;
        this.hasAC = hasAC;
        this.isSmoking = isSmoking;
        this.isPetsAllowed = isPetsAllowed;
        this.parking = parking;
    }

    public Attributes() {

    }

    public int getBedrooms() {
        return bedrooms;
    }

    public void setBedrooms(int bedrooms) {
        this.bedrooms = bedrooms;
    }

    public double getBathrooms() {
        return bathrooms;
    }

    public void setBathrooms(double bathrooms) {
        this.bathrooms = bathrooms;
    }

    public int getArea() {
        return area;
    }

    public void setArea(int area) {
        this.area = area;
    }

    public String getHeatingType() {
        return heatingType;
    }

    public void setHeatingType(String heatingType) {
        this.heatingType = heatingType;
    }

    public boolean isHasAC() {
        return hasAC;
    }

    public void setHasAC(boolean hasAC) {
        this.hasAC = hasAC;
    }

    public boolean isSmoking() {
        return isSmoking;
    }

    public void setSmoking(boolean smoking) {
        isSmoking = smoking;
    }

    public boolean isPetsAllowed() {
        return isPetsAllowed;
    }

    public void setPetsAllowed(boolean petsAllowed) {
        isPetsAllowed = petsAllowed;
    }

    public String getParking() {
        return parking;
    }

    public void setParking(String parking) {
        this.parking = parking;
    }

    @Override
    public String toString() {
        return "Attributes{" +
                "bedrooms=" + bedrooms +
                ", bathrooms=" + bathrooms +
                ", area=" + area +
                ", heatingType='" + heatingType + '\'' +
                ", hasAC=" + hasAC +
                ", isSmoking=" + isSmoking +
                ", isPetsAllowed=" + isPetsAllowed +
                ", parking='" + parking + '\'' +
                '}';
    }
}
