package io.github.roadmapper.docker;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import io.github.roadmapper.docker.instruction.From;
import io.github.roadmapper.docker.instruction.Instruction;

public class Dockerfile {
	public enum System {
		LINUX,
		WINDOWS
	}
	
	private System system;	
	private List<Instruction> instructions;

	public Dockerfile() {
		this.instructions = new ArrayList<>();
	}

	public Dockerfile(List<Instruction> instructions) {
		this.instructions = instructions;
	}

	public String getBaseImage() {
		From from = (From) instructions.stream().filter(instruction -> instruction instanceof From).findFirst().get();
		return from.getImage();
	}

	public void setBaseImage(From from) {
		addInstruction(from);
	}

	public void addInstruction(Instruction instruction) {
		instructions.add(instruction);
	}

	public String outputDockerfile() {
		StringBuilder sb = new StringBuilder();
		if (system == System.WINDOWS) {
			sb.append("# escape=`\n\n");
		}
		sb.append(instructions.stream().map(instruction -> instruction.getInstruction())
				.collect(Collectors.joining("\n")));
		return sb.toString();
	}
}
