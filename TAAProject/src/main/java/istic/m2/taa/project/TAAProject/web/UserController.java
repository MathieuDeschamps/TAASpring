package istic.m2.taa.project.TAAProject.web;

import java.util.Base64;
import java.util.List;
import java.util.Optional;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import istic.m2.taa.project.TAAProject.dto.UserDTO;
import istic.m2.taa.project.TAAProject.entity.Region;
import istic.m2.taa.project.TAAProject.entity.User;
import istic.m2.taa.project.TAAProject.repository.RegionDAO;
import istic.m2.taa.project.TAAProject.repository.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

@RestController
public class UserController {


    @Autowired
    UserDAO userDAO;

    @Autowired
    RegionDAO regionDAO;

    @Autowired
    PasswordEncoder passwordEncoder;

    @GetMapping(value = "/users", produces = MediaType.APPLICATION_JSON)
    public ResponseEntity<List<User>> getUsers() {

        List<User> users = userDAO.findAll();
        return new ResponseEntity<List<User>>(users, HttpStatus.ACCEPTED);

    }

    @GetMapping(value = "/user/id/{id}", produces = MediaType.APPLICATION_JSON)
    public ResponseEntity<UserDTO> getUserById(@PathVariable("id") Long id) {
        Optional<User> opt = userDAO.findById(id);
        if (opt.isPresent()) {
            return new ResponseEntity<UserDTO>(UserDTO.userToDTO(opt.get()), HttpStatus.ACCEPTED);
        } else {

            return ResponseEntity.notFound().build();
        }
    }


    @GetMapping(value = "/user/pseudo/{pseudo}", produces = MediaType.APPLICATION_JSON)
    public User getUserByPseudo(@PathVariable("pseudo") String pseudo) {

        return userDAO.findByName(pseudo);
    }

    //@CrossOrigin(origins="http://localhost:4200")@PostMapping(value = "/user", produces = {MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN}, consumes = MediaType.APPLICATION_JSON)
    public Response addUser(@RequestBody User user) {
        System.out.println("coucou");
		String password = Base64.getDecoder().decode(user.getPassword()).toString();
		user.setPassword( new BCryptPasswordEncoder().encode(password));userDAO.save(user);
        return Response.ok(user.getId()).status(Status.ACCEPTED)
                .build();
    }

    @PutMapping(value = "/user", produces = {MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN}, consumes = MediaType.APPLICATION_JSON)
    public Response updateUser(@RequestBody User user) {

        Optional<User> opt = userDAO.findById(user.getId());
        if (opt.isPresent()) {
            User usr = opt.get();
            usr.setEmail(user.getEmail());
            usr.setPassword(user.getPassword());
            userDAO.save(usr);
        } else {
            userDAO.save(user);
        }
        return Response.noContent().status(Status.ACCEPTED).build();
    }

    @PutMapping(value = "/user/{userId}/suscribeRegion/{id}", produces = {MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN}, consumes = MediaType.APPLICATION_JSON)
    public Response suscribeToRegion(@PathVariable("userId") Long userId, @PathVariable("id") Long id) {

        Optional<Region> optReg = regionDAO.findById(id);
        Optional<User> optUsr = userDAO.findById(userId);

        if (!optReg.isPresent() || !optUsr.isPresent()) {
            return Response.noContent().status(Status.NOT_FOUND).build();
        }
        Region reg = optReg.get();
        User user = optUsr.get();

        reg.getUsers().add(user);
        regionDAO.save(reg);
        user.getRegions().add(reg);

        return Response.noContent().status(Status.ACCEPTED).build();

    }

    @GetMapping(value = "/user/connect/{password}/{pseudo}", produces = MediaType.APPLICATION_JSON)
    public ResponseEntity<Boolean> connectUser(@PathVariable("password") String password, @PathVariable("pseudo") String pseudo) throws UnsupportedEncodingException {

        byte[] decodedAsBytes = Base64.getDecoder().decode(password);
        String decoded = new String(decodedAsBytes, "UTF-8");

        boolean matches = passwordEncoder.matches(decoded, userDAO.findByName(pseudo).getPassword());
        return new ResponseEntity<Boolean>(matches, HttpStatus.ACCEPTED);
    }


}
