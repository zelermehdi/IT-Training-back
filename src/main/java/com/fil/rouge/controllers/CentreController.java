package com.fil.rouge.controllers;

import com.fil.rouge.models.Centre;
import com.fil.rouge.Repository.CentreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/centres")
public class CentreController {

    private final CentreRepository centreRepository;

    @Autowired
    public CentreController(CentreRepository centreRepository) {
        this.centreRepository = centreRepository;
    }

    @GetMapping
    public List<Centre> getAllCentres() {
        return centreRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Centre> getCentreById(@PathVariable Long id) {
        Centre centre = centreRepository.findById(id).orElse(null);
        if (centre != null) {
            return ResponseEntity.ok(centre);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Centre> createCentre(@RequestBody Centre centre) {
        Centre savedCentre = centreRepository.save(centre);
        return ResponseEntity.ok(savedCentre);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Centre> updateCentre(@PathVariable Long id, @RequestBody Centre updatedCentre) {
        Centre existingCentre = centreRepository.findById(id).orElse(null);
        if (existingCentre != null) {
            // Mettez à jour les champs du centre existant avec les valeurs du nouveau centre
            existingCentre.setNom(updatedCentre.getNom());
            existingCentre.setAdresse(updatedCentre.getAdresse());

            Centre updatedCentreEntity = centreRepository.save(existingCentre);
            return ResponseEntity.ok(updatedCentreEntity);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Centre> deleteCentre(@PathVariable Long id) {
        Centre centreToDelete = centreRepository.findById(id).orElse(null);
        if (centreToDelete != null) {
            centreRepository.deleteById(id);
            return ResponseEntity.ok(centreToDelete);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}