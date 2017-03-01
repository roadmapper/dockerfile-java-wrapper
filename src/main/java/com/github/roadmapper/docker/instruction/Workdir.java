package com.github.roadmapper.docker.instruction;

import java.util.Locale;

/**
 * Workdir instruction.
 * 
 * @author Vinay Dandekar
 * @see <a href="https://docs.docker.com/engine/reference/builder/#/volume">
 *      VOLUME</a>
 */
public class Workdir extends Instruction {

	private String name = Workdir.class.getSimpleName().toUpperCase(Locale.ENGLISH);
	private String directory;
	
	/**
	 * Creates a workdir instruction.
	 * @param directory the working directory
	 */
	public Workdir(String directory) {
		this.directory = directory;
	}

	@Override
	public String getName() {
		return name;
	}

	/**
	 * Get the working directory.
	 * @return the working directory
	 */
	public String getDirectory() {
		return directory;
	}

	/**
	 * Set the working directory.
	 * @param directory the working directory
	 */
	public void setMountPoint(String directory) {
		this.directory = directory;
	}

	@Override
	public String getInstruction() {
		StringBuilder sb = new StringBuilder();
		sb.append(getName()).append(" ").append(directory);
		return sb.toString();
	}

}
