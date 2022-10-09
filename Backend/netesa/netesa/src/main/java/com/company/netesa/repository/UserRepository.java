package com.company.netesa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.company.netesa.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
