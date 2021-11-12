package com.example.bookingReservation.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.bookingReservation.domain.User;
import com.example.bookingReservation.domain.UserRepository;

@Service
@Transactional
public class UserServices implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	// Set value field for the resetPasswordToken of user found by email/ username
	// and persist change to the database
	public void updateResetPasswordToken(String token, String email) throws UserNotFoundException {
		User currentUser = userRepository.findByEmail(email);
		if (currentUser != null) {
			currentUser.setResetPasswordToken(token);
			userRepository.save(currentUser);
		} else {
			throw new UserNotFoundException("Could not find any user with the email " + email);
		}
	}

	// Find a user by the given reset password token
	public User getByResetPasswordToken(String token) {
		return userRepository.findByResetPasswordToken(token);
	}

	// Set new password for the user
	public void updatePassword(User user, String newPassword) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String encodedPassword = passwordEncoder.encode(newPassword);
		user.setPasswordHash(encodedPassword);

		user.setResetPasswordToken(null);
		userRepository.save(user);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}
}
