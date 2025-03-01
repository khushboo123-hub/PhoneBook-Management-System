package com.dollop.app.service;

import java.util.List;

import com.dollop.app.entity.Contact;
import com.dollop.app.entity.GroupEntity;

public interface IGroupService {
	
	public GroupEntity createGroup(GroupEntity groupEntity, Integer groupId);
	
	public String addContactToGroup(Integer groupId, String contName);
	
	public List<Contact> getContactsByGroup(Integer groupId);
	
	public String removeContactFromGroup(Integer groupId, Integer contId);
	
	public String deleteGroup(Integer groupId);

}
