package io.github.roadmapper.docker.instruction;

import java.util.Locale;

public class User extends Instruction {
	
	private String name = User.class.getSimpleName().toUpperCase(Locale.ENGLISH);
	private String user;
	
	public User(String user) {
		this.user = user;
	}
	
	@Override
	public String getName() {
		return name;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	@Override
	public String getInstruction() {
		StringBuilder sb = new StringBuilder();
		sb.append(name).append(" ").append(user);
		return sb.toString();
	}

}
