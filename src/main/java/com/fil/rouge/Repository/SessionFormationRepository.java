package com.fil.rouge.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fil.rouge.models.Formation;
import com.fil.rouge.models.SessionFormation;

public interface SessionFormationRepository extends JpaRepository<SessionFormation, Long> {

	List<SessionFormation> findByFormation(Formation formation);


}