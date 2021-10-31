package com.example.bookingReservation.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.bookingReservation.domain.RoomRepository;
import com.example.bookingReservation.domain.Student;
import com.example.bookingReservation.domain.StudentRepository;


@Controller
public class WebController {

	@Autowired 
	private StudentRepository studentRepository; 
	
	@Autowired
	private RoomRepository roomRepository; 
	
	//Login method
	// Prevent user from going back to login page if he/she already logged in by their username 
		@RequestMapping(value = "/login")
		public String login(Model model) {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
	            return "login";
	        }
	 
	        return "redirect:/";
	    
		}
	// Rest service that returns all students who book reservation 
		@RequestMapping(value = "/students", method = RequestMethod.GET)
		public @ResponseBody List<Student> studentListRest(){
			return (List<Student>) studentRepository.findAll(); 
		}
		
	// Rest service that returns student who book his/her reservation by id
		@RequestMapping(value = "/students/{id}", method = RequestMethod.GET)
		public @ResponseBody Optional<Student> findStudentRest(@PathVariable("id")Long studentId){
			return studentRepository.findById(studentId);  
		}
		
	// Show all reservation
	@RequestMapping(value = {"/", "/reservationlist"})
	public String reservationList(Model model) {
		model.addAttribute("students", studentRepository.findAll());
		return "reservationlist"; 
	}
	
	// Add new reservation
	@RequestMapping(value = "/add")
	public String addReservation(Model model) {
		model.addAttribute("student", new Student());
		model.addAttribute("rooms", roomRepository.findAll()); 
		return "addreservation"; 
	}
	
	// Save the reservation
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String saveReservation(Student student) {
		studentRepository.save(student);
		return "redirect:reservationlist"; 
	}
	
	// Delete reservation 
	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public String deleteReservation(@PathVariable("id") Long studentId, Model model) {
		studentRepository.deleteById(studentId);
		return "redirect:../reservationlist";
	}
	
	// Edit reservation 
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String editReservation(@PathVariable("id") Long studentId, Model model) {
		model.addAttribute("student", studentRepository.findById(studentId));
		model.addAttribute("rooms", roomRepository.findAll()); 
		return "editreservation";
	}
}
