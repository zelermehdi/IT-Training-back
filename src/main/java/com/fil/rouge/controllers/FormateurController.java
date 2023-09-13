package com.fil.rouge.controllers;


import com.fil.rouge.models.Centre;
import com.fil.rouge.models.Formateur;
import com.fil.rouge.services.GestionUserDao;
import com.fil.rouge.services.Userservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/formateurs")
public class FormateurController {

    @Autowired
    private GestionUserDao gestionUserDao;

    @Autowired
    private Userservice userservice;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/create")
    public ResponseEntity<String> createFormateur(@RequestBody Formateur formateur) {
        try {
            String encodedPassword = passwordEncoder.encode(formateur.getPassword());
            formateur.setPassword(encodedPassword);

            userservice.insertUser(formateur);

            return ResponseEntity.ok("Formateur créé avec succès.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erreur lors de la création du formateur : " + e.getMessage());
        }
    }
}
