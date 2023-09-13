
package com.fil.rouge.controllers;



import com.fil.rouge.models.Formation;
import com.fil.rouge.Repository.FormationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/formations")
public class FormationController {

    private final FormationRepository formationRepository;

    @Autowired
    public FormationController(FormationRepository formationRepository) {
        this.formationRepository = formationRepository;
    }

    @GetMapping
    public List<Formation> getAllFormations() {
        return formationRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Formation> getFormationById(@PathVariable Long id) {
        Optional<Formation> formation = formationRepository.findById(id);
        return formation.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Formation> createFormation(@Valid @RequestBody Formation formation) {
        Formation savedFormation = formationRepository.save(formation);
        return new ResponseEntity<>(savedFormation, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Formation> updateFormation(@PathVariable Long id, @Valid @RequestBody Formation updatedFormation) {
        Optional<Formation> formation = formationRepository.findById(id);
        if (formation.isPresent()) {
            updatedFormation.setId(id);
            Formation savedFormation = formationRepository.save(updatedFormation);
            return new ResponseEntity<>(savedFormation, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFormation(@PathVariable Long id) {
        Optional<Formation> formation = formationRepository.findById(id);
        if (formation.isPresent()) {
            formationRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
