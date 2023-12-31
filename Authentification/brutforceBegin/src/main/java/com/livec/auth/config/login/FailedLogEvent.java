package com.livec.auth.config.login;

import org.springframework.context.ApplicationEvent;

public class FailedLogEvent extends ApplicationEvent{
	
	private static final long serialVersionUID = 3396974156470324088L;
	private String name;

	public FailedLogEvent(Object source, String name) {
		super(source);
		this.name = name;
	}	
	
	public String getName() {
		return name;
	}
}
