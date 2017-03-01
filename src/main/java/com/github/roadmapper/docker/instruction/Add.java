package com.github.roadmapper.docker.instruction;

import java.util.List;
import java.util.Locale;

/**
 * Add instruction. The add instruction has some idiosyncrasies compared to the
 * <a href="https://docs.docker.com/engine/reference/builder/#/copy">copy</a>
 * instruction.
 * 
 * @author Vinay Dandekar
 * @see <a href="https://docs.docker.com/engine/reference/builder/#/add">ADD
 *      </a>
 */
public class Add extends FileInstruction {

	private String name = Add.class.getSimpleName().toUpperCase(Locale.ENGLISH);

	/**
	 * Creates a add instruction. Copies the source to the destination on the
	 * Docker container.
	 * 
	 * @param source
	 *            the source file/directory
	 * @param destination
	 *            the destination path
	 */
	public Add(String source, String destination) {
		super(source, destination);
	}

	/**
	 * Creates a add instruction. Copies the source to the destination on the
	 * Docker container.
	 * 
	 * @param source
	 *            the source file/directory
	 * @param destination
	 *            the destination path
	 */
	public Add(List<String> sources, String destination) {
		super(sources, destination);
	}

	@Override
	public String getName() {
		return name;
	}

}
