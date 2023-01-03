package com.techelevator.dao;

import com.techelevator.model.Address;
import com.techelevator.model.Attributes;
import com.techelevator.model.Property;
import com.techelevator.model.User;
import com.techelevator.model.dto.NewPropertyDto;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.sql.SQLException;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = TestingDatabaseConfig.class)
public class PropertyDaoTests extends BaseDaoTests{

    private JdbcPropertyDao sut;

    private final User USER_1 = new User(1, "user1", "user1", "USER");
    private final User USER_2 = new User(2, "user2", "user2", "USER");

    private Property PROP_1;
    private Property PROP_2;
    private Property PROP_3;
    private Property NULL_PROP;
    private Attributes ATTRIBUTES_1;
    private Attributes ATTRIBUTES_2;
    private Attributes ATTRIBUTES_3;
    private Address ADDRESS_1;
    private Address ADDRESS_2;
    private Address ADDRESS_3;
    private NewPropertyDto NEW_PROP_DTO;

    @Autowired
    protected DataSource dataSource;

    @Before
    public void setup() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        sut = new JdbcPropertyDao(jdbcTemplate);
        NEW_PROP_DTO = null;
        ADDRESS_1 = new Address(1,"1","1","1","11111");
        ADDRESS_2 = new Address(2,"2","2","2","2");
        ADDRESS_3 = new Address(3,"3","3","3","3");
        ATTRIBUTES_1 = new Attributes(1,1.0,1,"1",true,true,true,"1");
        ATTRIBUTES_2 = new Attributes(2,2.0,2,"2",false,false,false,"2");
        ATTRIBUTES_3 = new Attributes(3,3.0,3,"3",true,true,true,"3");
        PROP_1 = new Property(1,7,4,"1","1","1",ADDRESS_1,ATTRIBUTES_1,1);
        PROP_2 = new Property(2,8,5,"2","2","2",ADDRESS_2,ATTRIBUTES_2,2);
        PROP_3 = new Property(3,9,6,"3","3","3",ADDRESS_3,ATTRIBUTES_3,3);
        NULL_PROP = new Property(0,0,0,"","","",null,null,0);

    }

    @After
    public void rollback() throws SQLException {
        dataSource.getConnection().rollback();
    }

    @Test
    public void create_new_property_stores_in_database() {
        NEW_PROP_DTO = new NewPropertyDto(PROP_3);
        Property dbProperty = sut.createProperty(NEW_PROP_DTO);
        Assert.assertNotNull(dbProperty);
    }

    @Test
    public void returns_null_if_address_or_attributes_are_null() {
        NEW_PROP_DTO = new NewPropertyDto(PROP_1);
        NEW_PROP_DTO.setAttributes(null);
        Assert.assertNull(sut.createProperty(NEW_PROP_DTO));
        NEW_PROP_DTO = new NewPropertyDto(PROP_1);
        NEW_PROP_DTO.setAddress(null);
        Assert.assertNull(sut.createProperty(NEW_PROP_DTO));
    }

    @Test
    public void get_address_property_by_id(){
        addressChecker(ADDRESS_1,sut.getPropertyByPropertyId(1).getAddress());
        addressChecker(ADDRESS_2,sut.getPropertyByPropertyId(2).getAddress());
    }

    @Test
    public void get_attributes_by_property_id(){
        attributeChecker(ATTRIBUTES_1, sut.getPropertyByPropertyId(1).getAttributes());
        attributeChecker(ATTRIBUTES_2, sut.getPropertyByPropertyId(2).getAttributes());
    }

    @Test
    public void address_in_db_changes_on_update() {
        ADDRESS_1.setHouseNumber(5);
        ADDRESS_1.setZip("55555");
        ADDRESS_1.setState("55");
        ADDRESS_1.setCity("55");
        PROP_1.setAddress(ADDRESS_1);
        propertyChecker(PROP_1, sut.updateOccupiedProperty(PROP_1));
    }

    @Test
    public void attributes_in_db_changes_on_update() {
        ATTRIBUTES_1.setHasAC(false);
        ATTRIBUTES_1.setHeatingType("test");
        ATTRIBUTES_1.setPetsAllowed(false);
        ATTRIBUTES_1.setArea(5000);
        PROP_1.setAttributes(ATTRIBUTES_1);
        propertyChecker(PROP_1, sut.updateOccupiedProperty(PROP_1));
    }

    @Test
    public void return_null_if_renter_has_no_property() {
        Assert.assertNull(sut.getPropertyByRenterId(0));
    }

    @Test
    public void return_property_matching_renter_id() {
        propertyChecker(PROP_1, sut.getPropertyByRenterId(4));
        propertyChecker(PROP_2, sut.getPropertyByRenterId(5));
    }

    @Test
    public void return_list_of_available_properties() {
        Assert.assertEquals(2,sut.getAllAvailableProperties().size());
    }

    private void propertyChecker(Property p1, Property p2) {
        addressChecker(p1.getAddress(),p2.getAddress());
        attributeChecker(p1.getAttributes(),p2.getAttributes());
        Assert.assertEquals(p1.getDescription(), p2.getDescription());
        Assert.assertEquals(p1.getLandlordId(), p2.getLandlordId());
        Assert.assertEquals(p1.getMonthlyRent(), p2.getMonthlyRent());
        Assert.assertEquals(p1.getNeighborhood(), p2.getNeighborhood());
        Assert.assertEquals(p1.getRenterId(), p2.getRenterId());
        Assert.assertEquals(p1.getType(), p2.getType());
        Assert.assertEquals(p1.getPropertyId(), p2.getPropertyId());
    }

    private void addressChecker(Address a1, Address a2) {
        Assert.assertEquals(a1.getCity(),a2.getCity());
        Assert.assertEquals(a1.getHouseNumber(),a2.getHouseNumber());
        Assert.assertEquals(a1.getState(),a2.getState());
        Assert.assertEquals(a1.getStreet(),a2.getStreet());
        Assert.assertEquals(a1.getZip(),a2.getZip());
    }

    private void attributeChecker(Attributes atr1, Attributes atr2) {
        Assert.assertEquals(atr1.getArea(),atr2.getArea());
        Assert.assertEquals(atr1.getBedrooms(),atr2.getBedrooms());
        Assert.assertEquals(atr1.getBathrooms(),atr2.getBathrooms(),0.25);
        Assert.assertEquals(atr1.getHeatingType(),atr2.getHeatingType());
        Assert.assertEquals(atr1.getParking(),atr2.getParking());
    }
}
