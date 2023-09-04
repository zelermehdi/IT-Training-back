package com.fil.rouge.controllers;

import com.fil.rouge.Repository.FormationRepository;
import com.fil.rouge.models.Formation;
import com.fil.rouge.models.SessionFormation;
import com.fil.rouge.services.SessionFormationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sessions")
public class SessionFormationController {

    private final SessionFormationService sessionFormationService;
    private final FormationRepository formationRepository;

    @Autowired
    public SessionFormationController(
            SessionFormationService sessionFormationService,
            FormationRepository formationRepository
    ) {
        this.sessionFormationService = sessionFormationService;
        this.formationRepository = formationRepository;
    }

    @GetMapping
    public List<SessionFormation> getAllSessions() {
        return sessionFormationService.getAllSessions();
    }

    @GetMapping("/{id}")
    public SessionFormation getSessionById(@PathVariable Long id) {
        return sessionFormationService.getSessionById(id);
    }

    @PostMapping
    public SessionFormation createSession(@RequestBody SessionFormation sessionFormation) {
        return sessionFormationService.createSession(sessionFormation);
    }

    @PutMapping("/{id}")
    public SessionFormation updateSession(@PathVariable Long id, @RequestBody SessionFormation sessionFormation) {
        return sessionFormationService.updateSession(id, sessionFormation);
    }

    @DeleteMapping("/{id}")
    public void deleteSession(@PathVariable Long id) {
        sessionFormationService.deleteSession(id);
    }

    @GetMapping("/formation-avec-sessions/{id}")
    public ResponseEntity<Formation> getFormationAvecSessions(@PathVariable Long id) {
        Formation formation = formationRepository.findById(id).orElse(null);

        if (formation == null) {
            return ResponseEntity.notFound().build(); 
        }

        List<SessionFormation> sessions = sessionFormationService.getSessionsByFormation(formation);

        formation.setSessions(sessions);

        return ResponseEntity.ok(formation);
    }

}
