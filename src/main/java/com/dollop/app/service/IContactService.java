package com.dollop.app.service;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.dollop.app.dtos.PageableResponse;
import com.dollop.app.entity.Contact;
import com.dollop.app.entity.User;

public interface IContactService {

	public void addContact(Contact contact, String userName);

	public User getContactByName(String userName);

	public List<Contact> getAllContacts();

	public ResponseEntity<Contact> updateContact(Map<String, Object> contact, String contName);

	public String deleteContact(String contName);

	public String markIsFavorite(Integer contId);

	public String removeFavorite(Integer contId);

	public List<Contact> getFavoriteContacts();

	public List<Contact> getEmergencyContacts();
	
	public String setEmergencyContacts(String contName);

	List<Contact> searchContacts(String query);

	List<Contact> filterByCriteria(String address, String contEmail);
	
	public PageableResponse<Contact> getAllContactsInPage(int pageNumber, int pageSize, String sortBy, String sortDir);
}
