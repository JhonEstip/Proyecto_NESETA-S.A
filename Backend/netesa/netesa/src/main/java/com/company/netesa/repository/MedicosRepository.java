package com.company.netesa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.company.netesa.model.Medicos;

@Repository
public interface MedicosRepository extends JpaRepository<Medicos, Long> {

}

