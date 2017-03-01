package com.github.roadmapper.docker.instruction;

import java.util.Locale;

/**
 * Arg instruction.
 * 
 * @author Vinay Dandekar
 * @see <a href="https://docs.docker.com/engine/reference/builder/#/arg">
 *      ARG</a>
 */
public class Arg extends Instruction {

	private String name = Arg.class.getSimpleName().toUpperCase(Locale.ENGLISH);
	private String argName;
	private String defaultValue;
	
	/**
	 * Creates an arg for the Docker container.
	 * @param argName the argument name
	 */
	public Arg(String argName) {
		this.argName = argName;
		this.defaultValue = null;
	}

	/**
	 * Creates an arg for the Docker container with a default value.
	 * @param argName the argument name
	 * @param defaultValue the default value for the argument
	 */
	public Arg(String argName, String defaultValue) {
		this.argName = argName;
		this.defaultValue = defaultValue;
	}

	@Override
	public String getName() {
		return name;
	}
	
	/**
	 * Get the argument variable name.
	 * @return the argument name
	 */
	public String getArgName() {
		return argName;
	}

	/**
	 * Set the argument variable name.
	 * @param argName the argument name
	 */
	public void setArgName(String argName) {
		this.argName = argName;
	}

	/**
	 * Get the argument default value.
	 * @return the default value
	 */
	public String getDefaultValue() {
		return defaultValue;
	}

	/**
	 * Set the argument default value.
	 * @param defaultValue the default value
	 */
	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}

	@Override
	public String getInstruction() {
		StringBuilder sb = new StringBuilder();
		sb.append(getName()).append(" ").append(argName);
		if (defaultValue != null) {
			sb.append("=").append(defaultValue);
		}
		return sb.toString();
	}

}
