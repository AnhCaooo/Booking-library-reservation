package com.example.bookingReservation.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Student {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String fullName; 
	private String gender; 
	private Integer studentNumber; 
	private String startTime;
	private String endTime; 
	private Date date; 
	
	@DateTimeFormat (pattern="yyyy-MM-dd") 
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "roomid")
	private Room room; 
	
	
	public Student() {}
	
	public Student(String fullName, String gender, Integer studentNumber, String startTime, String endTime, Date date, Room room) {
		super();
		this.fullName = fullName; 
		this.gender = gender; 
		this.studentNumber = studentNumber;
		this.startTime = startTime; 
		this.endTime = endTime; 
		this.date = date; 
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


	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}


	
	
}
