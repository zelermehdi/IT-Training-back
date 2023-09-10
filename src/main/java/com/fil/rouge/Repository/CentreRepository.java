package com.fil.rouge.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fil.rouge.models.Centre;

@Repository
public interface CentreRepository extends JpaRepository<Centre, Long> {
}