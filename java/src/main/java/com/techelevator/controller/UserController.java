package com.techelevator.controller;

import com.techelevator.dao.UserDao;
import com.techelevator.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@CrossOrigin
@RestController
public class UserController {

    @Autowired
    private UserDao dao;

    private final String ROLE_WORKER = "ROLE_WORKER";
    private final String ROLE_RENTER = "ROLE_RENTER";

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/user/role")
    public String getUserRole(Principal principal) {
        String username = principal.getName();
        int id = dao.findIdByUsername(username);
        return dao.getRoleById(id);
    }


    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/landlord/renters")
    public List<User> getRentersByLandlordId(Principal principal) {
        String username = principal.getName();
        int landlordId = dao.findIdByUsername(username);
        return dao.getRentersByLandlordId(landlordId);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/workers")
    public List<User> getAllWorkers() {
        return dao.getAllWorkers();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/renters/{id}")
    public User getRenterById(@PathVariable int id) {
        return dao.getUserByIdAndRole(id, ROLE_RENTER);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/workers/{id}")
    public User getWorkerById(@PathVariable int id) {
        return dao.getUserByIdAndRole(id, ROLE_WORKER);
    }

}
