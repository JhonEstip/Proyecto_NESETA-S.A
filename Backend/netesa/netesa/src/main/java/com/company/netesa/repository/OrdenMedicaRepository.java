package com.company.netesa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.company.netesa.model.OrdenMedica;

@Repository
public interface OrdenMedicaRepository extends JpaRepository<OrdenMedica, Long> {

}

