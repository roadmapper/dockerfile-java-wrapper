package com.github.roadmapper.docker;

import com.github.roadmapper.docker.instruction.Add;
import com.github.roadmapper.docker.instruction.Cmd;
import com.github.roadmapper.docker.instruction.CommandType;
import com.github.roadmapper.docker.instruction.Copy;
import com.github.roadmapper.docker.instruction.Entrypoint;
import com.github.roadmapper.docker.instruction.Expose;
import com.github.roadmapper.docker.instruction.From;
import com.github.roadmapper.docker.instruction.HealthCheck;
import com.github.roadmapper.docker.instruction.OnBuild;
import com.github.roadmapper.docker.instruction.Run;

public class TestDockerfile {
	public static void main(String[] args) {
		Dockerfile df = new Dockerfile("1.11.1", Dockerfile.Platform.LINUX, new From("centos", "6.8"));
		df.addInstruction(
				new Run(null, true, "yum -y install epel-release", "yum -y install git python", "pip install boto"));
		df.addInstruction(new Add("lol.txt", "/tmp"));
		df.addInstruction(new Copy("lol.txt", "/tmp"));
		df.addInstruction(new Expose(8080, 8000));
		df.addInstruction(new Entrypoint(CommandType.EXEC, "tail", "-f", "lol.txt"));
		df.addInstruction(new OnBuild(
				new Run(null, true, "yum -y install epel-release", "yum -y install git python", "pip install boto")));
		df.addInstruction(new HealthCheck(null, null, null, new Cmd("ls", "-la")));

		System.out.println(df.getBaseImage());

		try {
			df.outputDockerfile().forEach(System.out::println);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
