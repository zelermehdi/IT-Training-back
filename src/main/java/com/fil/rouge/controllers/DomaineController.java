package com.fil.rouge.controllers;

import com.fil.rouge.models.Domaine;
import com.fil.rouge.Repository.DomaineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/domaines")
public class DomaineController {
    @Autowired
    private DomaineRepository domaineRepository;

    @GetMapping
    public List<Domaine> getAllDomaines() {
        return domaineRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Domaine> getDomaineById(@PathVariable Long id) {
        Optional<Domaine> domaine = domaineRepository.findById(id);

        if (domaine.isPresent()) {
            return ResponseEntity.ok(domaine.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public Domaine createDomaine(@Valid @RequestBody Domaine domaine) {
        return domaineRepository.save(domaine);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Domaine> updateDomaine(@PathVariable Long id, @RequestBody Domaine updatedDomaine) {
        Optional<Domaine> existingDomaine = domaineRepository.findById(id);

        if (existingDomaine.isPresent()) {
            Domaine domaine = existingDomaine.get();
            domaine.setNom(updatedDomaine.getNom());
            domaine.setDescription(updatedDomaine.getDescription());
            return ResponseEntity.ok(domaineRepository.save(domaine));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public void deleteDomaine(@PathVariable Long id) {
        domaineRepository.deleteById(id);
    }
    
    @GetMapping("/{id}/themes")
    public ResponseEntity<Domaine> getDomaineWithThemes(@PathVariable Long id) {
        Optional<Domaine> domaine = domaineRepository.findById(id);

        if (domaine.isPresent()) {
            Domaine domaineWithThemes = domaine.get();
            return ResponseEntity.ok(domaineWithThemes);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
