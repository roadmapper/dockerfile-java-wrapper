package io.github.roadmapper.docker;

import io.github.roadmapper.docker.instruction.Add;
import io.github.roadmapper.docker.instruction.CommandType;
import io.github.roadmapper.docker.instruction.Copy;
import io.github.roadmapper.docker.instruction.Entrypoint;
import io.github.roadmapper.docker.instruction.Expose;
import io.github.roadmapper.docker.instruction.From;
import io.github.roadmapper.docker.instruction.OnBuild;
import io.github.roadmapper.docker.instruction.Run;

public class TestDockerfile {
	public static void main(String[] args) {
		Dockerfile f = new Dockerfile();
		f.setBaseImage(new From("centos", "6.8"));
		f.addInstruction(new Run(null, true, "yum -y install epel-release", "yum -y install git python", "pip install boto"));
		f.addInstruction(new Add("lol.txt", "/tmp"));
		f.addInstruction(new Copy("lol.txt", "/tmp"));
		f.addInstruction(new Expose(8080, 8000));
		f.addInstruction(new Entrypoint(CommandType.EXEC, "tail", "-f", "lol.txt"));
		f.addInstruction(new OnBuild(new Run(null, true, "yum -y install epel-release", "yum -y install git python", "pip install boto")));

		System.out.println(f.outputDockerfile());
	}

}
