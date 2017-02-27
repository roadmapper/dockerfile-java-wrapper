package com.github.roadmapper.docker.instruction;

import java.util.Locale;

/**
 * User instruction.
 * 
 * @author Vinay Dandekar
 * @see <a href="https://docs.docker.com/engine/reference/builder/#/user">USER
 *      </a>
 */
public class User extends Instruction {

	private String name = User.class.getSimpleName().toUpperCase(Locale.ENGLISH);
	private String user;

	/**
	 * Create a user instruction to set the Dockerfile to use this user for the
	 * image.
	 * 
	 * @param user
	 *            the username or UID for the image
	 */
	public User(String user) {
		this.user = user;
	}

	@Override
	public String getName() {
		return name;
	}

	/**
	 * Get the username or UID for the Docker image.
	 * 
	 * @return the username or UID
	 */
	public String getUser() {
		return user;
	}

	/**
	 * Set the username or UID for the Docker image.
	 * 
	 * @param user
	 *            the username or UID
	 */
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
