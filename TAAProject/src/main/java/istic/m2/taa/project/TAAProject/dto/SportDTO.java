package istic.m2.taa.project.TAAProject.dto;

import java.util.HashSet;
import java.util.Set;

import istic.m2.taa.project.TAAProject.entity.LieuId;
import istic.m2.taa.project.TAAProject.entity.Sport;

public class SportDTO {
	private long id;
	private String label;
	private Set<Long> usersIds;
	private Set<LieuId> lieusids;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public Set<Long> getUsersIds() {
		return usersIds;
	}
	public void setUsersIds(Set<Long> usersIds) {
		this.usersIds = usersIds;
	}
	public Set<LieuId> getLieusids() {
		return lieusids;
	}
	public void setLieusids(Set<LieuId> lieusids) {
		this.lieusids = lieusids;
	}
	
	public static SportDTO entityToDTO (Sport sport) {
		SportDTO response = new SportDTO();
		response.setId(sport.getId());
		response.setLabel(sport.getLabel());
		response.setLieusids( new HashSet<LieuId>() );
		
		sport.getLieus().stream().forEach(lieu->{
			response.getLieusids().add(lieu.getId());
		});
		sport.getUsers().stream().forEach(users->{
			response.getUsersIds().add(users.getId());
		});
		
		return response;
	}
	

}
