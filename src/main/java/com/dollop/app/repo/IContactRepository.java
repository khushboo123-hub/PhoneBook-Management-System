package com.dollop.app.repo;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.dollop.app.entity.Contact;

@Repository
public interface IContactRepository extends JpaRepository<Contact, Integer>{


	Optional <Contact> findByContName(String contName);
	
	Optional<Contact> deleteByContName(String contName);
	
	@Query("Select c from Contact c where c.isFavorite = true")
	List<Contact> findByIsFavoriteTrue();
	
	@Query("Select c from Contact c where c.isEmergencyContact  = true")
	List<Contact> findByIsEmergencyContactTrue();
	
	List<Contact> findByContNameContainingIgnoreCaseOrContNumberContaining(String contName, String contNumber);

    List<Contact> findByAddressContainingIgnoreCaseOrContEmailContainingIgnoreCase(String address, String contEmail);
}
