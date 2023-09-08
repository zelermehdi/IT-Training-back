package com.fil.rouge.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import com.fil.rouge.models.AppUser;
import com.fil.rouge.Repository.AppUserRepository;

@Service
public class UserDetailsServiceImpl  implements UserDetailsService{
	@Autowired
	private AppUserRepository gestionUserDao;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		AppUser appUser = gestionUserDao.findUserByUsername(username);
		  
		if(appUser==null)throw new UsernameNotFoundException(String.format("User %s not found", username));
		
		String[] roles = appUser.getListRoles().stream().map(u->u.getRole()).toArray(String[]::new);
		
		UserDetails userDetails =
				User
				.withUsername(appUser.getUsername())
				.password(appUser.getPassword())
				.roles(roles).build();		
		
		return userDetails;
	}

}