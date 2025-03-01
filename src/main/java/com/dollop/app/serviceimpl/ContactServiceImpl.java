package com.dollop.app.serviceimpl;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.dollop.app.Exception.UserNotFoundException;
import com.dollop.app.dtos.PageableResponse;
import com.dollop.app.entity.Contact;
import com.dollop.app.entity.User;
import com.dollop.app.helper.Helper;
import com.dollop.app.repo.IContactRepository;
import com.dollop.app.repo.IUserRepository;
import com.dollop.app.service.IContactService;

@Service
public class ContactServiceImpl implements IContactService {

	@Autowired
	private IContactRepository contactRepository;

	@Autowired
	private IUserRepository userRepository;

	@Override
	public void addContact(Contact contact, String userName) {
		User user = userRepository.findByUserName(userName)
				.orElseThrow(() -> new UserNotFoundException("User " + userName + " Not Found"));
		contactRepository.save(contact);
		user.getContacts().add(contact);
		userRepository.save(user);
	}

	@Override
	public User getContactByName(String userName) {
		User user = userRepository.findByUserName(userName)
				.orElseThrow(() -> new UserNotFoundException("User " + userName + " Not Found"));
		return user;
	}

	@Override
	public List<Contact> getAllContacts() {
		List<Contact> contacts = contactRepository.findAll();
		return contacts;
	}

	@Override
	public ResponseEntity<Contact> updateContact(Map<String, Object> contact, String contName) {

		Optional<Contact> optional = contactRepository.findByContName(contName);

		if (optional.isPresent()) {
			Contact existContact = optional.get();

			if (contact.containsKey("contName")) {
				existContact.setContName((String) contact.get("contName"));
			}
			if (contact.containsKey("contNumber")) {
				existContact.setContNumber((String) contact.get("contNumber"));
			}
			if (contact.containsKey("contEmail")) {
				existContact.setContEmail((String) contact.get("contEmail"));
			}
			if (contact.containsKey("address")) {
				existContact.setAddress((String) contact.get("address"));
			}
			contactRepository.save(existContact);
			return ResponseEntity.ok(existContact);
		} else {
			return ResponseEntity.notFound().build();
		}

	}

	@Override
	public String deleteContact(String contName) {
		contactRepository.findByContName(contName)
				.orElseThrow(() -> new UserNotFoundException("Contact " + contName + " Not Found"));
		contactRepository.deleteByContName(contName);
		return "Contact " + contName + " Deleted Successfully";
	}

	@Override
	public String markIsFavorite(Integer contId) {
		Contact contact = contactRepository.findById(contId).orElseThrow(() -> new UserNotFoundException("Contact " + contId + " Not Found"));
	    contact.setIsFavorite(true);
	    contactRepository.save(contact);
	    return "Contact " + contId + " is added in favorite list ";
	}

	@Override
	public List<Contact> getFavoriteContacts() {
		List<Contact> contList = contactRepository.findByIsFavoriteTrue();
		return contList;
	}

	@Override
	public List<Contact> getEmergencyContacts() {
		List<Contact> contList = contactRepository.findByIsEmergencyContactTrue();

		return contList;
	}

	@Override
	public String removeFavorite(Integer contId) {
		Contact contact = contactRepository.findById(contId).orElseThrow(() -> new UserNotFoundException("Contact " + contId + " Not Found"));
	    contact.setIsFavorite(false);
	    contactRepository.save(contact);
	    return "Contact " + contId + " is removed from favorite list ";
	}

	@Override
	public List<Contact> searchContacts(String query) {
		return contactRepository.findByContNameContainingIgnoreCaseOrContNumberContaining(query, query);
	}

	@Override
	public List<Contact> filterByCriteria(String address, String contEmail) {
		String emailCriteria = "%" + contEmail + "%";
		return contactRepository.findByAddressContainingIgnoreCaseOrContEmailContainingIgnoreCase(address, emailCriteria);
	}

	@Override
	public String setEmergencyContacts(String contName) {
		Contact contact = contactRepository.findByContName(contName).orElseThrow(() -> new UserNotFoundException("EmergencyContact " + contName + " Not Found"));
	    contact.setIsEmergencyContact(true);
	    contactRepository.save(contact);
	    return "Set " + contName + " in Emergency Contact";
		
	}

	@Override
	public PageableResponse<Contact> getAllContactsInPage(int pageNumber, int pageSize, String sortBy, String sortDir) {

		Sort sort = (sortDir.equalsIgnoreCase("desc"))?
				(Sort.by(sortBy).descending()):
					(Sort.by(sortBy).ascending());
		Pageable pageable = PageRequest.of(pageNumber, pageSize,sort);
		Page<Contact> page = contactRepository.findAll(pageable);
		PageableResponse<Contact> pageableResponse = Helper.getPageableResponse(page, Contact.class);
		return pageableResponse;
	}
	

}
