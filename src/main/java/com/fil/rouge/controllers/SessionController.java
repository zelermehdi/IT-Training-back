package com.fil.rouge.controllers;


import com.fil.rouge.models.Session;
import com.fil.rouge.Repository.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/sessions")
public class SessionController {

    private final SessionRepository sessionRepository;

    @Autowired
    public SessionController(SessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
    }

    @GetMapping
    public List<Session> getAllSessions() {
        return sessionRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Session> getSessionById(@PathVariable Long id) {
        Optional<Session> session = sessionRepository.findById(id);
        return session.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Session> createSession(@Valid @RequestBody Session session) {
        Session savedSession = sessionRepository.save(session);
        return new ResponseEntity<>(savedSession, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Session> updateSession(@PathVariable Long id, @Valid @RequestBody Session updatedSession) {
        Optional<Session> session = sessionRepository.findById(id);
        if (session.isPresent()) {
            updatedSession.setId(id);
            updatedSession.setCandidats(sessionRepository.getById(id).getCandidats());
            Session savedSession = sessionRepository.save(updatedSession);
            return new ResponseEntity<>(savedSession, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSession(@PathVariable Long id) {
        Optional<Session> session = sessionRepository.findById(id);
        if (session.isPresent()) {
            sessionRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
