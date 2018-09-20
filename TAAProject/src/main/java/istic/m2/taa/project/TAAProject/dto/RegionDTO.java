package istic.m2.taa.project.TAAProject.dto;

import java.util.List;

import istic.m2.taa.project.TAAProject.entity.LieuId;
import istic.m2.taa.project.TAAProject.entity.Region;

/**
 * 
 * @author boby
 *
 */
public class RegionDTO {

	private long id;
	private long code;
	private String name;
	private List<Long> userIds;
	private List<LieuId> lieuxIds;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getCode() {
		return code;
	}
	public void setCode(long code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Long> getUserIds() {
		return userIds;
	}
	public void setUserIds(List<Long> userIds) {
		this.userIds = userIds;
	}
	public List<LieuId> getLieuxIds() {
		return lieuxIds;
	}
	public void setLieuxIds(List<LieuId> lieuxIds) {
		this.lieuxIds = lieuxIds;
	}
	
	public RegionDTO entityToDTO( Region region)
	{
		RegionDTO dto = new RegionDTO();
		return null;
	}
}
