package com.fil.rouge.controllers;


import com.fil.rouge.models.Candidat;
import com.fil.rouge.models.Formateur;
import com.fil.rouge.models.User;
import com.fil.rouge.services.GestionUserDao;
import com.fil.rouge.services.Userservice;

import java.util.List;
import java.util.Map;

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
    private Userservice userservice;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/create")
    public ResponseEntity<User> createCandidat(@RequestBody Candidat candidat) {
        String encodedPassword = passwordEncoder.encode(candidat.getPassword());
        candidat.setPassword(encodedPassword);

        User savedCandidat = userservice.insertUser(candidat);

        return ResponseEntity.ok(savedCandidat);
    }

    @GetMapping("/list")
    public ResponseEntity<List<Candidat>> getCandidats() {
        List<Candidat> candidats = userservice.getAllCandidats();
        return ResponseEntity.ok(candidats);
    }

    @PostMapping("/convert")
    public ResponseEntity<Candidat> convertUserToCandidat(@RequestBody Candidat user) {
        Candidat candidat = userservice.convertToCandidat(user);
        return ResponseEntity.ok(candidat);
    }
    
    @PostMapping("/validate")
    public ResponseEntity<String> validateCandidat(@RequestBody Map<String, String> requestBody) {
        String username = requestBody.get("username");

        if (username == null) {
            return ResponseEntity.badRequest().body("Le champ 'username' est manquant dans le corps de la requête.");
        }

        User existingUser = userservice.findUserByUsername(username);
        if (existingUser == null || !(existingUser instanceof Candidat)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Candidat non trouvé.");
        }

        Candidat candidat = (Candidat) existingUser;
        candidat.setValidate(true);
        userservice.insertUser(candidat);

        return ResponseEntity.ok("Le candidat a été validé avec succès.");
    }

    @PostMapping("/invalidate")
    public ResponseEntity<String> invalidateCandidat(@RequestBody Map<String, String> requestBody) {
        String username = requestBody.get("username");

        if (username == null) {
            return ResponseEntity.badRequest().body("Le champ 'username' est manquant dans le corps de la requête.");
        }

        User existingUser = userservice.findUserByUsername(username);
        if (existingUser == null || !(existingUser instanceof Candidat)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Candidat non trouvé.");
        }

        Candidat candidat = (Candidat) existingUser;
        candidat.setValidate(false);
        userservice.insertUser(candidat);

        return ResponseEntity.ok("Le candidat a été invalidé avec succès.");
    }


}
