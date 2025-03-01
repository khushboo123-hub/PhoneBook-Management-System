package com.dollop.app.serviceimpl;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dollop.app.Exception.UserNotFoundException;
import com.dollop.app.entity.User;
import com.dollop.app.repo.IUserRepository;
import com.dollop.app.service.IUserService;

@Service
public class UserServiceImpl implements IUserService {
	
	@Autowired
	private IUserRepository userRepository;

	@Override
	public User registerUser(User user) {
		User usr = userRepository.save(user);
		return usr;
	}  

	@Override
	public Map<String, Object> loginUser(String userName, String password) {
        Optional<User> user = userRepository.findByUserNameAndPassword(userName, password);
		Map<String, Object> map = new HashMap<>();
		if(user.isPresent()) {
			map.put("message", "User Login SuccessFully");
			return map;
		}else {
			 throw new UserNotFoundException("Invalid User Login");
		}
	
	}

}
