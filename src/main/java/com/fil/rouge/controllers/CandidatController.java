package com.fil.rouge.controllers;


import com.fil.rouge.models.Candidat;
import com.fil.rouge.services.GestionUserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
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

    @PostMapping("/create")
    public ResponseEntity<String> createCandidat(@RequestBody Candidat candidat) {
        try {
            String encodedPassword = passwordEncoder.encode(candidat.getPassword());
            candidat.setPassword(encodedPassword);

            gestionUserDao.insertUser(candidat);

            return ResponseEntity.ok("Candidat créé avec succès.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erreur lors de la création du candidat : " + e.getMessage());
        }
    }
}
