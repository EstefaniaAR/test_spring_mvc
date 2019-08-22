package com.example.test_thymeleaf;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.test_thymeleaf.auth.handler.SuccessHandler;
import com.example.test_thymeleaf.service.UserService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled=true, prePostEnabled=true)
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter
{
	@Autowired
	private SuccessHandler successHandler;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	DataSource dataSource;
	
	@Autowired
	UserService userService;
	
	@Autowired
	public void configurerGlobal(AuthenticationManagerBuilder builder) throws Exception 
	{
		builder.userDetailsService(userService)
		.passwordEncoder(passwordEncoder)
		;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception 
	{
		http.authorizeRequests()
		.antMatchers("/","/greet","/css/**","/js/**","/img/**")
		.permitAll()
		//.antMatchers("/form/**").hasAnyRole("ADMIN")
		//.antMatchers("/delete/**").hasAnyRole("ADMIN")
		//.antMatchers("/erase/**").hasAnyRole("ADMIN")
		//.antMatchers("/bill/**").hasAnyRole("ADMIN")
		.anyRequest()
		.authenticated()
		.and()
		.formLogin().successHandler(successHandler).loginPage("/login").permitAll()
		.and()
		.logout().permitAll()
		.and()
		.exceptionHandling()
		.accessDeniedPage("/error_403");
		
	}

}
