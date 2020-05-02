package com.whiteroom.ani2gether;

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
                    		"/api/users/ban/**",
                    		"/api/users/delete/**").hasRole("admin")
                    .and()
        			.formLogin().permitAll()   
                
                .and().csrf().disable()
                ;
    }

     @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    	//in memory auth
 		UserBuilder users = User.withDefaultPasswordEncoder();
 	
 		auth.inMemoryAuthentication()
 			.withUser(users.username("admy").password("_admy_123").roles("admin"))
 			.withUser(users.username("IvanTea").password("GandalfTheWhite").roles("admin"));
    }
	
	/*@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		//in memory auth
		UserBuilder users = User.withDefaultPasswordEncoder();
	
		//auth.inMemoryAuthentication()
		//	.withUser(users.username("admin").password("admin").roles("admin"));
		
		
		// takes users from database
		auth.jdbcAuthentication()
			.dataSource(securityDataSource)
			// since we define custom tables, we should redefine queries that retrieve data, necessarry for Spring Security
			// it's username, password and account status of an user			
			.usersByUsernameQuery("select [username], [password], [enabled] from [user] where [username] = ?")
			// and username, authoriries of that user
			.authoritiesByUsernameQuery("select [username], authority from UserRole join [user] on [user].id = UserRole.[user_id] join authority on authority.id = UserRole.role_id where username = ?");
	
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.authorizeRequests()
				.antMatchers("/").permitAll()
				.antMatchers("/css/**").permitAll()
				.antMatchers("/js/**").permitAll()
				.antMatchers("/img/**").permitAll()
				.antMatchers("/icon-fonts/**").permitAll()
				.antMatchers("/**").permitAll()
			.and()
			.formLogin()
				.loginPage("/login")
				.loginProcessingUrl("/authentithication")
				.permitAll()
			.and()
			.logout().permitAll()
			
			.and()
            .logout().deleteCookies("JSESSIONID")

            .and()
            .rememberMe().key("uniqueAndSecret")
           
            
			.and()
			.exceptionHandling().accessDeniedPage("/access-denied")
			
			.and().csrf().disable();
	}*/
	
}
