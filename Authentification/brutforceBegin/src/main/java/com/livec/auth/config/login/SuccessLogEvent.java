package com.livec.auth.config.login;

import org.springframework.context.ApplicationEvent;

public class SuccessLogEvent extends ApplicationEvent{
	private static final long serialVersionUID = 4894561705569382935L;
	private String name;

	public SuccessLogEvent(Object source, String name) {
		super(source);
		this.name = name;
	}	
	
	public String getName() {
		return name;
	}
}
