package com.example.bookingReservation.web;

import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.bookingReservation.domain.User;
import com.example.bookingReservation.service.UserNotFoundException;
import com.example.bookingReservation.service.UserServices;

import net.bytebuddy.utility.RandomString;

@Controller
@Component
public class ForgotPasswordController {

	@Autowired
    private JavaMailSender mailSender;
     
    @Autowired
    private UserServices userServices;
     
    @GetMapping("/forgotPassword")
    public String showForgotPasswordForm() {
    	return "forgotPasswordForm"; 
 
    }
 
    @PostMapping("/forgotPassword")
    public String processForgotPassword(HttpServletRequest request, Model model) {
        String email = request.getParameter("email");
        String token = RandomString.make(30);
         
        try {
        	userServices.updateResetPasswordToken(token, email);
            String resetPasswordLink = Utility.getSiteURL(request) + "/resetPassword?token=" + token;
            sendEmail(email, resetPasswordLink);
            model.addAttribute("message", "We have sent a reset password link to your email. Please check.");
             
        } catch (UserNotFoundException ex) {
            model.addAttribute("error", ex.getMessage());
        } catch (UnsupportedEncodingException | MessagingException e) {
            model.addAttribute("error", "Error while sending email");
        }
             
        return "forgotPasswordForm";
    }
     
    public void sendEmail(String recipientEmail, String link)
            throws MessagingException, UnsupportedEncodingException {
        MimeMessage message = mailSender.createMimeMessage();              
        MimeMessageHelper helper = new MimeMessageHelper(message);
         
        helper.setFrom("contact@library.com", "Library Support Team");
        helper.setTo(recipientEmail);
         
        String subject = "Here's the link to reset your password";
         
        String content = "<p>Hello,</p>"
                + "<p>You have requested to reset your password.</p>"
                + "<p>Click the link below to change your password:</p>"
                + "<p><a href=\"" + link + "\">Change my password</a></p>"
                + "<br>"
                + "<p>Ignore this email if you do remember your password, "
                + "or you have not made the request.</p>";
         
        helper.setSubject(subject);
         
        helper.setText(content, true);
         
        mailSender.send(message);
    }
     
     
    @GetMapping("/resetPassword")
    public String showResetPasswordForm(@Param(value = "token") String token, Model model) {
        User user = userServices.getByResetPasswordToken(token);
        model.addAttribute("token", token);
         
        if (user == null) {
            model.addAttribute("message", "Invalid Token");
            return "message";
        }
         
        return "resetPasswordForm";
    }
    
     
    @PostMapping("/resetPassword")
    public String processResetPassword(HttpServletRequest request, Model model) {
        String token = request.getParameter("token");
        String password = request.getParameter("password");
         
        User user = userServices.getByResetPasswordToken(token);
        model.addAttribute("title", "Reset your password");
         
        if (user == null) {
            model.addAttribute("message", "Invalid Token");
            return "message";
        } else {           
        	userServices.updatePassword(user, password);
             
            model.addAttribute("message", "You have successfully changed your password.");
        }
         
        return "message";
    }
}

 class Utility {
    public static String getSiteURL(HttpServletRequest request) {
        String siteURL = request.getRequestURL().toString();
        return siteURL.replace(request.getServletPath(), "");
    }
}