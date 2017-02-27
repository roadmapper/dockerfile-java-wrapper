package com.github.roadmapper.docker.instruction;

import java.util.Locale;

public class Entrypoint extends Cmd {
	private String name = Entrypoint.class.getSimpleName().toUpperCase(Locale.ENGLISH);
	
	public Entrypoint(CommandType type, String cmdExec, String... parameters) {
		super(type, cmdExec, parameters);
	}
	
	@Override
	public String getName() {
		return name;
	}
}
