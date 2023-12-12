package com.livec.auth.config;

import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.livec.auth.services.security.AppAuthProvider;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(10);
	}
	
	@Bean
	public AuthenticationManager authenticationManager() {
		return new AppAuthProvider();
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {		
		http.authorizeHttpRequests((requests) ->
			requests.requestMatchers("/api/signup").permitAll() );
		//requests.requestMatchers("/api/signin").permitAll() // public, obligatoire à définir en premier
		//				.requestMatchers("/api/signup").permitAll() // public, obligatoire à définir en premier
		//				.requestMatchers("/masuperbase").permitAll()
		//				.anyRequest().authenticated());
		// si espace public et espace auth
		//.requestMatchers("/auth/**").authenticated() 	// si dans l'url j'ai /auth/ je dois être authentifié pour y accéder
		//.requestMatchers().permitAll();

		// on aime la parano actuelle ;)
		http.cors().and().csrf().disable();
		return http.build();
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/api/signup").allowedOrigins("*");
			}
		};
	}
}
