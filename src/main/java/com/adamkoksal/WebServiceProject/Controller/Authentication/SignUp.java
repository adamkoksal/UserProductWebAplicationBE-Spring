package com.adamkoksal.WebServiceProject.Controller.Authentication;

import com.adamkoksal.WebServiceProject.Entity.User;
import com.adamkoksal.WebServiceProject.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@CrossOrigin(origins = "http://localhost:4201")
@RestController
@RequestMapping("/signup")
public class SignUp {

    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.POST)
    public void signUpUser(@RequestBody User user) throws SQLException {
        userService.signUpUser(user);
    }
}
