package com.github.roadmapper.docker.instruction;

import java.util.Arrays;
import java.util.List;

/**
 * The interface for {@link Add} and {@link Copy} instructions.
 * @author Vinay Dandekar
 */
abstract class FileInstruction extends Instruction {

	private List<String> sources;
	private String destination;
	
	public FileInstruction(String source, String destination) {
		this.sources = Arrays.asList(source);
		this.destination = destination;
	}

	public FileInstruction(List<String> sources, String destination) {
		this.sources = sources;
		this.destination = destination;
	}

	/**
	 * Get source file/directory names to add to the Docker container.
	 * @return the source files
	 */
	public List<String> getSources() {
		return sources;
	}

	/**
	 * Set the source file/directory names to add to the Docker container.
	 * @param sources the source file/directory names
	 */
	public void setSources(List<String> sources) {
		this.sources = sources;
	}

	/**
	 * Get the destination directory for the source file/directory container.
	 * @return the destination directory on the Docker container
	 */
	public String getDestination() {
		return destination;
	}

	/**
	 * Set the destination directory for the source file/directory names.
	 * @param destination the destination directory on the Docker container
	 */
	public void setDestination(String destination) {
		this.destination = destination;
	}

	@Override
	public String getInstruction() {
		StringBuilder sb = new StringBuilder();
		sb.append(getName()).append(" ");
		sb.append("[\"");
		sb.append(String.join("\", \"", sources));
		sb.append("\", \"").append(destination).append("\"]");
		return sb.toString();
	}

}
