package com.fil.rouge.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fil.rouge.models.AppRole;

@Repository
public interface AppRoleRepository extends JpaRepository<AppRole, Integer>	{
	
	public AppRole findRoleByRole(String role);
}