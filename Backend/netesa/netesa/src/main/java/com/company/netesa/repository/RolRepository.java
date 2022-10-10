package com.company.netesa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.company.netesa.model.Rol;

@Repository
public interface RolRepository extends JpaRepository<Rol, Long>{

}
