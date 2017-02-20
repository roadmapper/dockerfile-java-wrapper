package io.github.roadmapper.docker.instruction;

import java.util.Locale;

public class From extends Instruction {
	
	private String name = From.class.getSimpleName().toUpperCase(Locale.ENGLISH);
	private String image;
	private String tag;
	private String digest;
	
	public From(String image) {
		this(image, null, null);
	}
	
	public From(String image, String tag) {
		this(image, tag, null);
	}
	public From(String image, String tag, String digest) {
		this.image = image;
		this.tag = tag;
		this.digest = digest;
	}
	
	@Override
	public String getName() {
		return name;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
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

	@Override
	public String getInstruction() {
		StringBuilder sb = new StringBuilder();
		sb.append(name).append(" ").append(image);
		if (tag != null && digest == null) {
			sb.append(":").append(tag);
		} else if (tag == null && digest != null) {
			sb.append("@").append(digest);
		}
		return sb.toString();
	}
}
