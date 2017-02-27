package com.github.roadmapper.docker;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import com.github.roadmapper.docker.instruction.Cmd;
import com.github.roadmapper.docker.instruction.From;
import com.github.roadmapper.docker.instruction.HealthCheck;
import com.github.roadmapper.docker.instruction.Run;
import com.github.roadmapper.docker.instruction.Shell;

public class DockerfileTest {

	static String version;
	static From baseImageLinux;
	static From baseImageWindows;

	@BeforeClass
	public static void setUp() {
		version = "1.11.1";
		baseImageLinux = new From("centos", "6.8");
		baseImageWindows = new From("microsoft/nanoserver");
	}

	@Test
	public void testDockerfileLinux() {
		Dockerfile linuxDockerfile = new Dockerfile(version, Dockerfile.Platform.LINUX, baseImageLinux);
		assertEquals(linuxDockerfile.getVersion(), version);
		assertEquals(linuxDockerfile.getBaseImage(), baseImageLinux.getImage());
		assertEquals(linuxDockerfile.getPlatform(), Dockerfile.Platform.LINUX);
		assertEquals(linuxDockerfile.getInstructions().size(), 0);
	}

	@Test
	public void testAddInstructionToDockerfile() {
		Dockerfile linuxDockerfile = new Dockerfile(version, Dockerfile.Platform.LINUX, baseImageLinux);
		List<String> parameters = Arrays.asList("yum -y install epel-release", "yum -y install git python",
				"pip install boto");
		Run run = new Run(null, true, parameters.toArray(new String[parameters.size()]));
		linuxDockerfile.addInstruction(run);
		assertEquals(run, linuxDockerfile.getInstructions().stream().findFirst().get());
		assertEquals(String.join(" && ", parameters),
				((Run) linuxDockerfile.getInstructions().stream().findFirst().get()).getCommand());
	}

	@Test
	public void testAddInvalidInstructionToDockerfile() {
		Dockerfile linuxDockerfile = new Dockerfile(version, Dockerfile.Platform.LINUX, baseImageLinux);
		Cmd healthCheckCommand = new Cmd("uptime");
		HealthCheck healthCheck = new HealthCheck(null, null, null, healthCheckCommand);
		linuxDockerfile.addInstruction(healthCheck);
		assertFalse(linuxDockerfile.getInstructions().stream().findFirst().isPresent());
	}

	@Test
	public void testDockerfileLinuxOutput() throws Exception {
		Dockerfile linuxDockerfile = new Dockerfile(version, Dockerfile.Platform.LINUX, baseImageLinux);
		List<String> parameters = Arrays.asList("yum -y install epel-release", "yum -y install git python",
				"pip install boto");
		Run run = new Run(null, true, parameters.toArray(new String[parameters.size()]));
		linuxDockerfile.addInstruction(run);
		Cmd healthCheckCommand = new Cmd("uptime");
		HealthCheck healthCheck = new HealthCheck(null, null, null, healthCheckCommand);
		linuxDockerfile.addInstruction(healthCheck);

		StringBuilder output = new StringBuilder();
		output.append(baseImageLinux.getInstruction()).append("\n");
		output.append(run.getInstruction());

		assertEquals(output.toString(), linuxDockerfile.outputDockerfile());
	}

	@Test
	public void testDockerfileWindowsOutput() throws Exception {
		Dockerfile windowsDockerfile = new Dockerfile("1.12.0", Dockerfile.Platform.WINDOWS, baseImageWindows);
		Shell shell = new Shell("powershell", "-Command",
				"$ErrorActionPreference = 'Stop'; $ProgressPreference = 'SilentlyContinue';");
		windowsDockerfile.addInstruction(shell);
		Run run = new Run("dir");
		windowsDockerfile.addInstruction(run);

		StringBuilder output = new StringBuilder();
		output.append("# escape=`\n\n");
		output.append(baseImageWindows.getInstruction()).append("\n");
		output.append(shell.getInstruction()).append("\n");
		output.append(run.getInstruction());
		
		assertEquals(output.toString(), windowsDockerfile.outputDockerfile());
	}
}