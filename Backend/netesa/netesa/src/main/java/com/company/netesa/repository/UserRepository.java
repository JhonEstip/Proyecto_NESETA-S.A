package com.company.netesa.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.company.netesa.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	List<User> findUserById(Long id);
	
	@Query(value = "SELECT * FROM usuario u WHERE u.email = :email", nativeQuery=true)
    public List<User> findUserByEmail(String email);
	
	public Optional<User> findByEmail(String email);
	
}
