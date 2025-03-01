package com.dollop.app.controller;

import java.util.List;
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
import com.dollop.app.Exception.ContactNotFoundException;
import com.dollop.app.Exception.GroupNotFoundException;
import com.dollop.app.entity.Contact;
import com.dollop.app.entity.GroupEntity;
import com.dollop.app.service.IGroupService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/group/")
@RequiredArgsConstructor
public class GroupController {

	@Autowired
	private IGroupService groupService;

	@PostMapping("groupCreate/{groupId}")
	public ResponseEntity<GroupEntity> createGroup(@PathVariable Integer groupId, @RequestBody GroupEntity entity) {
		return ResponseEntity.ok(groupService.createGroup(entity, groupId));
	}

	@PutMapping("saveContactToGroup/{groupId}")
	public ResponseEntity<String> addContactToGroup(@PathVariable Integer groupId, @RequestParam String contName) {
		try {
			String result = groupService.addContactToGroup(groupId, contName);
			return ResponseEntity.ok(result);
		} catch (GroupNotFoundException | ContactNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("An error occurred while adding the contact.");
		}
	}

	@GetMapping("getContactByGroup/{groupId}")
	public ResponseEntity<List<Contact>> getContactsByGroup(@PathVariable Integer groupId) {
		try {
			List<Contact> contacts = groupService.getContactsByGroup(groupId);
			return ResponseEntity.ok(contacts);
		} catch (GroupNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}

	@DeleteMapping("/{groupId}/contacts/{contId}")
	public ResponseEntity<String> removeContactFromGroup(@PathVariable Integer groupId, @PathVariable Integer contId) {
		String result = groupService.removeContactFromGroup(groupId, contId);
		if (result.contains("successfully")) {
			return new ResponseEntity<>(result, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("deleteGroup/{groupId}")
	// @Transactional
	public ResponseEntity<String> deleteContact(@PathVariable("groupId") Integer groupId) {
		return new ResponseEntity<>(groupService.deleteGroup(groupId), HttpStatus.OK);
	}
}
