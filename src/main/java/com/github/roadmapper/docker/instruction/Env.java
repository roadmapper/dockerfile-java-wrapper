package com.github.roadmapper.docker.instruction;

import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Env instruction.
 * 
 * @author Vinay Dandekar
 * @see <a href="https://docs.docker.com/engine/reference/builder/#/from">ENV
 *      </a>
 */
public class Env extends Instruction {
	private String name = Env.class.getSimpleName().toUpperCase(Locale.ENGLISH);
	private Map<String, String> variables;

	/**
	 * Creates an env instruction.
	 * 
	 * @param variables
	 *            the map of environment variables
	 */
	public Env(Map<String, String> variables) {
		this.variables = variables;
	}

	@Override
	public String getName() {
		return name;
	}

	/**
	 * Get the map of environment variables.
	 * 
	 * @return the map of environment variables
	 */
	public Map<String, String> getVariables() {
		return variables;
	}

	/**
	 * Set the map of environment variables.
	 * 
	 * @param variables
	 *            the map of environment variables
	 */
	public void setVariables(Map<String, String> variables) {
		this.variables = variables;
	}

	@Override
	public String getInstruction() {
		StringBuilder sb = new StringBuilder();
		sb.append(name).append(" ");
		String varsStr = variables.entrySet().stream()
				.map(entry -> entry.getKey() + "=\"" + entry.getValue() + "\"")
				.collect(Collectors.joining(" "));
		sb.append(varsStr);
		return sb.toString();
	}

}
