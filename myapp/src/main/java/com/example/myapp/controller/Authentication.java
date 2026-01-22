package com.example.myapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;

import com.example.myapp.dto.LoginRequest;
import com.example.myapp.dto.SignupRequest;
import com.example.myapp.model.User;
import com.example.myapp.repo.UserRepository;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class Authentication {

    @Autowired
    UserRepository db;

    @PostMapping("/signup")
    String m(@RequestBody SignupRequest obj) {
        System.out.println("\t name :" + obj.getName());
        System.out.println("\t name :" + obj.getEmail());
        System.out.println("\t name :" + obj.getPassword());

        User u = new User();

        u.setName(obj.getName());
        u.setEmail(obj.getEmail());
        u.setPassword(obj.getPassword());

        db.save(u);

        return "signup sucess";
    }

    @PostMapping("/login")
    String loginApi(@RequestBody LoginRequest loginData) {
        System.out.println(" email : " + loginData.getEmail());
        System.out.println(" password : " + loginData.getPassword());

        User user = db.findByEmail(loginData.getEmail());

        if (user == null) {
            return "User not found";
        }

        if (!user.getPassword().equals(loginData.getPassword())) {
            return "Invalid password";
        }

        return "Login successful";
    }

    @GetMapping("/users")
    List<User> getALLUsers() {
        return db.findAll();
    }

    @PutMapping("/user/{id}")
    String updatUser(@PathVariable Long id, @RequestBody SignupRequest sd) {
        Optional<User> od = db.findById(id);
        if (od.isEmpty()) {
            return " user not found";
        }
        User user = od.get();
        user.setEmail(sd.getEmail());
        user.setPassword(sd.getPassword());
        db.save(user);
        return " updated user";
    

    }
    @DeleteMapping("/user/{id}")
    String deleteUser(@PathVariable Long id) {
        Optional<User> od = db.findById(id);
        if (od.isEmpty()) {
            return " user not found";
        }
        db.deleteById(id);
        return " deleted user";
    }   

}