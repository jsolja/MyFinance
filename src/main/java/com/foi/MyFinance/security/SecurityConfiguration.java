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

@EnableWebSecurity
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter
{
    private static final String URL_LOGIN = "/login";
    private static final String URL_USER_SECURITY = "/user/**";
    private static final String URL_HOME = "/user/home";
    private static final String URL_LOGOUT = "/logout";
    private static final String URL_LOGOUT_SUCCESS = "/login?logout";

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void configure(final AuthenticationManagerBuilder auth) throws Exception
    {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(final HttpSecurity http) throws Exception
    {
        http.cors().and().csrf().disable();
        http//
            .authorizeRequests()//
            .antMatchers(URL_USER_SECURITY)//
            .authenticated()//
            .anyRequest()//
            .permitAll()//
            .and()//
            .formLogin()//
            .loginPage(URL_LOGIN)//
            .defaultSuccessUrl(URL_HOME)//
            .permitAll()//
            .and()//
            .logout()//
            .logoutUrl(URL_LOGOUT)//
            .logoutSuccessUrl(URL_LOGOUT_SUCCESS);
    }

    @Bean
    public PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }
}
