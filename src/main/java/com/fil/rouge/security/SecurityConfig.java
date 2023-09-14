package com.fil.rouge.security;



import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.nimbusds.jose.jwk.source.ImmutableSecret;

import com.fil.rouge.models.AppUser;
import com.fil.rouge.Repository.AppUserRepository;



@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Autowired
	private AppUserRepository gestionUserDao;
	
	@Autowired
	private UserDetailsServiceImpl userDetailsService;

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
		
		return http.cors()
				.and().
				sessionManagement(sa->sa.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.csrf(csrf->csrf.disable())
				.authorizeHttpRequests(ar->ar.requestMatchers("/auth/login/**").permitAll())
				.authorizeHttpRequests(ar->ar.requestMatchers("/auth/create-user").permitAll())
				.authorizeHttpRequests(ar->ar.requestMatchers("/auth/profil/**").permitAll())

				.authorizeHttpRequests(ar->ar.anyRequest().authenticated())
				//.oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt)
				.oauth2ResourceServer(oa->oa.jwt(Customizer.withDefaults()))
				.userDetailsService(userDetailsService)
				.build();
	}
	@Bean
	JwtEncoder jwtEncoder() {
		String secretKey ="123s456dfghj456987ertyuiopm214qsdfghjklmnbv321654qwxertyuio9783k";
		return new NimbusJwtEncoder(new ImmutableSecret(secretKey.getBytes()));
	}
	@Bean
	JwtDecoder jwtDecoder() {
		String secretKey ="123s456dfghj456987ertyuiopm214qsdfghjklmnbv321654qwxertyuio9783k";
		SecretKeySpec secretKeySpec  = new SecretKeySpec(secretKey.getBytes(), "RSA");
		return NimbusJwtDecoder.withSecretKey(secretKeySpec).macAlgorithm(MacAlgorithm.HS512).build();
	}
	@Bean
	public AuthenticationManager authenticationManager(UserDetailsService userDetailsService) {
		DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
		daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
		daoAuthenticationProvider.setUserDetailsService(userDetailsService);
		
		return new ProviderManager(daoAuthenticationProvider);
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}