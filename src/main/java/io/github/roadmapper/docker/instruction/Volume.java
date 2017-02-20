package io.github.roadmapper.docker.instruction;

import java.util.Locale;

public class Volume extends Instruction {
	private String name = Volume.class.getSimpleName().toUpperCase(Locale.ENGLISH);
	private String mountPoint;

	@Override
	public String getName() {
		return name;
	}

	public String getMountPoint() {
		return mountPoint;
	}

	public void setMountPoint(String mountPoint) {
		this.mountPoint = mountPoint;
	}

	@Override
	public String getInstruction() {
		StringBuilder sb = new StringBuilder();
		sb.append(getName()).append(" ");
		sb.append("[\"").append(mountPoint).append("\"]");
		return sb.toString();
	}

}
