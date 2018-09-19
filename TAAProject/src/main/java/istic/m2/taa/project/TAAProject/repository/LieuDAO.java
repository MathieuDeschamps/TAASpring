package istic.m2.taa.project.TAAProject.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import istic.m2.taa.project.TAAProject.entity.Lieu;
import istic.m2.taa.project.TAAProject.entity.LieuId;

@Component
@Transactional
public interface LieuDAO extends JpaRepository<Lieu, LieuId> {

}
