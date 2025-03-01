package com.dollop.app.entity;

import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer userId;
	
	
	@Column(unique = true,length = 50,nullable = false)
	private String userName;
	
	@Email
	@Column(unique = true, nullable = false)
	private String userEmail;
	
	@Column(nullable = false)
	@Size(min = 8)
	private String password;
	
	@OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	@JoinColumn(name = "userIdFk")
	private List<Contact> contacts = new ArrayList<>() ;
	
//	@OneToMany(mappedBy = "user",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
//	private List<Contact> emergencyContacts = new ArrayList<>();

}
