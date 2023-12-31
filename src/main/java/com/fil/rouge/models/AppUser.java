package com.fil.rouge.models;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "app_user")
public class AppUser implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected int id;

    protected String username;
    protected String password;

    @ManyToMany(fetch = FetchType.EAGER)
    protected List<AppRole> listRoles = new ArrayList<>();

	public AppUser() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<AppRole> getListRoles() {
		return listRoles;
	}

	public void setListRoles(List<AppRole> listRoles) {
		this.listRoles = listRoles;
	}

	public AppUser(int id, String username, String password, List<AppRole> listRoles) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.listRoles = listRoles;
	}

}