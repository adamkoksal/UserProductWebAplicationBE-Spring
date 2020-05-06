package com.adamkoksal.WebServiceProject.Controller;

import com.adamkoksal.WebServiceProject.Entity.ID;
import com.adamkoksal.WebServiceProject.Entity.User;
import com.adamkoksal.WebServiceProject.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4201")
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.GET )
    public List<User> getAllUsers() throws SQLException {
        return userService.getAllUsers();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public User getUserById(@PathVariable("id") int id) throws SQLException {
        return userService.getUserById(id);
    }

    @RequestMapping(value = "/username={username}", method = RequestMethod.GET)
    public List<User> getUserByUsername(@PathVariable("username") String username) throws SQLException {
        return userService.getUserByUsername(username);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteUserById(@PathVariable("id") int id) throws SQLException {
        userService.deleteUserById(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public void updateUserById(@PathVariable("id") int id, @RequestBody User user) throws SQLException {
        userService.updateUserById(id, user);
    }

    @RequestMapping(method = RequestMethod.POST)
    public void createUser(@RequestBody User user) throws SQLException {
        userService.createUser(user);
    }

    @RequestMapping(value = "/approve", method = RequestMethod.POST)
    public void approveUser(@RequestBody ID id) throws SQLException {
        userService.approveUser(id.getId());
    }

}
