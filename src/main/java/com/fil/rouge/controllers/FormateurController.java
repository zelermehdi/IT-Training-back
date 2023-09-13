package com.fil.rouge.controllers;

import com.fil.rouge.models.Formateur;
import com.fil.rouge.models.User;
import com.fil.rouge.services.Userservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/formateurs")
public class FormateurController {

    @Autowired
    private Userservice userservice;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/create")
    public ResponseEntity<User> createFormateur(@RequestBody Formateur formateur) {

        String encodedPassword = passwordEncoder.encode(formateur.getPassword());
        formateur.setPassword(encodedPassword);

        User createdFormateur = userservice.insertUser(formateur);
        return new ResponseEntity<>(createdFormateur, HttpStatus.CREATED);

    }
    @GetMapping("/list")
    public ResponseEntity<List<Formateur>> getFormateurs() {
       
            List<Formateur> formateurs = userservice.getAllFormateurs();
            return ResponseEntity.ok(formateurs);
        
    }
   
}
