package com.example.bookingReservation;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.bookingReservation.domain.User;
import com.example.bookingReservation.domain.UserRepository;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class UserRepositoryTest {

	 @Autowired
	    private UserRepository userRepository;
	 
	 @Test 
	 public void findByUserNameShouldReturnUser() {
		 User users = userRepository.findByUsername("user"); 
		 assertThat(users.getRole()).isEqualTo("USER"); 
	 }
	 
	 @Test
	    public void addNewUser() {
	    	User user = new User();
	    	user.setUsername("anhcao");
	    	user.setPasswordHash("server2021");
	    	user.setRole("testingRole");
	    	user.setEmail("anhcao563@gmail.com");
	    	userRepository.save(user);
	    	assertThat(user.getId()).isNotNull();
	    }
	 
}
