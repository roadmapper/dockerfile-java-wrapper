package com.github.roadmapper.docker.instruction;

import java.util.List;
import java.util.Locale;

public class Add extends FileInstruction {
	
	private String name = Add.class.getSimpleName().toUpperCase(Locale.ENGLISH);
	
	public Add(String source, String destination) {
		super(source, destination);
	}
	
	public Add(List<String> sources, String destination) {
		super(sources, destination);
	}

	@Override
	public String getName() {
		return name;
	}

}
