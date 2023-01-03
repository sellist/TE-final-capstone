package com.techelevator.controller;

import com.techelevator.dao.PropertyDao;
import com.techelevator.dao.UserDao;
import com.techelevator.model.Property;
import com.techelevator.model.dto.NewPropertyDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@CrossOrigin
@RestController
public class PropertyController {

    @Autowired
    private PropertyDao dao;

    @Autowired
    private UserDao userDao;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/properties")
    public List<Property> getAllAvailableProperties() {
        return dao.getAllAvailableProperties();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/landlord/properties")
    public List<Property> getPropertiesByLandlordId(Principal principal) {
        String username = principal.getName();
        int landlordID = userDao.findIdByUsername(username);
        return dao.getPropertiesByLandlordId(landlordID);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/renter/properties")
    public Property getPropertyByRenterId(Principal principal) {
        String username = principal.getName();
        int renterID = userDao.findIdByUsername(username);
        return dao.getPropertyByRenterId(renterID);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("properties/{id}")
    public Property getPropertyByPropertyId(@PathVariable int id) {
        return dao.getPropertyByPropertyId(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/properties")
    public int createProperty(@RequestBody NewPropertyDto property, Principal principal) {
        String username = principal.getName();
        property.setLandlordId(userDao.findIdByUsername(username));
        return dao.createProperty(property).getPropertyId();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PutMapping("/properties/{id}/update")
    public int updateProperty(@PathVariable int id, @RequestBody Property property) {
        if (property.getRenterId() == 0) {
            return dao.updateVacantProperty(property).getPropertyId();
        }
        return dao.updateOccupiedProperty(property).getPropertyId();
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/properties/{id}")
    public void delete(@PathVariable int id) {
        dao.deleteProperty(id);
    }

}


