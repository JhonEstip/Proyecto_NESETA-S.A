package com.company.netesa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.company.netesa.model.Citas;

@Repository
public interface CitasRepository extends JpaRepository<Citas, Long> {

}

