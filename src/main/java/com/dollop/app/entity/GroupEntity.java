package com.dollop.app.entity;

import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class GroupEntity {

	@Id
	private Integer groupId;
	
	private String groupName;
	
	@OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	@JoinColumn(name = "groupIdFK")
	private List<Contact> contacts = new ArrayList<>() ;
}
