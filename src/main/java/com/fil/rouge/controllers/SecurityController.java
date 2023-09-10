package com.fil.rouge.controllers;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwsHeader;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
	
	@PostMapping("/login")
	public Map<String, String> login(String username,String password){
	//1-	on authentifie un user	
		Authentication authentication =	authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
	//2-	on crée le jeton
		Instant instant = Instant.now();
		
	//3-	on récupère les rôle
		String scope =authentication.getAuthorities().stream().map(a->a.getAuthority()).collect(Collectors.joining(" "));
	//4-	
		JwtClaimsSet jwtClaimsSet = JwtClaimsSet.builder()
									.issuedAt(instant)
									.expiresAt(instant.plus(10,ChronoUnit.MINUTES))
									.subject(username)
									.claim("scope", scope)
									.build();
		//5- Création du tokem		
		JwtEncoderParameters jwtEncoderParameters =
			JwtEncoderParameters.from(
					JwsHeader.with(MacAlgorithm.HS512).build(),
					jwtClaimsSet
					);
		//Jwt jwt = jwtEncoder.encode(jwtEncoderParameters);
		String jwt  = jwtEncoder.encode(jwtEncoderParameters).getTokenValue();
	
		return Map.of("access-token",jwt);
	}
	 @PostMapping("/create-user")
	    public ResponseEntity<String> createUser(@RequestBody User newUser) {
	        try {
	            User existingUser = userservice.findUserByUsername(newUser.getUsername());
	            if (existingUser != null) {
	                return ResponseEntity.badRequest().body("User already exists.");
	            }

	            userservice.insertUser(newUser);


	            return ResponseEntity.ok("User created successfully");
	        } catch (Exception e) {
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error creating the user.");
	        }
	    }
	 

}