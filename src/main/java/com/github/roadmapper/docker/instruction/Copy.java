package com.github.roadmapper.docker.instruction;

import java.util.List;
import java.util.Locale;

public class Copy extends FileInstruction {
	
	private String name = Copy.class.getSimpleName().toUpperCase(Locale.ENGLISH);
	
	public Copy(String source, String destination) {
		super(source, destination);
	}
	
	public Copy(List<String> sources, String destination) {
		super(sources, destination);
	}

	@Override
	public String getName() {
		return name;
	}

}
