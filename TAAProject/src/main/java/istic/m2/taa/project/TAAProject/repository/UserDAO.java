package istic.m2.taa.project.TAAProject.repository;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import istic.m2.taa.project.TAAProject.entity.User;
@Component
@Transactional
public interface UserDAO extends JpaRepository<User, Long> {
	
	/**
	 * 
	 */
	public Optional<User> findById(Long arg0);
	
	@Query(value="SELECT u FROM User u WHERE u.pseudo = :pseudo")
	public User findByName(@Param("pseudo") String pseudo);
		
}
