package com.foi.MyFinance.service.impl;

import com.foi.MyFinance.entity.UserEntity;
import com.foi.MyFinance.service.EmailService;
import com.foi.MyFinance.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

    @Autowired
    private UserService userService;

    @Async
    @Override
    public void sendForgottenPasswordEmail(
            final UserEntity userEntity, final HttpServletRequest request)
    {
        userService.createToken(userEntity);
        final String appUrl = request.getScheme() + COLON + SLASH + SLASH + request.getServerName() + COLON + request
                .getServerPort() + request.getContextPath();
        final SimpleMailMessage passwordResetEmail = new SimpleMailMessage();
        passwordResetEmail.setFrom(fromEmail);
        passwordResetEmail.setTo(userEntity.getEmail());
        passwordResetEmail.setSubject(emailPasswordResetSubject);
        passwordResetEmail.setText(emailTextPasswordReset + appUrl + URL_RESET_PASSWORD + QUESTION_MARK + TOKEN + EQUALS + userEntity
                .getToken());
        javaMailSender.send(passwordResetEmail);
    }

    @Override
    public void sendActivationEmail(final UserEntity userEntity, final HttpServletRequest request)
    {
        userService.createToken(userEntity);
        final String appUrl = request.getScheme() + COLON + SLASH + SLASH + request.getServerName() + COLON + request
                .getServerPort() + request.getContextPath();
        final SimpleMailMessage activationEmail = new SimpleMailMessage();
        activationEmail.setFrom(fromEmail);
        activationEmail.setTo(userEntity.getEmail());
        activationEmail.setSubject(emailActivationSubject);
        activationEmail.setText(emailActivationText + appUrl + URL_VERIFY_ACCOUNT + QUESTION_MARK + TOKEN + EQUALS + userEntity
                .getToken());
        javaMailSender.send(activationEmail);
    }
}
