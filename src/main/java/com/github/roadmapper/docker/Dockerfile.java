package com.github.roadmapper.docker;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;

import com.github.roadmapper.docker.instruction.From;
import com.github.roadmapper.docker.instruction.Instruction;
import com.github.zafarkhaja.semver.Version;

/**
 * Models a Dockerfile.
 *
 * @author Vinay Dandekar
 * @see <a href="https://docs.docker.com/engine/reference/builder">Dockerfile
 *      reference</a>
 */
public class Dockerfile {

	/**
	 * Controls whether or not certain Windows based changes will apply to the
	 * Dockerfile.
	 * 
	 * @author Vinay Dandekar
	 */
	public enum Platform {
		LINUX, WINDOWS
	}

	private Version version;
	private Platform platform;
	private From baseImage;
	private List<Instruction> instructions;

	/**
	 * Creates a Dockerfile.
	 * 
	 * @param version
	 *            the version of Docker that will be used to build this
	 *            Dockerfile
	 * @param platform the platform of the Docker image
	 * @param baseImage
	 *            the FROM instruction for the Dockerfile's base image
	 */
	public Dockerfile(String version, Platform platform, From baseImage) {
		this.version = Version.valueOf(version);
		this.platform = platform;
		this.baseImage = baseImage;
		this.instructions = new ArrayList<>();
	}

	/**
	 * Creates a Dockerfile.
	 * 
	 * @param version
	 *            the version of Docker that will be used to build this
	 *            Dockerfile
	 * @param platform the platform of the Docker image
	 * @param baseImage
	 *            the FROM instruction for the Dockerfile's base image
	 * @param instructions
	 *            {@link List} of {@link Instruction}s to build
	 */
	public Dockerfile(String version, Platform platform, From baseImage, List<Instruction> instructions) {
		this.version = Version.valueOf(version);
		this.platform = platform;
		this.baseImage = baseImage;
		this.instructions = instructions;
	}

	/**
	 * Creates a Dockerfile from an existing Dockerfile.
	 * 
	 * @param file
	 *            the Dockerfile to copy from
	 */
	public Dockerfile(Dockerfile file) {
		this.version = file.version;
		this.platform = file.platform;
		this.baseImage = file.baseImage;
		this.instructions = file.instructions;
	}

	/**
	 * Creates a Dockerfile from an existing Dockerfile.
	 * 
	 * @param version
	 *            the version of Docker that will be used to build this
	 *            Dockerfile
	 * @param file
	 *            the Dockerfile to copy from
	 */
	public Dockerfile(String version, Dockerfile file) {
		this.version = Version.valueOf(version);
		this.platform = file.platform;
		this.baseImage = file.baseImage;
		this.instructions = file.instructions;
	}

	/**
	 * Get the Docker version that is compatible with this Dockerfile.
	 * 
	 * @return the Docker version as a {@link String}
	 */
	public String getVersion() {
		return version.toString();
	}

	/**
	 * Get the platform that is compatible with this Dockerfile.
	 * @return the {@link Platform}
	 */
	public Platform getPlatform() {
		return platform;
	}

	/**
	 * Get the base image of the Dockerfile.
	 * 
	 * @return the base image name
	 */
	public String getBaseImage() {
		return baseImage.getImage();
	}

	/**
	 * Set the base image of the Dockerfile.
	 * 
	 * @param baseImage
	 *            the FROM instruction for the Dockerfile's base image
	 */
	public void setBaseImage(From baseImage) {
		this.baseImage = baseImage;
	}

	/**
	 * Get all the instructions for Dockerfile.
	 * @return the {@link List} of {@link Instructions} for the Dockerfile
	 */
	public List<Instruction> getInstructions() {
		return instructions;
	}
	
	/**
	 * Add build instruction to Dockerfile.
	 * 
	 * @param instruction
	 *            the {@link Instruction} to add to the Dockerfile
	 * @return true if the instruction was added to the Dockerfile
	 */
	public boolean addInstruction(Instruction instruction) {
		if (checkInstructionVersion(instruction)) {
			instructions.add(instruction);
			return true;
		}
		return false;
	}

	/**
	 * Output the Dockerfile in the order that the instructions were added into
	 * it.
	 * 
	 * @return a {@link List} of {@link String}s of all of the Dockerfile instructions
	 * @throws Exception
	 *             if the base image is null
	 */
	public List<String> outputDockerfile() throws Exception {
		List<String> lines = new ArrayList<>();
		if (platform == Platform.WINDOWS) {
			lines.add("# escape=`");
			lines.add("");
		}
		if (baseImage == null) {
			throw new Exception("Missing base image for Dockerfile");
		}
		lines.add(baseImage.getInstruction());
		instructions.stream().map(instruction -> instruction.getInstruction()).forEach(lines::add);
		return lines;
	}

	/**
	 * Check the instruction to make sure the Docker version can use the
	 * instruction
	 * 
	 * @param instruction
	 *            the {@link Instruction} to check
	 * @return true if the Docker version is greater than or equal to the
	 *         instruction version
	 */
	private boolean checkInstructionVersion(Instruction instruction) {
		boolean isVersionOk = true;
		Annotation[] annotations = instruction.getClass().getAnnotations();
		if (annotations.length > 0) {
			for (Annotation annotation : annotations) {
				if (annotation instanceof DockerVersion) {
					DockerVersion ann = (DockerVersion) annotation;
					Version instructionVersion = Version.valueOf(ann.value());
					if (version.greaterThanOrEqualTo(instructionVersion)) {
						isVersionOk = true;
						break;
					} else {
						isVersionOk = false;
						break;
					}
				}
			}
		}
		return isVersionOk;
	}
}
