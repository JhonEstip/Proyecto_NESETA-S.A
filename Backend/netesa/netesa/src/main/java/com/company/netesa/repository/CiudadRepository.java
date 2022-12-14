package com.company.netesa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.company.netesa.model.Ciudad;

@Repository
public interface CiudadRepository extends JpaRepository<Ciudad, Long>{

}
