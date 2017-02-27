package com.github.roadmapper.docker.instruction;

public class Comment extends Instruction {

	private String comment;
	
	public Comment(String comment) {
		this.comment = comment;
	}
	
	@Override
	public String getName() {
		return "# ";
	}

	@Override
	public String getInstruction() {
		return getName() + comment;
	}

}
