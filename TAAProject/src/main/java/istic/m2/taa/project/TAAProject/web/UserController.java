package istic.m2.taa.project.TAAProject.web;

import java.util.Optional;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import istic.m2.taa.project.TAAProject.entity.User;
import istic.m2.taa.project.TAAProject.repository.UserDAO;

@RestController
public class UserController {
	
	
	@Autowired UserDAO userDAO;
	
	
	@GetMapping(value="/user/id/{id}",produces=MediaType.APPLICATION_JSON)
	public ResponseEntity<User> getUserById(@PathVariable("id") Long id) {
		User temp = new User();
		temp.setEmail("mai");
		temp.setPassword("pass");
		temp.setPseudo("toitoi");
		userDAO.save(temp);
		Optional<User> opt = userDAO.findById(id);
		if( opt.isPresent()){
			return new ResponseEntity<User>(opt.get(), HttpStatus.ACCEPTED);
		}
		else{
			User user = new User();
			user.setId(-1L);
			return new ResponseEntity<User>(user,HttpStatus.ACCEPTED);
		}	
	}
	
	
	@GetMapping(value="/user/pseudo/{pseudo}",produces=MediaType.APPLICATION_JSON)
	public User getUserByPseudo(@PathVariable("pseudo") String pseudo) {
	
	 return userDAO.findByName(pseudo);
	}
	
	@PostMapping(value="/user",produces=MediaType.TEXT_PLAIN,consumes=MediaType.APPLICATION_JSON)
	public Response addUser(@RequestBody User user){
		userDAO.save(user);
		return Response.noContent().status(Status.ACCEPTED)	
					   .build();
	}
	
	@PutMapping(value="/user",produces=MediaType.TEXT_PLAIN,consumes=MediaType.APPLICATION_JSON)
	public Response updateUser(@RequestBody User user){
		
		Optional<User> opt = userDAO.findById(user.getId( ) );
		if( opt.isPresent( ) )
		{
			User usr = opt.get();
			usr.setEmail( user.getEmail());
			usr.setPassword( user.getPassword( ) );
			userDAO.save(usr);
		}
		else
		{
			userDAO.save(user);
		}
		return Response.noContent().status(Status.ACCEPTED).build();
	}
	
	

}
