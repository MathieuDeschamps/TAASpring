package istic.m2.taa.project.TAAProject.web;

import java.util.Optional;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import istic.m2.taa.project.TAAProject.entity.Region;
import istic.m2.taa.project.TAAProject.repository.RegionDAO;

@RestController
public class RegionController {
	
	@Autowired
	RegionDAO regionDAO;
	
	@GetMapping(value="/region/id/{id}",produces=MediaType.APPLICATION_JSON)
	public ResponseEntity<Region> getRegion( @PathVariable("id") Long id){
		
		Optional<Region> opt = regionDAO.findById(id);
		
		if( opt.isPresent( ) ){
			Region region = opt.get();
			return new ResponseEntity<Region>(region,HttpStatus.ACCEPTED);
		}
		Region region = new Region();
		region.setId(-1);
		return new ResponseEntity<Region>(region,HttpStatus.ACCEPTED);
		
	}
	
	@GetMapping(value="/region/name/{name}",produces=MediaType.APPLICATION_JSON)
	public ResponseEntity<Region> getRegionByName( @PathVariable("name") String name){
		
		Region region = regionDAO.findByName(name);
		if(region != null ){
			return new ResponseEntity<Region>(region,HttpStatus.ACCEPTED);
		}
		else
		{
			return new ResponseEntity<Region>(region, HttpStatus.NO_CONTENT);
		}
		
	}
	
	@PostMapping(value="/region")
	public Response postRegion(@RequestBody Region region){
		
		regionDAO.save(region);
		
		return  Response.noContent().build();
	}

}
