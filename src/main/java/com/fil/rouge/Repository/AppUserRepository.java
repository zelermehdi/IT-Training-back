package com.fil.rouge.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import  com.fil.rouge.models.AppUser;


@Repository
public interface AppUserRepository extends JpaRepository<AppUser, Integer>{
	
	public AppUser findUserByUsername(String username);
}
