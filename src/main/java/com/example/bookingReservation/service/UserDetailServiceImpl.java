package com.example.bookingReservation.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.bookingReservation.domain.User;
import com.example.bookingReservation.domain.UserRepository;

/*
 * Spring Security is used to authenticate and authorize user
 * */
@Service
public class UserDetailServiceImpl implements UserDetailsService {
	
	@Autowired
	private UserRepository userRepository;

	@Autowired
	public UserDetailServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {   
    	User curruser = userRepository.findByUsername(username);
        UserDetails user = new org.springframework.security.core.userdetails.User(username, curruser.getPasswordHash(), 
        		AuthorityUtils.createAuthorityList(curruser.getRole()));
        return user;
    }   
}
