package io.github.roadmapper.docker.instruction;

import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

public class Env extends Instruction {
	private String name = Env.class.getSimpleName().toUpperCase(Locale.ENGLISH);
	private Map<String, String> variables;

	@Override
	public String getName() {
		return name;
	}

	@Override
	public String getInstruction() {
		StringBuilder sb = new StringBuilder();
		sb.append(name).append(" ");
		String varsStr = variables.entrySet().stream().map(entry -> entry.getKey() + "=\"" + entry.getValue() + "\"")
				.collect(Collectors.joining(" "));
		sb.append(varsStr);
		return sb.toString();
	}

}
