package com.example.bookingReservation.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Long> {
	User findByUsername(String username);
	
	@Query("SELECT U1 FROM User as U1 WHERE U1.email = ?1")
	User findByEmail(String email); 
	
	User findByResetPasswordToken(String token); 
}
