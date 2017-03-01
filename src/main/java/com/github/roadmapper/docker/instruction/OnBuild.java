package com.github.roadmapper.docker.instruction;

import java.util.Locale;

/**
 * Onbuild instruction.
 * 
 * @author Vinay Dandekar
 * @see <a href="https://docs.docker.com/engine/reference/builder/#/onbuild">
 *      ONBUILD</a>
 */
public class OnBuild extends Instruction {
	private String name = OnBuild.class.getSimpleName().toUpperCase(Locale.ENGLISH);
	private Instruction buildInstruction;

	/**
	 * Creates an onbuild instruction.
	 * 
	 * @param buildInstruction
	 *            the instruction to run on build of this Docker image
	 */
	public OnBuild(Instruction buildInstruction) {
		this.buildInstruction = buildInstruction;
	}

	@Override
	public String getName() {
		return name;
	}

	/**
	 * Get the on build instruction.
	 * 
	 * @return the instruction
	 */
	public Instruction getBuildInstruction() {
		return buildInstruction;
	}

	/**
	 * Sets the on build instruction.
	 * 
	 * @param buildInstruction
	 *            the instruction to run on build of this Docker image
	 */
	public void setBuildInstruction(Instruction buildInstruction) {
		this.buildInstruction = buildInstruction;
	}

	@Override
	public String getInstruction() {
		StringBuilder sb = new StringBuilder();
		sb.append(getName()).append(" ");
		sb.append(buildInstruction.getInstruction());
		return sb.toString();
	}

}
