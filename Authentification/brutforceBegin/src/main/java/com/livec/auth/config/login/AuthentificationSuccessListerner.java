package com.livec.auth.config.login;
import jakarta.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.stereotype.Component;


@Component
public class AuthentificationSuccessListerner implements 
  ApplicationListener<SuccessLogEvent> {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private LoginAttemptService loginAttemptService;

    @Override
    public void onApplicationEvent(SuccessLogEvent e) {
    	System.out.println("****************** success *******************");

    }
}