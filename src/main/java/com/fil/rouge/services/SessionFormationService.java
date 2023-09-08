package com.fil.rouge.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fil.rouge.Repository.SessionFormationRepository;
import com.fil.rouge.models.Formation;
import com.fil.rouge.models.SessionFormation;

import java.util.List;
import java.util.Optional;

@Service
public class SessionFormationService {

	private final SessionFormationRepository sessionFormationRepository;

	@Autowired
	public SessionFormationService(SessionFormationRepository sessionFormationRepository) {
		this.sessionFormationRepository = sessionFormationRepository;
	}

	public List<SessionFormation> getAllSessions() {
		return sessionFormationRepository.findAll();
	}

	public SessionFormation getSessionById(Long id) {
		Optional<SessionFormation> optionalSession = sessionFormationRepository.findById(id);
		return optionalSession.orElse(null);
	}

	public SessionFormation createSession(SessionFormation sessionFormation) {
		return sessionFormationRepository.save(sessionFormation);
	}

	public SessionFormation updateSession(Long id, SessionFormation sessionFormation) {
		if (sessionFormationRepository.existsById(id)) {
			sessionFormation.setSessionFormationID(id);
			return sessionFormationRepository.save(sessionFormation);
		}
		return null;
	}

	public void deleteSession(Long id) {
		sessionFormationRepository.deleteById(id);
	}

	public List<SessionFormation> getSessionsByFormation(Formation formation) {
		return sessionFormationRepository.findByFormation(formation);
	}
}
