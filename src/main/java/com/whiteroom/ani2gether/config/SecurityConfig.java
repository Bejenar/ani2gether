package com.whiteroom.ani2gether.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Value("${admin.login}")
    private String login;

    @Value("${admin.password}")
    private String password;
	
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                    .antMatchers(
                            "/registration",
                            "/icon-fonts/**",
                            "/js/**",
                            "/css/**",
                            "/img/**",
                            "/webjars/**",
                            "/",
                            "/room").permitAll()
                    .antMatchers(
                    		"/api/users/ban/**").hasRole("admin")
                    .and()
        			.formLogin().permitAll()   
                
                .and().csrf().disable()
                ;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

 		UserBuilder users = User.withDefaultPasswordEncoder();
 	
 		auth.inMemoryAuthentication()
 			.withUser(users.username(login).password(password).roles("admin"));
    }

	
}
