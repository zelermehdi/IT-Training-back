package com.fil.rouge.controllers;



import com.fil.rouge.models.Adresse;
import com.fil.rouge.Repository.AdresseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@PreAuthorize("hasAuthority('SCOPE_ROLE_ADMIN')")

@RequestMapping("/adresses")

public class AdresseController {

    private final AdresseRepository adresseRepository;

    @Autowired
    public AdresseController(AdresseRepository adresseRepository) {
        this.adresseRepository = adresseRepository;
    }
    

    @GetMapping


    public List<Adresse> getAllAdresses() {
        return adresseRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Adresse> getAdresseById(@PathVariable Long id) {
        Adresse adresse = adresseRepository.findById(id).orElse(null);
        if (adresse != null) {
            return ResponseEntity.ok(adresse);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public Adresse createAdresse(@RequestBody Adresse adresse) {
        return adresseRepository.save(adresse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Adresse> updateAdresse(@PathVariable Long id, @RequestBody Adresse updatedAdresse) {
        Adresse existingAdresse = adresseRepository.findById(id).orElse(null);
        if (existingAdresse != null) {
            existingAdresse.setNumero(updatedAdresse.getNumero());
            existingAdresse.setAdresse(updatedAdresse.getAdresse());
            existingAdresse.setVille(updatedAdresse.getVille());

            return ResponseEntity.ok(adresseRepository.save(existingAdresse));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public void deleteAdresse(@PathVariable Long id) {
        adresseRepository.deleteById(id);
    }
}
