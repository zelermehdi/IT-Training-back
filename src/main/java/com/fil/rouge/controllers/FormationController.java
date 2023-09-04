
package com.fil.rouge.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.fil.rouge.Repository.FormationRepository;
import com.fil.rouge.models.Formation;
import com.fil.rouge.models.SessionFormation;

import java.util.List;

@RestController
@RequestMapping("/formations")
public class FormationController {
    @Autowired
    private FormationRepository formationRepository;


    @GetMapping
    public List<Formation> getAllFormations() {
        return formationRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Formation> getFormationById(@PathVariable Long id) {
        Formation formation = formationRepository.findById(id).orElse(null);

        if (formation == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(formation);
    }

    @GetMapping("/{id}/formation-avec-sessions")
    public ResponseEntity<Formation> getFormationWithSessions(@PathVariable Long id) {
        Formation formation = formationRepository.findById(id).orElse(null);

        if (formation == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(formation);
    }

    @PostMapping
    public Formation createFormation(@RequestBody Formation formation) {
        return formationRepository.save(formation);
    }

    @PutMapping("/{id}")
    public Formation updateFormation(@PathVariable Long id, @RequestBody Formation updatedFormation) {
        Formation existingFormation = formationRepository.findById(id).orElse(null);
        if (existingFormation != null) {
            existingFormation.setNom(updatedFormation.getNom());
            existingFormation.setDescription(updatedFormation.getDescription());
            existingFormation.setPrix(updatedFormation.getPrix());
            existingFormation.setDuree(updatedFormation.getDuree());
            existingFormation.setPrerequis(updatedFormation.getPrerequis());
            return formationRepository.save(existingFormation);
        } else {
            return null;
        }
    }

    @DeleteMapping("/{id}")
    public void deleteFormation(@PathVariable Long id) {
        formationRepository.deleteById(id);
    }

    @GetMapping("/{id}/sessions")
    public ResponseEntity<List<SessionFormation>> getSessionsByFormation(@PathVariable Long id) {
        Formation formation = formationRepository.findById(id).orElse(null);

        if (formation == null) {
            return ResponseEntity.notFound().build();
        }

        List<SessionFormation> sessions = formation.getSessions();
        return ResponseEntity.ok(sessions);
    }
}
