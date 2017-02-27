package com.github.roadmapper.docker.instruction;

import java.util.Locale;

import com.github.roadmapper.docker.DockerVersion;

/**
 * Healthcheck instruction. Requires Docker 1.12 or above.
 * 
 * @author Vinay Dandekar
 * @see <a href="https://docs.docker.com/engine/reference/builder/#healthcheck">
 *      HEALTHCHECK</a>
 */
@DockerVersion("1.12.0")
public class HealthCheck extends Instruction {

	private String name = HealthCheck.class.getSimpleName().toUpperCase(Locale.ENGLISH);
	private Integer interval;
	private Integer timeout;
	private Integer retries;
	private Cmd command;
	private boolean disable;

	/**
	 * Creates a healthcheck instruction.
	 * 
	 * @param interval
	 *            the interval in seconds (if null, default is 30s)
	 * @param timeout
	 *            the timeout in seconds (if null, default is 30s)
	 * @param retries
	 *            the number of retries to perform (if null, default is 3)
	 * @param command
	 *            the {@link Cmd} to run for the healthcheck
	 * 
	 */
	public HealthCheck(Integer interval, Integer timeout, Integer retries, Cmd command) {
		this.interval = interval;
		this.timeout = timeout;
		this.retries = retries;
		this.command = command;
		this.disable = false;
	}

	/**
	 * Creates a healthcheck instruction that is disabled.
	 */
	public HealthCheck() {
		this.interval = null;
		this.timeout = null;
		this.retries = null;
		this.command = null;
		this.disable = true;
	}

	@Override
	public String getName() {
		return name;
	}

	/**
	 * Gets the healthcheck interval in seconds.
	 * 
	 * @return the healthcheck interval in seconds
	 */
	public Integer getInterval() {
		return interval;
	}

	/**
	 * Sets the healthcheck interval in seconds.
	 * 
	 * @param interval
	 *            the healthcheck interval in seconds
	 */
	public void setInterval(Integer interval) {
		this.interval = interval;
	}

	/**
	 * Gets the healthcheck timeout in seconds.
	 * 
	 * @return the healthcheck timeout in seconds
	 */
	public Integer getTimeout() {
		return timeout;
	}

	/**
	 * Sets the healthcheck timeout in seconds.
	 * 
	 * @param timeout
	 *            the healthcheck timeout in seconds
	 */
	public void setTimeout(Integer timeout) {
		this.timeout = timeout;
	}

	/**
	 * Gets the number of retries for a healthcheck.
	 * 
	 * @return the number of retries
	 */
	public Integer getRetries() {
		return retries;
	}

	/**
	 * Sets the amount of retries for a healthcheck.
	 * 
	 * @param retries
	 *            the number of retries
	 */
	public void setRetries(Integer retries) {
		this.retries = retries;
	}

	public Cmd getCommand() {
		return command;
	}

	public void setCommand(Cmd command) {
		this.command = command;
	}

	public boolean isDisable() {
		return disable;
	}

	public void setDisable(boolean disable) {
		this.disable = disable;
	}

	@Override
	public String getInstruction() {
		StringBuilder sb = new StringBuilder();
		sb.append(getName()).append(" ");
		if (!disable) {
			if (interval != null) {
				sb.append("--interval").append(interval).append(" ");
			}
			if (timeout != null) {
				sb.append("--timeout").append(timeout).append(" ");
			}
			if (retries != null) {
				sb.append("--retries").append(retries).append(" ");
			}
			sb.append(command.getInstruction());
		} else {
			sb.append("NONE");
		}
		return sb.toString();
	}

}
