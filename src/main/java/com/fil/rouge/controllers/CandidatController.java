package com.fil.rouge.controllers;


import com.fil.rouge.models.Candidat;
import com.fil.rouge.models.Formateur;
import com.fil.rouge.models.User;
import com.fil.rouge.services.GestionUserDao;
import com.fil.rouge.services.Userservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/candidats")
public class CandidatController {

    @Autowired
    private GestionUserDao gestionUserDao;

    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Autowired
    private Userservice userservice;

    @PostMapping("/create")
    public ResponseEntity<User> createCandidat(@RequestBody Candidat candidat) {
      
            String encodedPassword = passwordEncoder.encode(candidat.getPassword());
            candidat.setPassword(encodedPassword);

            gestionUserDao.insertUser(candidat);

            return ResponseEntity.ok(candidat);
        
    }
    @GetMapping("/list")
    public ResponseEntity<List<Candidat>> getCandidats() {
       
            List<Candidat> Candidats = userservice.getAllCandidats();
            return ResponseEntity.ok(Candidats);
        
    }
}
