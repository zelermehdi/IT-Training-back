package com.fil.rouge;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.fil.rouge.models.AppRole;
import com.fil.rouge.models.AppUser;
import com.fil.rouge.services.GestionUserDao;


@SpringBootApplication
public class ItTrainingBackApplication implements CommandLineRunner {
	
	@Autowired
	private GestionUserDao gestionUserDao;
	
    public static void main(String[] args) {
    	
    	
    	
    	
    	
    	
        SpringApplication.run(ItTrainingBackApplication.class, args);
    }

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		
		
		
		
		//4-	Insert de plusieurs users
		
		  List<AppRole> liste = new ArrayList<AppRole>();
		  this.gestionUserDao.insertUser( new AppUser(0,"admin","Test1234",liste));
		  this.gestionUserDao.insertUser( new AppUser(0,"francis","Test1234",liste));
		  this.gestionUserDao.insertUser( new AppUser(0,"bart","Test1234",liste));
		  this.gestionUserDao.insertUser( new AppUser(0,"lisa","Test1234",liste));
		  
		  //5- Insert des roles this.gestionUserDao.insertRole(new AppRole(0,"ADMIN"));
		  this.gestionUserDao.insertRole(new AppRole(0,"USER"));
		  this.gestionUserDao.insertRole(new AppRole(0,"MANAGER"));
		  
		  //6- Add Role To User 
		  AppRole roleAdmin = new AppRole();
		  roleAdmin.setIdRole(1); AppRole roleUser = new AppRole();
		  roleUser.setIdRole(2);
		  
		  AppUser userFrancis = new AppUser(); userFrancis.setIdUser(2); //francis
		  AppUser userBart = new AppUser(); userBart.setIdUser(3); //francis
		  this.gestionUserDao.addRoleToUser(roleAdmin, userFrancis);
		  this.gestionUserDao.addRoleToUser(roleUser, userFrancis);
		  this.gestionUserDao.addRoleToUser(roleUser, userBart);	
		
	}
}


