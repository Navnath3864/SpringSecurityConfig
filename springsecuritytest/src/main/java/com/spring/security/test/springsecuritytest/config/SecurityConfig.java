package com.spring.security.test.springsecuritytest.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public UserDetailsService userDetailsService() {
		UserDetails normalUser = User.withUsername("Nav").password(passwordEncoder().encode("Pass")).roles("NORMAL")
				.build();
		UserDetails adminUser = User.withUsername("Navnath").password(passwordEncoder().encode("Password"))
				.roles("ADMIN").build();
		return new InMemoryUserDetailsManager(normalUser, adminUser);
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
		httpSecurity
		 .authorizeHttpRequests((authz) -> authz
	                .requestMatchers("/home/admin").hasRole("ADMIN")
	                .requestMatchers("/api/normal").hasRole("NORMAL")
	                .requestMatchers("home/public").permitAll()
	                .anyRequest().authenticated()
	            ).formLogin();
		
		return httpSecurity.build();

	}

}
