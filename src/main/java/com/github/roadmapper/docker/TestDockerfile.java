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
		Dockerfile f = new Dockerfile("1.11.1", Dockerfile.Platform.WINDOWS, new From("centos", "6.8"));
		f.addInstruction(new Run(null, true, "yum -y install epel-release", "yum -y install git python", "pip install boto"));		
		f.addInstruction(new Add("lol.txt", "/tmp"));		
		f.addInstruction(new Copy("lol.txt", "/tmp"));		
		f.addInstruction(new Expose(8080, 8000));		
		f.addInstruction(new Entrypoint(CommandType.EXEC, "tail", "-f", "lol.txt"));
		f.addInstruction(new OnBuild(new Run(null, true, "yum -y install epel-release", "yum -y install git python", "pip install boto")));
		f.addInstruction(new HealthCheck(null, null, null, new Cmd("ls", "-la")));
		
		System.out.println(f.getBaseImage());

		try {
			System.out.println(f.outputDockerfile());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
