package com.dollop.app.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Contact {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer contId;
	

	@Size(min = 2, max = 100)
	@NotBlank(message = "Name is required")
	private String contName;
	
	@Column(unique =  true,nullable = false)
	@NotBlank(message = "Name PhoneNumber is required")
	private String contNumber;
	
	@Email
	@Column(nullable = false)
	private String contEmail;
	
	@Column(length = 50, nullable = false)
	private String address;
	
	private Boolean isFavorite = false;
	
	private LocalDate birthday;
	
	private List<String> tags = new ArrayList<>();
	
	@Column(length = 500, nullable = false)
	private String notes;
	
	private Boolean isEmergencyContact = false;
	
}
