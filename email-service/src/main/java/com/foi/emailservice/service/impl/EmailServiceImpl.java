package com.foi.emailservice.service.impl;

import com.foi.emailservice.entity.UserEntity;
import com.foi.emailservice.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
public class EmailServiceImpl implements EmailService
{
    private static final String COLON = ":";
    private static final String SLASH = "/";
    private static final String QUESTION_MARK = "?";
    private static final String TOKEN = "token";
    private static final String EQUALS = "=";
    private static final String URL_RESET_PASSWORD = "/reset-password";
    private static final String URL_VERIFY_ACCOUNT = "/verify-account";

    @Value("${spring.mail.properties.from}")
    private String fromEmail;

    @Value("${spring.mail.properties.subject.password.reset}")
    private String emailPasswordResetSubject;

    @Value("${spring.mail.properties.text.password.reset}")
    private String emailTextPasswordReset;

    @Value("${spring.mail.properties.subject.user.activation}")
    private String emailActivationSubject;

    @Value("${spring.mail.properties.text.user.activation}")
    private String emailActivationText;

    @Autowired
    private JavaMailSender javaMailSender;

    @Async
    @Override
    public boolean sendForgottenPasswordEmail(
            final UserEntity userEntity, final HttpServletRequest request)
    {
        //        userService.createToken(userEntity); //prebaciti u user service
        final String appUrl = request.getScheme() + COLON + SLASH + SLASH + request.getServerName() + COLON + request
                .getServerPort() + request.getContextPath();
        final SimpleMailMessage passwordResetEmail = new SimpleMailMessage();
        passwordResetEmail.setFrom(fromEmail);
        passwordResetEmail.setTo(userEntity.getEmail());
        passwordResetEmail.setSubject(emailPasswordResetSubject);
        passwordResetEmail.setText(emailTextPasswordReset + appUrl + URL_RESET_PASSWORD + QUESTION_MARK + TOKEN + EQUALS + userEntity
                .getToken());
        try
        {
            javaMailSender.send(passwordResetEmail);
            return true;
        }
        catch (MailException ex)
        {
            return false;
        }

    }

    @Override
    public boolean sendActivationEmail(final UserEntity userEntity, final HttpServletRequest request)
    {
        //        userService.createToken(userEntity); //prebaciti u user service
        final String appUrl = request.getScheme() + COLON + SLASH + SLASH + request.getServerName() + COLON + request
                .getServerPort() + request.getContextPath();
        final SimpleMailMessage activationEmail = new SimpleMailMessage();
        activationEmail.setFrom(fromEmail);
        activationEmail.setTo(userEntity.getEmail());
        activationEmail.setSubject(emailActivationSubject);
        activationEmail.setText(emailActivationText + appUrl + URL_VERIFY_ACCOUNT + QUESTION_MARK + TOKEN + EQUALS + userEntity
                .getToken());
        try
        {
            javaMailSender.send(activationEmail);
            return true;
        }
        catch (MailException ex)
        {
            return false;
        }

    }
}
