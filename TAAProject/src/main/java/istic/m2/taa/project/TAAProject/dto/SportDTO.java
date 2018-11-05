package istic.m2.taa.project.TAAProject.dto;

import java.util.HashSet;
import java.util.Set;

import istic.m2.taa.project.TAAProject.entity.LieuId;
import istic.m2.taa.project.TAAProject.entity.Sport;
import istic.m2.taa.project.TAAProject.entity.Sportexterieur;

public class SportDTO {
	private long id;
	private String label;
	//private Set<Long> usersIds;
	private Set<LieuId> lieusids;
	private Double temperatureMax;
	private Double temperatureMin;
	
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
	
	public Set<LieuId> getLieusids() {
		return lieusids;
	}
	public void setLieusids(Set<LieuId> lieusids) {
		this.lieusids = lieusids;
	}
	public Double getTemperatureMax() {
		return temperatureMax;
	}
	public void setTemperatureMax(Double temperatureMax) {
		this.temperatureMax = temperatureMax;
	}
	public Double getTemperatureMin() {
		return temperatureMin;
	}
	public void setTemperatureMin(Double temperatureMin) {
		this.temperatureMin = temperatureMin;
	}
	
	
	public static SportDTO entityToDTO (Sport sport) {
		SportDTO response = new SportDTO();
		response.setId(sport.getId());
		response.setLabel(sport.getLabel());
		response.setLieusids( new HashSet<LieuId>() );
		
		sport.getLieus().stream().forEach(lieu->{
			response.getLieusids().add(lieu.getId());
		});
		
		return response;
	}
	
	public static SportDTO entityToDTOexterieur (Sportexterieur sport) {
		SportDTO response = new SportDTO();
		response.setId(sport.getId());
		response.setLabel(sport.getLabel());
		response.setLieusids( new HashSet<LieuId>() );
		response.setTemperatureMax(sport.getTemperatureMax());
		response.setTemperatureMin(sport.getTemperatureMin());
		
		sport.getLieus().stream().forEach(lieu->{
			response.getLieusids().add(lieu.getId());
		});
		
		return response;
	}
	

}
