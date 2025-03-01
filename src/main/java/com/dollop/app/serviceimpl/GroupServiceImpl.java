package com.dollop.app.serviceimpl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dollop.app.Exception.ContactNotFoundException;
import com.dollop.app.Exception.DublicateEntryException;
import com.dollop.app.Exception.GroupNotFoundException;
import com.dollop.app.entity.Contact;
import com.dollop.app.entity.GroupEntity;
import com.dollop.app.repo.IContactRepository;
import com.dollop.app.repo.IGroupRepository;
import com.dollop.app.service.IGroupService;

@Service
public class GroupServiceImpl implements IGroupService {
	
	@Autowired
	private IGroupRepository groupRepository;
	
	@Autowired
	private IContactRepository contactRepository;

	@Override
	public GroupEntity createGroup(GroupEntity groupEntity,Integer groupId) {
		boolean groupExists = groupRepository.existsById(groupId);
	    if (groupExists) {
	        throw new DublicateEntryException("The group with ID " + groupId + " already exists.");
	    }
	    groupEntity.setGroupId(groupId);
	    return groupRepository.save(groupEntity); 
	}

	@Override
	public String addContactToGroup(Integer groupId, String contName) {

		GroupEntity group = groupRepository.findById(groupId)
	        .orElseThrow(() -> new GroupNotFoundException("Group with ID " + groupId + " not found"));

	    Contact contact = contactRepository.findByContName(contName)
	        .orElseThrow(() -> new ContactNotFoundException("Contact with name " + contName + " not found"));

	    if (group.getContacts().contains(contact)) {
	        return "Contact is already part of the group";
	    }

	    group.getContacts().add(contact);

	    groupRepository.save(group);

	    return "Contact successfully added to the group";
	}

	@Override
	public List<Contact> getContactsByGroup(Integer groupId) {
		
		GroupEntity group = groupRepository.findById(groupId)
	            .orElseThrow(() -> new GroupNotFoundException("Group with ID " + groupId + " not found"));
	        return group.getContacts();  
	}

	@Override
	public String removeContactFromGroup(Integer groupId, Integer contId) {
			GroupEntity group = groupRepository.findById(groupId)
		        .orElseThrow(() -> new GroupNotFoundException("Group with ID " + groupId + " not found"));

		    Contact contact = contactRepository.findById(contId)
		        .orElseThrow(() -> new ContactNotFoundException("Contact with name " + contId + " not found"));

		    group.getContacts().remove(contact);

		    groupRepository.save(group);

		    return "Contact successfully Removed to the group";
	}

	@Override
	public String deleteGroup(Integer groupId) {
		GroupEntity group = groupRepository.findById(groupId)
		        .orElseThrow(() -> new GroupNotFoundException("Group with ID " + groupId + " not found"));
		groupRepository.delete(group);
		return "Group Deleted " + groupId + " Successfully";
	}

}
