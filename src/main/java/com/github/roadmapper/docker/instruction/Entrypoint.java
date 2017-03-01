package com.github.roadmapper.docker.instruction;

import java.util.Locale;

/**
 * Entrypoint instruction.
 * 
 * @author Vinay Dandekar
 * @see <a href="https://docs.docker.com/engine/reference/builder/#/from">ENTRYPOINT
 *      </a>
 */
public class Entrypoint extends Cmd {
	private String name = Entrypoint.class.getSimpleName().toUpperCase(Locale.ENGLISH);
	
	/**
	 * Creates an entrypoint instruction.
	 * 
	 * @param type
	 *            type of command (shell or exec)
	 * @param cmdExec
	 *            the command or executable to run
	 * @param parameters
	 *            the parameters to run with the command/executable
	 */
	public Entrypoint(CommandType type, String cmdExec, String... parameters) {
		super(type, cmdExec, parameters);
	}
	
	@Override
	public String getName() {
		return name;
	}
}
