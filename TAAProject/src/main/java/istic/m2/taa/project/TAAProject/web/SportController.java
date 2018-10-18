package istic.m2.taa.project.TAAProject.web;

import javax.ws.rs.core.MediaType;
import java.util.Optional;

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

import istic.m2.taa.project.TAAProject.dto.SportDTO;
import istic.m2.taa.project.TAAProject.entity.Sport;
import istic.m2.taa.project.TAAProject.entity.Sportexterieur;
import istic.m2.taa.project.TAAProject.repository.SportDAO;

@RestController
public class SportController {
	
	@Autowired SportDAO sportDAO;
	
	@GetMapping(value="/sport/id/{id}",produces=MediaType.APPLICATION_JSON)
	public ResponseEntity<SportDTO> getSportById (@PathVariable("id") long id){
		
		Optional<Sport> optional = sportDAO.findById(id);
		if(optional.isPresent()) {
			return new ResponseEntity<SportDTO>(SportDTO.entityToDTO(optional.get()) ,HttpStatus.ACCEPTED);
		}
		else {
			return ResponseEntity.noContent().build();
		}
	}
	
	@GetMapping(value="/sport/label/{label}", produces=MediaType.APPLICATION_JSON)
	public ResponseEntity<SportDTO> getSportByLabel(@PathVariable("label") String label){
		Sport sport = sportDAO.findByLabel(label);
		if(sport!=null) {
			return new ResponseEntity<SportDTO> (SportDTO.entityToDTO(sport), HttpStatus.ACCEPTED);
		}
		else {
			return ResponseEntity.noContent().build();
		}
	}
	
	@PostMapping(value="/sport",produces=MediaType.TEXT_PLAIN,consumes=MediaType.APPLICATION_JSON)
	public Response addSport(@RequestBody Sport sport) {
		sportDAO.save(sport);
		return Response.noContent().status(Status.ACCEPTED).build();
	}
	
	
	@GetMapping("/sportexterieur/{id}")
	public ResponseEntity<SportDTO> getSportExtById (@PathVariable("id") long id){
		Optional<Sportexterieur> optional = sportDAO.getSportExterieur(id);
		
		if(optional.isPresent()) {
			return new ResponseEntity<SportDTO>(SportDTO.entityToDTOexterieur(optional.get()) ,HttpStatus.ACCEPTED);
		}
		else {
			return ResponseEntity.noContent().build();
		}
		
	}
	
	@PutMapping(value="/sport", produces=MediaType.TEXT_PLAIN, consumes=MediaType.APPLICATION_JSON)
	public Response updateSport(@RequestBody Sport sport) {
		Optional<Sport> optional = sportDAO.findById(sport.getId());
		if(optional.isPresent()) {
			Sport sporttmp = optional.get();
			sporttmp.setLabel(sport.getLabel());
			sporttmp.setLieus(sport.getLieus());
			sporttmp.setUsers(sport.getUsers());
			
			sportDAO.save(sporttmp);
			return Response.noContent().status(Status.ACCEPTED).build();
		}
		else {
			sportDAO.save(sport);
			return Response.noContent().status(Status.ACCEPTED).build();
		}
	}

	
	

}
