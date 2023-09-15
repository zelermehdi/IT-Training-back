package com.fil.rouge.controllers;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwsHeader;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.fil.rouge.models.AppRole;
import com.fil.rouge.models.AppUser;
import com.fil.rouge.models.Candidat;
import com.fil.rouge.models.User;
import com.fil.rouge.services.GestionUserDao;
import com.fil.rouge.services.Userservice;

@RestController
@RequestMapping("/auth")
public class SecurityController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtEncoder jwtEncoder;
    @Autowired
    private GestionUserDao gestionUserDao;
    @Autowired
    private Userservice userservice;

    @GetMapping("/profil")
    public Authentication authentication(Authentication authentication) {
        return authentication;
    }

    @PostMapping("/add-role-user")
    public ResponseEntity<String> addRoleToUser(@RequestBody UserRole userRole) {
        try {
            this.gestionUserDao.addRoleToUser(userRole.appRole.getRole(), userRole.appUser.getUsername());
            return ResponseEntity.ok("Utilisateur créé avec succès");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur lors de la création de l'utilisateur.");
        }
    }

   

    @PostMapping("/login")
    public ResponseEntity<UserInfo> login(@RequestBody Map<String, String> loginRequest) {
        String username = loginRequest.get("username");
        String password = loginRequest.get("password");

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));

        String roles = authentication.getAuthorities().stream().map(a -> a.getAuthority()).collect(Collectors.joining(","));

        UserInfo userInfo = new UserInfo();
        userInfo.setUsername(username);
        userInfo.setRoles(roles);

        Instant instant = Instant.now();
        JwtClaimsSet jwtClaimsSet = JwtClaimsSet.builder()
                .issuedAt(instant)
                .expiresAt(instant.plus(10080, ChronoUnit.MINUTES))
                .subject(username)
                .claim("scope", roles)
                .build();

        JwtEncoderParameters jwtEncoderParameters =
                JwtEncoderParameters.from(JwsHeader.with(MacAlgorithm.HS512).build(), jwtClaimsSet);

        String jwt = jwtEncoder.encode(jwtEncoderParameters).getTokenValue();

        userInfo.setAccessToken(jwt);

        return ResponseEntity.ok(userInfo);
    }

    class UserInfo {
        private String username;
        private String roles;
        private String accessToken;

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getRoles() {
            return roles;
        }

        public void setRoles(String roles) {
            this.roles = roles;
        }

        public String getAccessToken() {
            return accessToken;
        }

        public void setAccessToken(String accessToken) {
            this.accessToken = accessToken;
        }
    }

    @PostMapping("/create-role")
    public ResponseEntity<String> createRole(@RequestBody AppRole newRole) {
        try {
            AppRole existingRole = this.gestionUserDao.findRoleByRole(newRole.getRole());

            if (existingRole != null) {
                return ResponseEntity.badRequest().body("Le Role existe déjà.");
            } else {
                this.gestionUserDao.insertRole(newRole);
                return ResponseEntity.ok("Utilisateur créé avec succès");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur lors de la création de l'utilisateur.");
        }
    }

    @PostMapping("/create-user")
    public ResponseEntity<User> createUser(@RequestBody User newUser) {
        try {
            User existingUser = userservice.findUserByUsername(newUser.getUsername());
            if (existingUser != null) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "L'utilisateur existe déjà.");
            }

            User createdUser = userservice.insertUser(newUser);

            return ResponseEntity.ok(createdUser);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erreur lors de la création de l'utilisateur.", e);
        }
    }
}

class UserRole {
    public AppRole appRole;
    public AppUser appUser;
}
