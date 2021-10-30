package com.example.bookingReservation.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Room {
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
	private Long roomid;
	
	 @Column(name="name")
	private String name; 
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "room")
	private List<Student> students;
	
	public Room() {}
	
	/* Room entity with an unique name*/
	public Room(String name) {
		this.name = name; 
	}

	// setters and getters 
	public Long getRoomid() {
		return roomid;
	}

	public void setRoomid(Long roomid) {
		this.roomid = roomid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Student> getStudents() {
		return students;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}
	
}
