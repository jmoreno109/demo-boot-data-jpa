package com.example.demo;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.demo.auth.filter.JWTAuthenticationFilter;
import com.example.demo.service.JpaUserDetailsService;

@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	DataSource dataSource;
	
	@Autowired
	JpaUserDetailsService jpaUserDetailsService;

	@Override
	@Autowired
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		auth.userDetailsService(jpaUserDetailsService).passwordEncoder(passwordEncoder());
		
		/*auth.jdbcAuthentication()
		.dataSource(dataSource)
		.passwordEncoder(passwordEncoder())
		.usersByUsernameQuery("select username, password, enabled from users where username = ?")
		.authoritiesByUsernameQuery("select u.username,a.authority from authorities a inner join users u on (a.user_id=u.id) where u.username = ?");
		*/
		
		/*auth.inMemoryAuthentication()
		.withUser("admin").password(passwordEncoder().encode("admin")).roles("ROLE_ADMIN")
		.and()
		.withUser("pepe").password(passwordEncoder().encode("pepe")).roles("ROLE_USER");
		*/
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		// With Sessions
		
		/*
		http.authorizeRequests()
		.antMatchers("/","/css/**","/js/**","/images/**").permitAll()
		//.antMatchers("/list").hasAnyRole("USER","ADMIN")
		//.antMatchers("/create/**").hasRole("ADMIN")
		//.antMatchers("/edit/**").hasRole("ADMIN")
		//.antMatchers("/delete/**").hasRole("ADMIN")
		.anyRequest().authenticated()
		.and()
		.formLogin().loginPage("/login").permitAll()
		.and()
		.logout().permitAll();
		*/
		
		
		// With JWT
		
		http.authorizeRequests()
		.antMatchers("/","/css/**","/js/**","/images/**").permitAll()
		.anyRequest().authenticated()
		.and()
		.addFilter(new JWTAuthenticationFilter(authenticationManager()))
		.csrf().disable()
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		
	}
	
	

}
