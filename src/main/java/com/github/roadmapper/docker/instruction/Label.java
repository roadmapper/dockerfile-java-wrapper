package com.github.roadmapper.docker.instruction;

import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

public class Label extends Instruction {
	
	private String name = Label.class.getSimpleName().toUpperCase(Locale.ENGLISH);
	private Map<String, String> labels;

	@Override
	public String getName() {
		return name;
	}

	@Override
	public String getInstruction() {
		StringBuilder sb = new StringBuilder();
		sb.append(name).append(" ");
		String labelsStr = labels.entrySet().stream().map(entry -> entry.getKey() + "=\"" + entry.getValue() + "\"")
				.collect(Collectors.joining(" "));
		sb.append(labelsStr);
		return sb.toString();
	}
	
}
