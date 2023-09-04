package com.fil.rouge.services;



import com.fil.rouge.models.Formation;
import java.util.List;
import java.util.Optional;

public interface FormationService {
    List<Formation> getAllFormations();

    Optional<Formation> getFormationById(Long id);

    Formation createFormation(Formation formation);

    Optional<Formation> updateFormation(Long id, Formation updatedFormation);

    boolean deleteFormation(Long id);
}
