package com.example.bookingReservation.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Student {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String fullName; 
	private String gender; 
	private Integer studentNumber; 
	private String bookingTime; 
	
	@ManyToOne
	@JoinColumn(name = "roomid")
	private Room room; 
	
	public Student() {}
	
	public Student(String fullName, String gender, Integer studentNumber, String bookingTime, Room room) {
		super();
		this.fullName = fullName; 
		this.gender = gender; 
		this.studentNumber = studentNumber;
		this.bookingTime = bookingTime; 
		this.room = room; 
	}

	// setters and getters
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Integer getStudentNumber() {
		return studentNumber;
	}

	public void setStudentNumber(Integer studentNumber) {
		this.studentNumber = studentNumber;
	}

	public String getBookingTime() {
		return bookingTime;
	}

	public void setBookingTime(String bookingTime) {
		this.bookingTime = bookingTime;
	}

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}


	
	
}
