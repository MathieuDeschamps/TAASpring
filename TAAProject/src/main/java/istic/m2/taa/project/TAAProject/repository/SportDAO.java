package istic.m2.taa.project.TAAProject.repository;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import istic.m2.taa.project.TAAProject.entity.Sport;
import istic.m2.taa.project.TAAProject.entity.Sportexterieur;

@Component
@Transactional
public interface SportDAO extends JpaRepository<Sport, Long>{
	
	@Query(value="SELECT s FROM Sport s WHERE s.label = :label")
	public Sport findByLabel(@Param("label") String label);
	
	@Query(value="SELECT s FROM Sportexterieur s WHERE s.id = :id ")
	public Optional<Sportexterieur> getSportExterieur(@Param("id") long id);

}
