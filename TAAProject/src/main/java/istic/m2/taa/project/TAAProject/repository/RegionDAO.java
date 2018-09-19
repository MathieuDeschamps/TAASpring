package istic.m2.taa.project.TAAProject.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import istic.m2.taa.project.TAAProject.entity.Region;
@Component
@Transactional
public interface RegionDAO extends JpaRepository<Region, Long>{

	@Query(value="SELECT r FROM Region r WHERE r.name = :name")
	Region findByName( String name );
}
