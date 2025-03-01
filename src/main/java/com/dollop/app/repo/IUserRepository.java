package com.dollop.app.repo;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.dollop.app.entity.User;

@Repository
public interface IUserRepository extends JpaRepository<User, Integer>{
	
	Optional <User> findByUserNameAndPassword(String userName, String password);
	
	Optional <User> findByUserName(String userName);


}
