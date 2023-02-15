package com.exercise.pitufos.persistence.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.exercise.pitufos.persistence.entity.PitufoEntity;

@Repository
public interface PitufoRepository extends JpaRepository<PitufoEntity,Long>{
	
	@Transactional(readOnly = true)
	List<PitufoEntity> findByNombreContainingIgnoreCase(String name);

}
