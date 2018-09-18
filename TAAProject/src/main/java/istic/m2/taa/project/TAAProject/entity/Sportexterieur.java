package istic.m2.taa.project.TAAProject.entity;

import javax.persistence.Entity;

@Entity
public class Sportexterieur extends Sport {
	
	private double temperatureMax;
	private double temperatureMin;
		
	public double getTemperatureMax() {
		return temperatureMax;
	}
	
	public void setTemperatureMax(double temperatureMax) {
		this.temperatureMax = temperatureMax;
	}
	
	
	public double getTemperatureMin() {
		return temperatureMin;
	}
	
	
	public void setTemperatureMin(double temperatureMin) {
		this.temperatureMin = temperatureMin;
	}
	
	

}
