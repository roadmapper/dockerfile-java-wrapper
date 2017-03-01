package com.github.roadmapper.docker.instruction;

import java.util.List;
import java.util.Locale;

/**
 * Copy instruction.
 * 
 * @author Vinay Dandekar
 * @see <a href="https://docs.docker.com/engine/reference/builder/#/copy">COPY
 *      </a>
 */
public class Copy extends FileInstruction {

	private String name = Copy.class.getSimpleName().toUpperCase(Locale.ENGLISH);

	/**
	 * Creates a copy instruction. Copies the source to the destination on the
	 * Docker container.
	 * 
	 * @param source
	 *            the source file/directory
	 * @param destination
	 *            the destination path
	 */
	public Copy(String source, String destination) {
		super(source, destination);
	}

	/**
	 * Creates a copy instruction. Copies the sources to the destination on the
	 * Docker container.
	 * 
	 * @param source
	 *            the source files/directories
	 * @param destination
	 *            the destination path
	 */
	public Copy(List<String> sources, String destination) {
		super(sources, destination);
	}

	@Override
	public String getName() {
		return name;
	}

}
