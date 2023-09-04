package com.fil.rouge.services;

import com.fil.rouge.Repository.FormationRepository;
import com.fil.rouge.models.Formation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FormationServiceImpl implements FormationService {
    @Autowired
    private FormationRepository formationRepository;

    @Override
    public List<Formation> getAllFormations() {
        return formationRepository.findAll();
    }

    @Override
    public Optional<Formation> getFormationById(Long id) {
        return formationRepository.findById(id);
    }

    @Override
    public Formation createFormation(Formation formation) {
        return formationRepository.save(formation);
    }

    @Override
    public Optional<Formation> updateFormation(Long id, Formation updatedFormation) {
        Optional<Formation> existingFormation = formationRepository.findById(id);
        if (existingFormation.isPresent()) {
            Formation formation = existingFormation.get();
            formation.setNom(updatedFormation.getNom());
            formation.setDescription(updatedFormation.getDescription());
            formation.setPrix(updatedFormation.getPrix());
            formation.setDuree(updatedFormation.getDuree());
            formation.setPrerequis(updatedFormation.getPrerequis());
            return Optional.of(formationRepository.save(formation));
        } else {
            return Optional.empty(); 
        }
    }

    @Override
    public boolean deleteFormation(Long id) {
        Optional<Formation> existingFormation = formationRepository.findById(id);
        if (existingFormation.isPresent()) {
            formationRepository.deleteById(id);
            return true; 
        } else {
            return false; 
        }
    }
}