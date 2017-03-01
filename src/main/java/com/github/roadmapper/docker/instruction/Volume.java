package com.github.roadmapper.docker.instruction;

import java.util.Locale;

/**
 * Volume instruction.
 * 
 * @author Vinay Dandekar
 * @see <a href="https://docs.docker.com/engine/reference/builder/#/volume">
 *      VOLUME</a>
 */
public class Volume extends Instruction {
	private String name = Volume.class.getSimpleName().toUpperCase(Locale.ENGLISH);
	private String mountPoint;

	/**
	 * Creates a volume instruction.
	 * 
	 * @param mountPoint
	 *            the mount point on the container
	 */
	public Volume(String mountPoint) {
		this.mountPoint = mountPoint;
	}

	@Override
	public String getName() {
		return name;
	}

	/**
	 * Get the mount point on the container.
	 * 
	 * @return the mount point
	 */
	public String getMountPoint() {
		return mountPoint;
	}

	/**
	 * Sets the mount point on the container.
	 * 
	 * @param mountPoint
	 *            the mount point
	 */
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
