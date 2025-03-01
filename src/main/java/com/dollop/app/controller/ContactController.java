package com.dollop.app.controller;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dollop.app.dtos.PageableResponse;
import com.dollop.app.entity.Contact;
import com.dollop.app.entity.User;
import com.dollop.app.service.IContactService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/contact")
@RequiredArgsConstructor
public class ContactController {
	
	@Autowired
	private IContactService contactService;
	
	@PostMapping("/save/{userName}")
	public ResponseEntity<Contact> addContact(@PathVariable String userName,@RequestBody Contact contact){
		contactService.addContact(contact, userName);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping("/get/{userName}")
	public ResponseEntity<User> getContactByName(@PathVariable String userName){
		return ResponseEntity.ok(contactService.getContactByName(userName));
	}
	
	@PostMapping("/favorite/{contId}")
	public ResponseEntity<String> markIsFavorite(@PathVariable Integer contId){
		return new ResponseEntity<>(contactService.markIsFavorite(contId),HttpStatus.OK);
	}
	
	@GetMapping("/getAllFavorites")
	public ResponseEntity<List<Contact>> getFavoriteContacts(){
		return  ResponseEntity.ok(contactService.getFavoriteContacts());
	}
	
	@PostMapping("/removefavorite/{contId}")
	public ResponseEntity<String> removeFavorite(@PathVariable Integer contId){
		return new ResponseEntity<>(contactService.removeFavorite(contId),HttpStatus.OK);
	}
	
	@PutMapping("/update/{contName}")
	public ResponseEntity<Contact> updateContact(@RequestBody Map<String, Object> contact,@PathVariable("contName") String contName){
		return contactService.updateContact(contact, contName);
	}
	
	@DeleteMapping("/deleteContact/{contName}")
	@Transactional
	public ResponseEntity<String> deleteContact(@PathVariable("contName") String contName) {
		return new ResponseEntity<>(contactService.deleteContact(contName),HttpStatus.OK);
	}
	
	@GetMapping("/searchContact")
    public ResponseEntity<List<Contact>> searchContacts(@RequestParam("query") String query) {
        List<Contact> contacts = contactService.searchContacts(query);
        return ResponseEntity.ok(contacts);
    }

    @GetMapping("/filterByCriteria")
    public ResponseEntity<List<Contact>> filterByCriteria(
            @RequestParam(required = false) String address,
            @RequestParam(required = false) String emailDomain) {
        
        List<Contact> contacts = contactService.filterByCriteria(address, emailDomain);
        return ResponseEntity.ok(contacts);
    }
    
    @PostMapping("/setemergency/{contName}")
	public ResponseEntity<String> setEmergencyContacts(@PathVariable String contName){
		return new ResponseEntity<>(contactService.setEmergencyContacts(contName),HttpStatus.OK);
	}
    
    @GetMapping("/getAllEmergencies")
	public ResponseEntity<List<Contact>> getEmergencyContacts(){
		return  ResponseEntity.ok(contactService.getEmergencyContacts());
	}
    
    @GetMapping("/allData")
    public ResponseEntity<PageableResponse<Contact>> getAllContactByPages(
    		@RequestParam(value = "pageNumber", defaultValue = "0", required = false) int pageNumber,
    		@RequestParam(value = "pageSize", defaultValue = "2", required = false) int pageSize,
    		@RequestParam(value = "sortBy", defaultValue = "contName", required = false) String sortBy,
    		@RequestParam(value = "sortDir", defaultValue = "acs", required = false) String sortDir
    		) {
				return new ResponseEntity<PageableResponse<Contact>>(contactService.getAllContactsInPage(pageNumber, pageSize, sortBy, sortDir),
						HttpStatus.OK);
    }
    
    
}
