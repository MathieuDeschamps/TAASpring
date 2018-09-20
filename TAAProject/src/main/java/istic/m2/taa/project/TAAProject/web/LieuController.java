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

import istic.m2.taa.project.TAAProject.dto.LieuDTO;
import istic.m2.taa.project.TAAProject.entity.Lieu;
import istic.m2.taa.project.TAAProject.entity.LieuId;
import istic.m2.taa.project.TAAProject.repository.LieuDAO;

@RestController
public class LieuController {

	@Autowired LieuDAO lieuDAO;
	
	@GetMapping(value="lieu/id/{CodePostal}/{NomVille}",produces=MediaType.APPLICATION_JSON)
	public ResponseEntity<LieuDTO> getLieuById(@PathVariable("CodePostal") String codepostal, @PathVariable("NomVille") String nomville){		
		LieuId lieuid = new LieuId();
		lieuid.setCodePostal(codepostal);
		lieuid.setLabelVille(nomville);
		Optional<Lieu> optional = lieuDAO.findById(lieuid);
		if(optional.isPresent()) {
			return new ResponseEntity<LieuDTO>(LieuDTO.entityToDTO(optional.get()) ,HttpStatus.ACCEPTED);
		}
		else {
			return new ResponseEntity<LieuDTO>(LieuDTO.entityToDTO(new Lieu()), HttpStatus.NO_CONTENT);
		}
	}
	
	@GetMapping(value="lieu/label/{label}",produces=MediaType.APPLICATION_JSON)
	public ResponseEntity<LieuDTO> getLieuByLabel(@PathVariable("label") String label){
		Lieu lieu = lieuDAO.findByLabel(label);
		if(lieu != null) {
			return new ResponseEntity<LieuDTO>(LieuDTO.entityToDTO(lieu),HttpStatus.ACCEPTED);
		}
		else {
			return new ResponseEntity<LieuDTO>(LieuDTO.entityToDTO(new Lieu()), HttpStatus.NO_CONTENT);
		}
	}
	
	@PostMapping(value="/lieu",produces=MediaType.TEXT_PLAIN,consumes=MediaType.APPLICATION_JSON)
	public Response addLieu(@RequestBody Lieu lieu) {
		lieuDAO.save(lieu);
		return Response.noContent().status(Status.ACCEPTED).build();
	}
	
	@PutMapping(value="/lieu", produces=MediaType.TEXT_PLAIN,consumes=MediaType.APPLICATION_JSON)
	public Response updateSportLieu (@RequestBody Lieu lieu) {
		Optional<Lieu> optional = lieuDAO.findById(lieu.getId());
		if(optional.isPresent()) {
			Lieu lieutmp = optional.get();
			lieutmp.setSports(lieu.getSports());
			return Response.noContent().status(Status.ACCEPTED).build();
		}
		else {
			lieuDAO.save(lieu);
			return Response.noContent().status(Status.ACCEPTED).build();

		}
	}
	
	
}
