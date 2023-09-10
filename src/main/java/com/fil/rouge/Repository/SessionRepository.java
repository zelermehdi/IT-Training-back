package com.fil.rouge.Repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fil.rouge.models.Formation;
import com.fil.rouge.models.Session;

@Repository
public interface SessionRepository extends JpaRepository<Session, Long> {
	List<Session> findByFormation(Formation formation);

}
