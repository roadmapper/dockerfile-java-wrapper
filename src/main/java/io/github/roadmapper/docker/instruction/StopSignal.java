package io.github.roadmapper.docker.instruction;

import java.util.Locale;

public class StopSignal extends Instruction {
	private String name = StopSignal.class.getSimpleName().toUpperCase(Locale.ENGLISH);
	private String signal;
	
	public StopSignal(String signal) {
		this.signal = signal;
	}
	
	@Override
	public String getName() {
		return name;
	}
	
	public String getSignal() {
		return signal;
	}

	public void setSignal(String signal) {
		this.signal = signal;
	}

	@Override
	public String getInstruction() {
		StringBuilder sb = new StringBuilder();
		sb.append(getName()).append(" ").append(signal);
		return sb.toString();
	}

}
