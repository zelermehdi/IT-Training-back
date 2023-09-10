package com.fil.rouge.services;

import javax.management.relation.Role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.fil.rouge.models.AppRole;
import com.fil.rouge.models.AppUser;

import jakarta.transaction.Transactional;

import com.fil.rouge.Repository.AppRoleRepository;
import com.fil.rouge.Repository.AppUserRepository;
import lombok.AllArgsConstructor;
import lombok.Data;

@Transactional
@Service
@Data
@AllArgsConstructor
public class GestionUserDao {

	@Autowired
	private AppUserRepository userRepository;
	@Autowired
	private AppRoleRepository roleRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;

	public void insertUser(AppUser appUser) {
		// 1- Encoder le password de l'utilisateur
		// 1-1 récupération du mot de passe dans l'objet
		String pwd = appUser.getPassword();
		// 1-2 On encode le mot de passe
		String pwdCrypter = this.passwordEncoder.encode(pwd);
		// 1-3 On stocke le mot de passe encodé dans l'objet appUser
		appUser.setPassword(pwdCrypter);

		// 1-4 On enregistre l'utilisateur dans la base
		this.userRepository.save(appUser);
	}

	/**
	 * @param appRole
	 */
	public void insertRole(AppRole appRole) {
		this.roleRepository.save(appRole);
	}

	public void addRoleToUser(AppRole appRole, AppUser appUser) {
		AppRole role = this.roleRepository.getById(appRole.getIdRole());
		AppUser user = this.userRepository.getById(appUser.getIdUser());

		user.getListRoles().add(role);
	}

	public AppUser findUserByUsername(String username) {
		return this.userRepository.findUserByUsername(username);
	}
	/*
	 * public AppRole findRoleByRole(String role) { return
	 * this.roleRepository.findRoleByRole(role); }
	 */

	public void createUser(AppUser newUser) {
	    // 1. Encodez le mot de passe de l'utilisateur
	    String encodedPassword = passwordEncoder.encode(newUser.getPassword());
	    newUser.setPassword(encodedPassword);
	    
	    // 2. Enregistrez l'utilisateur dans la base de données
	    userRepository.save(newUser);
	}

	
	
}