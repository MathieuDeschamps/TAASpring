package istic.m2.taa.project.TAAProject.entity;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 
 * Sport entity
 *
 */
@Entity
public class Sport  {
	
	private long id;
	private String label;
	private Set<User> users;
	private Set<Lieu> lieus;
	
	@Id
	@GeneratedValue
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
	
	@ManyToMany(mappedBy="sports")
	@JsonIgnore
	public Set<User> getUsers() {
		return users;
	}
	
	
	public void setUsers(Set<User> users) {
		this.users = users;
	}
	
	@ManyToMany(mappedBy="sports")
	public Set<Lieu> getLieus() {
		return lieus;
	}
	
	
	public void setLieus(Set<Lieu> lieus) {
		this.lieus = lieus;
	}
	
	

}
