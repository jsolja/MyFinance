package com.foi.MyFinance.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@EnableWebSecurity
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter implements WebMvcConfigurer
{
    private static final String LOGIN_PAGE = "/login";
    private static final String HOME_PAGE = "/home";
    private static final String LOGOUT_PAGE = "/logout";
    private static final String LOGOUT_SUCCESS_PAGE = "/login?logout";

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void configure(final AuthenticationManagerBuilder auth) throws Exception
    {
        auth.userDetailsService(userDetailsService).passwordEncoder(this.passwordEncoder());
    }

    @Override
    protected void configure(final HttpSecurity http) throws Exception
    {
        http//
            .authorizeRequests()//
            .anyRequest()//
            .authenticated()//
            .and()//
            .formLogin()//
            .loginPage(LOGIN_PAGE)//
            .defaultSuccessUrl(HOME_PAGE)//
            .permitAll()//
            .and()//
            .logout()//
            .logoutUrl(LOGOUT_PAGE)//
            .logoutSuccessUrl(LOGOUT_SUCCESS_PAGE)//
            .permitAll();
    }

    @Bean
    public PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }
}
