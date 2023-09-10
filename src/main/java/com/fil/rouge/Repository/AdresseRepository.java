package com.fil.rouge.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.fil.rouge.models.Adresse;
@Repository
public interface AdresseRepository extends JpaRepository<Adresse, Long> {
}
