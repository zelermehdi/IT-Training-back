package com.fil.rouge.Repository;  

import org.springframework.data.jpa.repository.JpaRepository;
import com.fil.rouge.models.Formation;

public interface FormationRepository extends JpaRepository<Formation, Long> {

}
