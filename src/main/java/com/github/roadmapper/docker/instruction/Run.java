package com.github.roadmapper.docker.instruction;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class Run extends Instruction {
	
	private String name = Run.class.getSimpleName().toUpperCase(Locale.ENGLISH);
	private String command;
	private String executable;
	private List<String> parameters;
	
	public Run(String command) {
		this.command = command;
		this.executable = null;
		this.parameters = null;
	}
	
	public Run(String executable, boolean multiCommand, String... params) {
		if (multiCommand) {
			this.command = String.join(" && ", params);
			this.executable = null;
			this.parameters = null;
		} else {
			this.command = null;
			this.executable = executable;
			this.parameters = Arrays.asList(params);
		}
	}
	
	@Override
	public String getName() {
		return name;
	}

	public String getCommand() {
		return command;
	}

	public void setCommand(String command) {
		this.command = command;
	}

	public String getExecutable() {
		return executable;
	}

	public void setExecutable(String executable) {
		this.executable = executable;
	}

	public List<String> getParameters() {
		return parameters;
	}

	public void setParameters(List<String> parameters) {
		this.parameters = parameters;
	}

	@Override
	public String getInstruction() {
		StringBuilder sb = new StringBuilder();
		sb.append(name).append(" ");
		if (command != null && executable == null) {
			sb.append(command);
		} else if (command == null && executable != null) {
			sb.append(executable).append(" ").append("[\"");
			sb.append(String.join("\", \"", parameters));
			sb.append("\"]");
		}
		return sb.toString();
	}
}
