package istic.m2.taa.project.TAAProject.dto;

import java.util.ArrayList;
import java.util.List;

import istic.m2.taa.project.TAAProject.entity.Region;
import istic.m2.taa.project.TAAProject.entity.Sport;
import istic.m2.taa.project.TAAProject.entity.Sportexterieur;
import istic.m2.taa.project.TAAProject.entity.User;

public class UserDTO {

	private long id;
	private String pseudo;
	private String email;
	private List<Long> regionsId;
	private List<Long> sportsId;
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getPseudo() {
		return pseudo;
	}
	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public List<Long> getRegionsId() {
		return regionsId;
	}
	public void setRegionsId(List<Long> regionsId) {
		this.regionsId = regionsId;
	}
	public List<Long> getSportsId() {
		return sportsId;
	}
	public void setSportsId(List<Long> sportsId) {
		this.sportsId = sportsId;
	}
	
	public static UserDTO userToDTO( User user )
	{
		UserDTO dto = new UserDTO();
		dto.setId(user.getId( ));
		dto.setPseudo(user.getPseudo( ));
		dto.setEmail(user.getEmail( ));
		List<Long> region = new ArrayList<>();
		
		for( Region reg :  user.getRegions( ) ){
			region.add( reg.getId( ) );
		}
		
		dto.setRegionsId(region);
		List<Long> sports = new ArrayList<>();
		for(Sportexterieur sp : user.getSports( ) )
		{
			sports.add(sp.getId( ) );
		}
		dto.setSportsId(sports);
		
		return dto;
		
	}
	
	
}
