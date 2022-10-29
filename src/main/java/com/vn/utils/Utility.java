package com.vn.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;

@Component
public class Utility {
    @Autowired
    private JavaMailSender mailSender;
    public static String getSiteURL(HttpServletRequest request) {
        String siteURL = request.getRequestURL().toString();
        return siteURL.replace(request.getServletPath(), "");
    }

    public void sendEmail(String recipientEmail, String link)
            throws MessagingException, UnsupportedEncodingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setFrom("hoangtienutc1507@gmail.com", "Rental Car Support");
        helper.setTo(recipientEmail);

        String subject = "Here's the link to reset your password";

        String content = "<p>Hello,</p>"
                + "<p>We have just received a password reset request for " + recipientEmail + "</p>"
                + "<p>Please click the link below to set your new password:</p>"
                + "<p><a href=\"" + link + "\">Set a new password</a></p>"
                + "<br>"
                + "<p>For your security, the link will expire in 24 hours or immediately after you reset your password.</p>";

        helper.setSubject(subject);

        helper.setText(content, true);

        mailSender.send(message);

        // We have just received a password reset request for <user’s email address>.
        //Please click here to reset your password.
        //For your security, the link will expire in 24 hours or immediately after you reset your password
    }
}
