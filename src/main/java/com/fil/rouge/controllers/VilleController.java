package com.fil.rouge.controllers;


import com.fil.rouge.models.Ville;
import com.fil.rouge.Repository.VilleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@RestController
@RequestMapping("/villes")
public class VilleController {

    private final VilleRepository villeRepository;

    @Autowired
    public VilleController(VilleRepository villeRepository) {
        this.villeRepository = villeRepository;
    }

    @GetMapping
    public List<Ville> getAllVilles() {
        return villeRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ville> getVilleById(@PathVariable Long id) {
        Ville ville = villeRepository.findById(id).orElse(null);
        if (ville != null) {
            return ResponseEntity.ok(ville);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Ville> createVille(@RequestBody Ville ville) {
        Ville savedVille = villeRepository.save(ville);
        return ResponseEntity.ok(savedVille);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Ville> updateVille(@PathVariable Long id, @RequestBody Ville updatedVille) {
        Ville existingVille = villeRepository.findById(id).orElse(null);
        if (existingVille != null) {
            existingVille.setNom(updatedVille.getNom());
            existingVille.setLat(updatedVille.getLat());
            existingVille.setLon(updatedVille.getLon());

            Ville updatedVilleEntity = villeRepository.save(existingVille);
            return ResponseEntity.ok(updatedVilleEntity);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Ville> deleteVille(@PathVariable Long id) {
        Ville villeToDelete = villeRepository.findById(id).orElse(null);
        if (villeToDelete != null) {
            villeRepository.deleteById(id);
            return ResponseEntity.ok(villeToDelete);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
