package com.example.bookingReservation.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, updatable = false)
	private Long userid;

	// Username with unique constraint
	@Column(name = "username", nullable = false, unique = true)
	private String username;

	@Column(name = "password", nullable = false)
	private String passwordHash;

	@Column(name = "email", nullable = false, unique = true, length = 45)
	private String email;

	@Column(name = "role", nullable = false)
	private String role;
	
	@Column(name = "verificationCode", length = 64)
    private String verificationCode;
     
    private boolean enabled;

    @Column(name = "resetPasswordToken")
    private String resetPasswordToken; 
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
	private List<Student> students;

	public User() {
	}

	/*
	 * User entity class with parameters
	 * 
	 * @param username
	 * 
	 * @param passwordHash
	 * 
	 * @param role
	 * 
	 * @param email
	 */
	public User(String username, String passwordHash, String role, String email) {
		super();
		this.username = username; 
		this.passwordHash = passwordHash; 
		this.email = email; 
		this.role = role;

	}

	// Setters and getters
	public Long getUserid() {
		return userid;
	}

	public void setUserid(Long userid) {
		this.userid = userid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPasswordHash() {
		return passwordHash;
	}

	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getVerificationCode() {
		return verificationCode;
	}

	public void setVerificationCode(String verificationCode) {
		this.verificationCode = verificationCode;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}


	public List<Student> getStudents() {
		return students;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}

	public String getResetPasswordToken() {
		return resetPasswordToken;
	}

	public void setResetPasswordToken(String resetPasswordToken) {
		this.resetPasswordToken = resetPasswordToken;
	}


}
