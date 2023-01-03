package com.techelevator.dao;

import com.fasterxml.jackson.databind.annotation.JsonAppend;
import com.techelevator.model.Property;
import com.techelevator.model.dto.NewPropertyDto;

import java.util.List;

public interface PropertyDao {

    List<Property> getAllAvailableProperties();

    List<Property> getPropertiesByLandlordId(int landlordId);

    Property getPropertyByRenterId(int renterId);

    Property getPropertyByPropertyId(int propertyId);

    Property createProperty(NewPropertyDto property);

    Property updateOccupiedProperty(Property property);

    Property updateVacantProperty(Property property);

    void deleteProperty(int propertyId);

}
