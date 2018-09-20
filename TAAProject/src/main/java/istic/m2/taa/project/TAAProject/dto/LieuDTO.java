package istic.m2.taa.project.TAAProject.dto;

import java.util.HashSet;
import java.util.Set;

import istic.m2.taa.project.TAAProject.entity.Lieu;
import istic.m2.taa.project.TAAProject.entity.LieuId;

public class LieuDTO {
	
	private LieuId id;
	private Set<Long> sportsids;
	private Long regionid;
	public LieuId getId() {
		return id;
	}
	public void setId(LieuId id) {
		this.id = id;
	}
	public Set<Long> getSportsids() {
		return sportsids;
	}
	public void setSportsids(Set<Long> sportsids) {
		this.sportsids = sportsids;
	}
	public Long getRegion() {
		return regionid;
	}
	public void setRegion(Long regionid) {
		this.regionid = regionid;
	}
	
	public static LieuDTO entityToDTO (Lieu lieu) {
		LieuDTO response = new LieuDTO();
		response.setId(lieu.getId());
		response.setRegion(lieu.getRegion().getId());
		response.setSportsids(new HashSet<Long>());
		
		lieu.getSports().stream().forEach(sports->{
			response.sportsids.add(sports.getId());
		});
		return response;
	}

}
