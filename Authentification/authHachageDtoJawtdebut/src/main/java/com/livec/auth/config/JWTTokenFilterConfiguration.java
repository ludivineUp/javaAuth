package com.livec.auth.config;

import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;

public class JWTTokenFilterConfiguration extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity>{

}
