package com.example.bookingReservation.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

import com.example.bookingReservation.domain.SignUpForm;
import com.example.bookingReservation.domain.User;
import com.example.bookingReservation.domain.UserRepository;

@Controller
public class UserController {

	@Autowired
	private UserRepository userRepository;

	@RequestMapping("signup")
	public String addUser(Model model) {
		model.addAttribute("signupform", new SignUpForm());
		return "signup";
	}
	
	 @RequestMapping(value = "saveuser", method = RequestMethod.POST)
	    public String save(@Valid @ModelAttribute("signupform") SignUpForm signupForm, BindingResult bindingResult) {
	    	System.out.println(bindingResult.toString());
	    	if (!bindingResult.hasErrors()) { // validation errors
	    		if (signupForm.getPassword().equals(signupForm.getPasswordCheck())) { // check password match		
		    		String pwd = signupForm.getPassword();
			    	BCryptPasswordEncoder bc = new BCryptPasswordEncoder();
			    	String hashPwd = bc.encode(pwd);

			    	User newUser = new User();
	                newUser.setPasswordHash(hashPwd);
	                newUser.setUsername(signupForm.getUsername());
	                newUser.setRole("USER");
	                newUser.setEmail(signupForm.getEmail());
	                if(userRepository.findByUsername(signupForm.getUsername()) == null){
	                	userRepository.save(newUser);
	                }  else {
	                    bindingResult.rejectValue("username", "err.username", "Username already exists!");
	                    return "signup";
	                }
	            }
	            else {
	                bindingResult.rejectValue("passwordCheck", "err.passCheck", "Password does not match!");
	                return "signup";
	            }
	        } else {
	            return "signup";
	        }
	        return "redirect:/login";
	    }
}
