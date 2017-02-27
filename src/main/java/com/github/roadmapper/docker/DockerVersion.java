package com.github.roadmapper.docker;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Sets the minimum Docker version for an {@link Instruction}.
 * @author Vinay Dandekar
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface DockerVersion {
	
	/**
	 * The minimum Docker version for an {@link Instruction}.
	 * @return the Docker version as a {@link String}
	 */
	String value();
}
