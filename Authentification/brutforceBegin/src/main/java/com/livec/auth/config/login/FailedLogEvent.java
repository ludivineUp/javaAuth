package com.livec.auth.config.login;

import org.springframework.context.ApplicationEvent;

public class FailedLogEvent extends ApplicationEvent{
	
	private String name;

	public FailedLogEvent(Object source, String name) {
		super(source);
		this.name = name;
	}	
	
	public String getName() {
		return name;
	}
}
