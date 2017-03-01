package com.github.roadmapper.docker.instruction;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

/**
 * Cmd instruction.
 * 
 * @author Vinay Dandekar
 * @see <a href="https://docs.docker.com/engine/reference/builder/#/from">CMD
 *      </a>
 */
public class Cmd extends Instruction {

	private String name = Cmd.class.getSimpleName().toUpperCase(Locale.ENGLISH);
	private String command;
	private String executable;
	private List<String> parameters;

	/**
	 * Creates a cmd instruction.
	 * 
	 * @param type
	 *            type of command (shell or exec)
	 * @param cmdExec
	 *            the command or executable to run
	 * @param parameters
	 *            the parameters to run with the command/executable
	 */
	public Cmd(CommandType type, String cmdExec, String... parameters) {
		switch (type) {
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

	/**
	 * Creates a cmd instruction for an entrypoint.
	 * 
	 * @param parameters
	 *            the parameters to run with the command/executable
	 */
	public Cmd(String... parameters) {
		this.command = null;
		this.executable = null;
		this.parameters = Arrays.asList(parameters);
	}

	@Override
	public String getName() {
		return name;
	}

	/**
	 * Get the command to run.
	 * 
	 * @return the command
	 */
	public String getCommand() {
		return command;
	}

	/**
	 * Set the command to run.
	 * 
	 * @param command
	 *            the command to run
	 */
	public void setCommand(String command) {
		this.command = command;
	}

	/**
	 * Get the executable to run.
	 * 
	 * @return the executable
	 */
	public String getExecutable() {
		return executable;
	}

	/**
	 * Set the executable to run.
	 * 
	 * @param executable
	 *            the executable to run
	 */
	public void setExecutable(String executable) {
		this.executable = executable;
	}

	/**
	 * Get list of parameters.
	 * 
	 * @return the list of parameters
	 */
	public List<String> getParameters() {
		return parameters;
	}

	/**
	 * Set list of parameters to run.
	 * 
	 * @param parameters
	 *            the list of parameters
	 */
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
