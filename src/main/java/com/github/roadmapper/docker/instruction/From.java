package com.github.roadmapper.docker.instruction;

import java.util.Locale;

public class From extends Instruction {
	
	private String name = From.class.getSimpleName().toUpperCase(Locale.ENGLISH);
	private String repository;
	private String tag;
	private String digest;
	
	public From(String image) {
		this(image, null, null);
	}
	
	public From(String repository, String tag) {
		this(repository, tag, null);
	}
	public From(String repository, String tag, String digest) {
		this.repository = repository;
		this.tag = tag;
		this.digest = digest;
	}
	
	@Override
	public String getName() {
		return name;
	}

	public String getRepository() {
		return repository;
	}

	public void setRepository(String repository) {
		this.repository = repository;
	}

	public String getTag() {
		return tag != null ? tag : "latest";
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getDigest() {
		return digest;
	}

	public void setDigest(String digest) {
		this.digest = digest;
	}
	
	public String getImage() {
		if (tag != null && digest == null) {
			return repository + ":" + tag;
		} else if (tag == null && digest != null) {
			return repository + "@" + digest;
		} else {
			return repository;
		}
	}

	@Override
	public String getInstruction() {
		StringBuilder sb = new StringBuilder();
		sb.append(name).append(" ");
		sb.append(getImage());
		return sb.toString();
	}
}
