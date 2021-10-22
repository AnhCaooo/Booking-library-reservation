package com.example.bookingReservation.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface RoomRepository extends CrudRepository<Room, Long> {
	List<Room> findRoomByName(String roomName); 
}
