package com.github.roadmapper.docker.instruction;

import java.util.Locale;

public class OnBuild extends Instruction {
	private String name = OnBuild.class.getSimpleName().toUpperCase(Locale.ENGLISH);
	private Instruction buildInstruction;
	
	public OnBuild(Instruction buildInstruction) {
		this.buildInstruction = buildInstruction;
	}
	
	@Override
	public String getName() {
		return name;
	}
	
	public Instruction getBuildInstruction() {
		return buildInstruction;
	}

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
