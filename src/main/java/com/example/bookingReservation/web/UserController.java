package com.example.bookingReservation.web;

import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.bookingReservation.domain.SignUpForm;
import com.example.bookingReservation.domain.User;
import com.example.bookingReservation.domain.UserRepository;
import com.example.bookingReservation.service.UserServices;

import net.bytebuddy.utility.RandomString;

@Controller
@Component
public class UserController {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserServices userService;

//	@Autowired
//    private JavaMailSender mailSender;

	@RequestMapping("signup")
	public String addUser(Model model) {
		model.addAttribute("signupform", new SignUpForm());
		return "signup";
	}

	@RequestMapping(value = "saveuser", method = RequestMethod.POST)
	public String save(@Valid @ModelAttribute("signupform") SignUpForm signupForm, BindingResult bindingResult,
			HttpServletRequest request) throws UnsupportedEncodingException, MessagingException {

		String randomCode = RandomString.make(64);
		String siteURL = request.getRequestURL().toString().replace(request.getServletPath(), "");
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
				newUser.setVerificationCode(randomCode);
				newUser.setEnabled(false);
				if (userRepository.findByUsername(signupForm.getUsername()) == null) {
					userRepository.save(newUser);
					sendVerificationEmail(newUser, siteURL);
				} else {
					bindingResult.rejectValue("username", "err.username", "Username already exists!");
					return "signup";
				}

			} else {
				bindingResult.rejectValue("passwordCheck", "err.passCheck",
						"Password does not match! Please enter again");
				return "signup";
			}
		} else {
			return "signup";
		}
		return "redirect:/login";
	}

	private void sendVerificationEmail(User user, String siteURL)
			throws MessagingException, UnsupportedEncodingException {
		String toAddress = user.getEmail();
		String fromAddress = "Library";
		String senderName = "Library Server";
		String subject = "Please verify your registration";
		String content = "Dear [[name]],<br>" + "Please click the link below to verify your registration:<br>"
				+ "<h3><a href=\"[[URL]]\" target=\"_self\">VERIFY</a></h3>" + "Thank you,<br>" + "Library Server.";

		JavaMailSender mailSender = this.getJavaMailSender();

		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);

		helper.setFrom(fromAddress, senderName);
		helper.setTo(toAddress);
		helper.setSubject(subject);

		content = content.replace("[[name]]", user.getUsername());
		String verifyURL = siteURL + "/verify?code=" + user.getVerificationCode();

		content = content.replace("[[URL]]", verifyURL);

		helper.setText(content, true);

		mailSender.send(message);

	}

	
	public JavaMailSender getJavaMailSender() {
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		mailSender.setHost("smtp.gmail.com");
		mailSender.setPort(587);

		mailSender.setUsername("libraray2021@gmail.com");
		mailSender.setPassword("server123456");

		Properties props = mailSender.getJavaMailProperties();
		props.put("mail.transport.protocol", "smtp");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.debug", "true");

		return mailSender;
	}

	@GetMapping("/verify")
	public String verifyUser(@Param("code") String code) {
		if (userService.verify(code)) {
			return "verifySuccess";
		} else {
			return "verifyFail";
		}

	}
}
