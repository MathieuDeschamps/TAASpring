package istic.m2.taa.project.TAAProject.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import istic.m2.taa.project.TAAProject.entity.Lieu;
import istic.m2.taa.project.TAAProject.entity.LieuId;

import java.util.List;

@Component
@Transactional
public interface LieuDAO extends JpaRepository<Lieu, LieuId> {

	@Query(value="SELECT l FROM Lieu l WHERE l.id.labelVille = :label")
	public Lieu findByLabel(@Param("label") String label);


	@Query(value="SELECT l FROM Lieu l")
	public List<Lieu> getAllLieu();


}
