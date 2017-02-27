package com.github.roadmapper.docker.instruction;

import java.util.Locale;

public class Workdir extends Instruction {

	private String name = Workdir.class.getSimpleName().toUpperCase(Locale.ENGLISH);
	private String directory;

	@Override
	public String getName() {
		return name;
	}

	public String getDirectory() {
		return directory;
	}

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
