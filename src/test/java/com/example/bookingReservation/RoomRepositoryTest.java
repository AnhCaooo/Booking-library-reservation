package com.example.bookingReservation;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.bookingReservation.domain.Room;
import com.example.bookingReservation.domain.RoomRepository;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class RoomRepositoryTest {

	@Autowired
	private RoomRepository roomRepository;

	@Test
	public void findByNameShouldReturnRoomType() {
		List<Room> room = roomRepository.findRoomByName("Individual room"); 
		assertThat(room).hasSize(1); 
		assertThat(room.get(0).getRoomid()).isNotNull();
	}
	
	// Test delete room type
	@Test 
	public void deleteRoom() {
		List<Room> roomType = roomRepository.findRoomByName("5-14 persons room");
		assertThat(roomType).hasSize(1);
		roomRepository.deleteById((long) 3);
		roomType = roomRepository.findRoomByName("5-14 persons room");
		assertThat(roomType).hasSize(0);
	}
}
