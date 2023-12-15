package com.livec.auth.config;


import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;

import com.livec.auth.services.security.AppAuthProvider;
import com.livec.auth.services.security.UserAuthService;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

// tous les outils utilisés pour la sécu
@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	// Bean => rend accesible de partout l'instance
	@Bean
	public PasswordEncoder passwordEncoder() {
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
	

    @Autowired
    private AuthenticationFailureHandler authenticationFailureHandler() {
    	return new AuthenticationFailureHandler() {
			
			@Override
			public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
					AuthenticationException exception) throws IOException, ServletException {
				System.out.println("************** onAuthenticationFailure **********");
				
			}
		};
    }
    
	@Autowired
    private UserAuthService userDetailsService;
	
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }
	
	@Bean
	  public SecurityContextRepository securityContextRepository() {
	    return new HttpSessionSecurityContextRepository();
	  }

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		// filter le protocole HTTP en fonction des routes

		// on aime la parano actuelle ;)
		http.csrf(csrf -> csrf.disable());
		http.cors(cors -> cors.disable());
		
		// forcer le stateless
		 http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
		// tjs commencer apr les routes public
		// tjs finir par un anyRequest = toutes les autres routes
		http.authorizeHttpRequests((requests) ->
		requests.requestMatchers("/api/signin").permitAll() // public, obligatoire à définir en premier
						.requestMatchers("/api/signup").permitAll() // public, obligatoire à définir en premier
						.requestMatchers("/masuperbase").permitAll()
						//.requestMatchers("/api/users").permitAll()
						.anyRequest().authenticated());	// toutes les autres routes sont "privées"
		// si espace public et espace auth
		//.requestMatchers("/auth/**").authenticated() 	// si dans l'url j'ai /auth/ je dois être authentifié pour y accéder
		//.anyRequest().permitAll();

		// on aime la parano actuelle ;)
		http.csrf(csrf -> csrf.disable());
		http.cors(cors -> cors.disable());
		
		//http.formLogin(fl -> fl.loginPage("/api/signin").permitAll());
		
		// pour appliquer jwt
		http.addFilterBefore(new JWTTokenFilter(jwtTokenProvider()), UsernamePasswordAuthenticationFilter.class);
		return http.build();
	}
	
	
}
