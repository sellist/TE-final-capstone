package com.techelevator.dao;

import com.fasterxml.jackson.databind.annotation.JsonAppend;
import com.techelevator.model.Address;
import com.techelevator.model.Attributes;
import com.techelevator.model.Property;
import com.techelevator.model.dto.NewPropertyDto;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import javax.sql.RowSet;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class JdbcPropertyDao implements PropertyDao {

    private final JdbcTemplate jdbcTemplate;

    public JdbcPropertyDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Property> getAllAvailableProperties() {
        List<Property> properties = new ArrayList<>();

        String sql = "select * from property where renter_id IS null;";
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql);
        System.out.println(rowSet);
        while (rowSet.next()) {
            properties.add(mapRowToProperty(rowSet));
        }
        return properties;
    }

    @Override
    public List<Property> getPropertiesByLandlordId(int landlordId) {
        List<Property> properties = new ArrayList<>();

        String sql = "select * from property where landlord_id = ?;";
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, landlordId);

        while (rowSet.next()) {
            properties.add(mapRowToProperty(rowSet));
        }
        return properties;
    }

    @Override
    public Property getPropertyByRenterId(int renterId) {
        String sql = "select * from property where renter_id = ?;";
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, renterId);

        if (rowSet.next()) {
            return mapRowToProperty(rowSet);
        }
        return null;
    }

    @Override
    public Property getPropertyByPropertyId(int propertyId) {

        String sql = "select * from property where property_id = ?;";
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, propertyId);

        if (rowSet.next()) {
            return mapRowToProperty(rowSet);
        }
        return null;
    }

    @Override
    public Property createProperty(NewPropertyDto property) {
        if (property.getAddress() == null || property.getAttributes() == null) {
            return null;
        }

        String sql = "insert into property (landlord_id, renter_id, description, neighborhood, house_number, street, " +
                " city, state, zipcode, monthly_rent, type, bedrooms, bathrooms, " +
                " area, heating_type, has_ac, is_smoking, are_pets_allowed, parking) " +
                " values (?,?,?,?,?, ?,?,?,?, ?,?,?,?,?, ?,?,?,?,?) returning property_id;";
        Integer newId = jdbcTemplate.queryForObject(sql, Integer.class,

                property.getLandlordId(),
                property.getRenterId(),
                property.getDescription(),
                property.getNeighborhood(),
                property.getAddress().getHouseNumber(),
                property.getAddress().getStreet(),
                property.getAddress().getCity(),
                property.getAddress().getState(),
                property.getAddress().getZip(),
                property.getMonthlyRent(),
                property.getType(),
                property.getAttributes().getBedrooms(),
                property.getAttributes().getBathrooms(),
                property.getAttributes().getArea(),
                property.getAttributes().getHeatingType(),
                property.getAttributes().isHasAC(),
                property.getAttributes().isSmoking(),
                property.getAttributes().isPetsAllowed(),
                property.getAttributes().getParking());


        if (newId != null) {
            return getPropertyByPropertyId(newId);
        }
        return null;
    }

    @Override
    public Property updateOccupiedProperty(Property property) {

        String sql =
                "UPDATE property " +
                "SET landlord_id = ?, " +
                "renter_id = ?, " +
                "description = ?, " +
                "neighborhood = ?, " +
                "house_number = ?, " +
                "street = ?, " +
                "city = ?, " +
                "state = ?, " +
                "zipcode = ?, " +
                "monthly_rent = ?, " +
                "type = ?, " +
                "bedrooms = ?, " +
                "bathrooms = ?, " +
                "area = ?, " +
                "heating_type = ?, " +
                "has_ac = ?, " +
                "is_smoking = ?, " +
                "are_pets_allowed = ?, " +
                "parking = ? " +
                "WHERE property_id = ?;";

        jdbcTemplate.update(sql,
                property.getLandlordId(),
                property.getRenterId(),
                property.getDescription(),
                property.getNeighborhood(),
                property.getAddress().getHouseNumber(),
                property.getAddress().getStreet(),
                property.getAddress().getCity(),
                property.getAddress().getState(),
                property.getAddress().getZip(),
                property.getMonthlyRent(),
                property.getType(),
                property.getAttributes().getBedrooms(),
                property.getAttributes().getBathrooms(),
                property.getAttributes().getArea(),
                property.getAttributes().getHeatingType(),
                property.getAttributes().isHasAC(),
                property.getAttributes().isSmoking(),
                property.getAttributes().isPetsAllowed(),
                property.getAttributes().getParking(),
                property.getPropertyId());

        return getPropertyByPropertyId(property.getPropertyId());
    }

    @Override
    public Property updateVacantProperty(Property property) {

        String sql =
                "UPDATE property " +
                        "SET landlord_id = ?, " +

                        "description = ?, " +
                        "neighborhood = ?, " +
                        "house_number = ?, " +
                        "street = ?, " +
                        "city = ?, " +
                        "state = ?, " +
                        "zipcode = ?, " +
                        "monthly_rent = ?, " +
                        "type = ?, " +
                        "bedrooms = ?, " +
                        "bathrooms = ?, " +
                        "area = ?, " +
                        "heating_type = ?, " +
                        "has_ac = ?, " +
                        "is_smoking = ?, " +
                        "are_pets_allowed = ?, " +
                        "parking = ? " +
                        "WHERE property_id = ?;";

        jdbcTemplate.update(sql,
                property.getLandlordId(),

                property.getDescription(),
                property.getNeighborhood(),
                property.getAddress().getHouseNumber(),
                property.getAddress().getStreet(),
                property.getAddress().getCity(),
                property.getAddress().getState(),
                property.getAddress().getZip(),
                property.getMonthlyRent(),
                property.getType(),
                property.getAttributes().getBedrooms(),
                property.getAttributes().getBathrooms(),
                property.getAttributes().getArea(),
                property.getAttributes().getHeatingType(),
                property.getAttributes().isHasAC(),
                property.getAttributes().isSmoking(),
                property.getAttributes().isPetsAllowed(),
                property.getAttributes().getParking(),
                property.getPropertyId());

        return getPropertyByPropertyId(property.getPropertyId());
    }

    @Override
    public void deleteProperty(int propertyId) {

        String sql = "delete from property where property_id = ?;";
        jdbcTemplate.update(sql, propertyId);
    }

    public Property mapRowToProperty (SqlRowSet rowSet) {
        Property property = new Property();
        Address address = new Address();
        Attributes attributes = new Attributes();

        property.setPropertyId(rowSet.getInt("property_id"));
        property.setLandlordId(rowSet.getInt("landlord_id"));
        property.setRenterId(rowSet.getInt("renter_id"));
        property.setDescription(rowSet.getString("description"));
        property.setNeighborhood(rowSet.getString("neighborhood"));
        property.setType(rowSet.getString("type"));
        property.setMonthlyRent(rowSet.getInt("monthly_rent"));

        address.setHouseNumber(rowSet.getInt("house_number"));
        address.setStreet(rowSet.getString("street"));
        address.setCity(rowSet.getString("city"));
        address.setState(rowSet.getString("state"));
        address.setZip(rowSet.getString("zipcode"));

        attributes.setBedrooms(rowSet.getInt("bedrooms"));
        attributes.setBathrooms(rowSet.getDouble("bathrooms"));
        attributes.setArea(rowSet.getInt("area"));
        attributes.setHeatingType(rowSet.getString("heating_type"));
        attributes.setHasAC(rowSet.getBoolean("has_ac"));
        attributes.setSmoking(rowSet.getBoolean("is_smoking"));
        attributes.setPetsAllowed(rowSet.getBoolean("are_pets_allowed"));
        attributes.setParking(rowSet.getString("parking"));

        property.setPhotoPaths(getPhotoPaths(property.getPropertyId()));

        property.setAddress(address);
        property.setAttributes(attributes);

        return property;
    }

    private Set<String> getPhotoPaths(int propertyId) {
        Set<String> photoPaths = new HashSet<>();
        String sql = "select photo_path from photo join photo_property " +
                "using (photo_id) where property_id = ?;";

        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, propertyId);

        while(rowSet.next()) {
            photoPaths.add(rowSet.getString("photo_path"));
        }
        return photoPaths;
    }

}
