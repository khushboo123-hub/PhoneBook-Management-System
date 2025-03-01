package com.dollop.app.service;


import java.util.Map;

import com.dollop.app.entity.User;

public interface IUserService {
	
	public User registerUser(User user);
	
    public Map<String,Object> loginUser(String userName, String password);
	

}
