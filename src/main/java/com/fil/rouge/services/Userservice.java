package com.fil.rouge.services;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.fil.rouge.models.AppRole;
import com.fil.rouge.models.Candidat;
import com.fil.rouge.models.Formateur;
import com.fil.rouge.models.User;
import jakarta.transaction.Transactional;

import com.fil.rouge.Repository.CandidatRepository;
import com.fil.rouge.Repository.FormateurRepository;
import com.fil.rouge.Repository.UserRepository;

@Transactional
@Service
public class Userservice {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Autowired
    private FormateurRepository formateurRepository;
    
    @Autowired
    private CandidatRepository candidatRepository;

    public User insertUser(User newUser) {
        String pwd = newUser.getPassword();
        String pwdCrypter = this.passwordEncoder.encode(pwd);
        newUser.setPassword(pwdCrypter);

        return this.userRepository.save(newUser);
    }

    public void insertRole(AppRole appRole) {
    }

    public void addRoleToUser(AppRole appRole, User user) {
    }

    public User findUserByUsername(String username) {
        return this.userRepository.findByUsername(username);
    }

    public void createUser(User newUser) {
        String encodedPassword = passwordEncoder.encode(newUser.getPassword());
        newUser.setPassword(encodedPassword);

        userRepository.save(newUser);
    }

   

    public List<Formateur> getAllFormateurs() {
        return formateurRepository.findAll();
    }
    public List<Candidat> getAllCandidats() {
        return candidatRepository.findAll();
    }
}
