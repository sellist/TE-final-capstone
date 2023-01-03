package com.techelevator.controller;

import com.sun.tools.javac.Main;
import com.techelevator.dao.MaintenanceDao;
import com.techelevator.dao.UserDao;
import com.techelevator.model.MaintenanceRequest;
import com.techelevator.model.Property;
import com.techelevator.model.dto.MaintenanceRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

// @PreAuthorize("isAuthenticated()")
@CrossOrigin
@RestController
@RequestMapping(path = "/maintenance/")

public class MaintenanceController {

    @Autowired
    private MaintenanceDao dao;
    @Autowired
    private UserDao userDao;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("{requestId}")
    public MaintenanceRequest getRequestById(@PathVariable int requestId) {
        return dao.getRequestById(requestId);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("property/{propertyId}")
    public List<MaintenanceRequest> getRequestsByPropertyId(@PathVariable int propertyId) {
        return dao.getRequestsByPropertyId(propertyId);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("worker/{workerId}")
    public List<MaintenanceRequest> getRequestsByWorkerId(@PathVariable int workerId) {
        return dao.getRequestsByWorkerId(workerId);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("landlord/{landlordId}")
    public List<MaintenanceRequest> getRequestsByLandlordId(@PathVariable int landlordId) {
        return dao.getRequestsByLandlordId(landlordId);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/")
    public MaintenanceRequest createMaintenanceRequest(@RequestBody MaintenanceRequestDto requestDto) {
        return dao.createMaintenanceRequest(requestDto);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("{id}/update")
    public void updateMaintenanceRequest(@PathVariable int id, @RequestBody MaintenanceRequest maintenanceRequest) {
        dao.updateMaintenanceRequest(id, maintenanceRequest);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("{id}")
    public void deleteMaintenanceRequest(@PathVariable int id) {
        dao.deleteMaintenanceRequest(id);
    }


}
