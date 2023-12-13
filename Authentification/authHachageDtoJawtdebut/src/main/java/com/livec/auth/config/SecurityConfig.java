package com.livec.auth.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;
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
		//return new BCryptPasswordEncoder(10);
		int memoryCost = 8; // increases memory usage i
		int parallelization = 1; // currently not supported by Spring Security
		int keyLength = 32; // key length in bytes int saltLength = 64; // salt length in bytes
		SCryptPasswordEncoder sCryptPasswordEncoder = new SCryptPasswordEncoder( (int) 65536 , memoryCost, parallelization, keyLength, 16);
		return sCryptPasswordEncoder;
	}
	
	@Bean
	public AuthenticationManager authenticationManager() {
		return new AppAuthProvider();
	}
	
	@Bean
	public JWTTokenProvider jwtTokenProvider() {
		return new JWTTokenProvider();
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		// filter le protocole HTTP en fonction des routes
		
		// forcer le stateless
		http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
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
		
		// pour appliquer jwt
		http.addFilterBefore(new JWTTokenFilterConfiguration(jwtTokenProvider), JWTTokenFilterConfiguration.class);
		return http.build();
	}
}
