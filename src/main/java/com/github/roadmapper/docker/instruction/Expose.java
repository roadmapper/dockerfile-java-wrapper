package com.github.roadmapper.docker.instruction;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class Expose extends Instruction {

	private String name = Expose.class.getSimpleName().toUpperCase(Locale.ENGLISH);
	private List<Integer> ports;
	
	public Expose(Integer... ports) {
		this.ports = Arrays.asList(ports);
	}
	
	@Override
	public String getName() {
		return name;
	}

	@Override
	public String getInstruction() {
		StringBuilder sb = new StringBuilder();
		sb.append(name).append(" ");
		sb.append(ports.stream().map(port -> port.toString()).collect(Collectors.joining(" ")));
		return sb.toString();
	}

}
