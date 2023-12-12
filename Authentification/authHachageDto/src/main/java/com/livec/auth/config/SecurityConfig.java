package com.livec.auth.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.livec.auth.services.security.AppAuthProvider;

// tous les outils utilisés pour la sécu
@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	// Bean => rend accesible de partout l'instance
	@Bean
	public PasswordEncoder passwordEncoder() {
		//changer avec ScryptPasswordEncoder (slides 13)
		return new BCryptPasswordEncoder(10);
	}
	
	@Bean
	public AuthenticationManager authenticationManager() {
		return new AppAuthProvider();
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		// filter le protocole HTTP en fonction des routes
		// tjs commencer apr les routes public
		// tjs finir par un anyRequest = toutes les autres routes
		http.authorizeHttpRequests((requests) ->
		requests.requestMatchers("/api/signin").permitAll() // public, obligatoire à définir en premier
						.requestMatchers("/api/signup").permitAll() // public, obligatoire à définir en premier
						.requestMatchers("/masuperbase").permitAll()
						.requestMatchers("/api/users").permitAll()
						.anyRequest().authenticated());	// toutes les autres routes sont "privées"
		// si espace public et espace auth
		//.requestMatchers("/auth/**").authenticated() 	// si dans l'url j'ai /auth/ je dois être authentifié pour y accéder
		//.anyRequest().permitAll();

		// on aime la parano actuelle ;)
		http.csrf(csrf -> csrf.disable());
		http.cors(cors -> cors.disable());
		return http.build();
	}
}
