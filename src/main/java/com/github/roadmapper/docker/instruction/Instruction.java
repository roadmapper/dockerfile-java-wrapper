package com.github.roadmapper.docker.instruction;

/**
 * A Dockerfile instruction.
 * 
 * @author Vinay Dandekar
 */
public abstract class Instruction {
	
	/**
	 * The name of the instruction.
	 * @return the name of the instruction
	 */
	public abstract String getName();

	/**
	 * The formatted Dockerfile instruction string.
	 * @return the formatted instruction string
	 */
	public abstract String getInstruction();
}
