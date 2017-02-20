package io.github.roadmapper.docker.instruction;

import java.util.Arrays;
import java.util.List;

public abstract class FileInstruction extends Instruction {

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

	public List<String> getSources() {
		return sources;
	}

	public void setSources(List<String> sources) {
		this.sources = sources;
	}

	public String getDestination() {
		return destination;
	}

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
