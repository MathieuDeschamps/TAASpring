package istic.m2.taa.project.TAAProject.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class LieuId implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1266602705422374864L;
	
	private String codePostal;
	private String labelVille;
	
	@Column(name="code_postal")
	public String getCodePostal() {
		return codePostal;
	}
	
	public void setCodePostal(String codePostal) {
		this.codePostal = codePostal;
	}
	
	@Column(name="label_ville")
	public String getLabelVille() {
		return labelVille;
	}
	

	public void setLabelVille(String labelVille) {
		this.labelVille = labelVille;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codePostal == null) ? 0 : codePostal.hashCode());
		result = prime * result + ((labelVille == null) ? 0 : labelVille.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LieuId other = (LieuId) obj;
		if (codePostal == null) {
			if (other.codePostal != null)
				return false;
		} else if (!codePostal.equals(other.codePostal))
			return false;
		if (labelVille == null) {
			if (other.labelVille != null)
				return false;
		} else if (!labelVille.equals(other.labelVille))
			return false;
		return true;
	}

}
