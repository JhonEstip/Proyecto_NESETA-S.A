package com.company.netesa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.company.netesa.model.Genero;

@Repository
public interface GeneroRepository extends JpaRepository<Genero, Long> {

}

