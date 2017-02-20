package io.github.roadmapper.docker.instruction;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class Cmd extends Instruction {

	private String name = Cmd.class.getSimpleName().toUpperCase(Locale.ENGLISH);
	private String command;
	private String executable;
	private List<String> parameters;

	public Cmd(CommandType type, String cmdExec, String... parameters) {
		switch(type) {
			case EXEC:
				this.command = null;
				this.executable = cmdExec;
				break;
			case SHELL:
				this.command = cmdExec;
				this.executable = null;
				break;
		}
		this.parameters = Arrays.asList(parameters);
	}

	public Cmd(String... parameters) {
		this.command = null;
		this.executable = null;
		this.parameters = Arrays.asList(parameters);
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
		sb.append(getName()).append(" ");
		if (command != null && executable == null) {
			sb.append(command).append(" ");
			sb.append(String.join(" ", parameters));
		} else if (command == null && executable != null) {
			sb.append("[\"").append(executable).append("\", \"");
			sb.append(String.join("\", \"", parameters));
			sb.append("\"]");
		} else {
			sb.append("[\"");
			sb.append(String.join("\", \"", parameters));
			sb.append("\"]");
		}
		return sb.toString();
	}

}
