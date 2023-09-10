package com.fil.rouge.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fil.rouge.models.Ville;

@Repository
public interface VilleRepository extends JpaRepository<Ville, Long> {
}