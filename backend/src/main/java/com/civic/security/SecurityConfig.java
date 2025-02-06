package com.civic.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Configuration
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {
	//DIs here are: passwordEncoder, jwtAuthFilter, customAuthEntry
	
	@Autowired
	private PasswordEncoder passEnc;
	
	@Autowired 
	private JwtAuthFilter jwtFilter; 
	
	@Autowired
	private CustomAuthenticationEntryPoint authEntry;
	
	//config the security-filters' chain
	@Bean
	public SecurityFilterChain authorizeRequests(HttpSecurity http) throws Exception {
		//URL based rules - cors, csrf
		http
		.cors(cors -> cors.disable())
		.csrf(csrf -> csrf.disable())	
		.exceptionHandling(excep -> excep.authenticationEntryPoint(authEntry))
		.authorizeHttpRequests(
				auth -> auth.requestMatchers("/api/view","/api/auth/**","/api/requests/**","/api/events/**",
						"/v*/api-doc*/**","/swagger-ui/**").permitAll()
						.requestMatchers(HttpMethod.OPTIONS).permitAll()
						.requestMatchers("/api/admin/**").hasRole("ADMIN")
						.requestMatchers("/api/users/**").hasRole("CITIZEN")
						.anyRequest().authenticated())
		.sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
		.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
		return http.build();
		
		
	}
	
	//config authmgr as a bean
	@Bean
	public AuthenticationManager authenticationManger(AuthenticationConfiguration config) throws Exception{
		return config.getAuthenticationManager();
	}
}
