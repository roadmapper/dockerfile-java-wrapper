package com.github.roadmapper.docker.instruction;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import com.github.roadmapper.docker.DockerVersion;

/**
 * Shell instruction. Requires Docker 1.12 or above.
 * 
 * @author Vinay Dandekar
 * @see <a href="https://docs.docker.com/engine/reference/builder/#shell">SHELL
 *      </a>
 */
@DockerVersion("1.12.0")
public class Shell extends Instruction {

	private String name = Shell.class.getSimpleName().toUpperCase(Locale.ENGLISH);
	private String executable;
	private List<String> parameters;

	/**
	 * Creates a shell instruction.
	 * 
	 * @param executable
	 *            the executable for the shell
	 * @param parameters
	 *            a {@link List} of parameters for the shell executable
	 */
	public Shell(String executable, String... parameters) {
		this.executable = executable;
		this.parameters = Arrays.asList(parameters);
	}

	@Override
	public String getName() {
		return name;
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
		sb.append("[\"").append(executable).append("\", \"");
		sb.append(String.join("\", \"", parameters));
		sb.append("\"]");
		return sb.toString();
	}

}
