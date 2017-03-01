package com.github.roadmapper.docker.instruction;

import java.util.Locale;

/**
 * From instruction.
 * 
 * @author Vinay Dandekar
 * @see <a href="https://docs.docker.com/engine/reference/builder/#/from">FROM
 *      </a>
 */
public class From extends Instruction {

	private String name = From.class.getSimpleName().toUpperCase(Locale.ENGLISH);
	private String repository;
	private String tag;
	private String digest;

	/**
	 * Creates a from instruction.
	 * 
	 * @param image
	 *            the Docker base image
	 */
	public From(String image) {
		this(image, null, null);
	}

	/**
	 * Creates a from instruction.
	 * 
	 * @param repository
	 *            the Docker base repository
	 * @param tag
	 *            the Docker base tag
	 */
	public From(String repository, String tag) {
		this(repository, tag, null);
	}

	/**
	 * Creates a from instruction. Provide a tag or a digest, not both. If both
	 * are provided, only the repository will get returned for
	 * {@link #getInstruction()}.
	 * 
	 * @param repository
	 *            the Docker base repository
	 * @param tag
	 *            the Docker base tag
	 * @param digest
	 *            the Docker base digest
	 */
	public From(String repository, String tag, String digest) {
		this.repository = repository;
		this.tag = tag;
		this.digest = digest;
	}

	@Override
	public String getName() {
		return name;
	}

	/**
	 * Get the repository/image name.
	 * 
	 * @return the repository/image name
	 */
	public String getRepository() {
		return repository;
	}

	/**
	 * Set the repository/image name.
	 * 
	 * @param repository
	 *            the Docker repository/image name
	 */
	public void setRepository(String repository) {
		this.repository = repository;
	}

	/**
	 * Get the repository/image tag. If the tag is null, the instruction will
	 * return "latest".
	 * 
	 * @return the repository/image tag
	 */
	public String getTag() {
		return tag != null ? tag : "latest";
	}

	/**
	 * Set the repository/image tag. Set the tag to null for the latest
	 * repository/image.
	 * 
	 * @param tag
	 *            the Docker repository/image tag
	 */
	public void setTag(String tag) {
		this.tag = tag;
	}

	/**
	 * Get the repository/image digest.
	 * 
	 * @return the repository/image digest
	 */
	public String getDigest() {
		return digest;
	}

	/**
	 * Set the repository/image digest.
	 * 
	 * @param digest
	 *            the repository/image digest
	 */
	public void setDigest(String digest) {
		this.digest = digest;
	}

	/**
	 * Get the image with tag or digest if applicable.
	 * 
	 * @return the image string
	 */
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
