package com.company.netesa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.company.netesa.model.Sucursal;

@Repository
public interface SucursalRepository extends JpaRepository<Sucursal, Long> {

}
