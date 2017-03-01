Dockerfile Java Wrapper
=============================

[![Build Status](https://travis-ci.org/roadmapper/dockerfile-java-wrapper.svg?branch=master)](https://travis-ci.org/roadmapper/dockerfile-java-wrapper)
[![](https://jitpack.io/v/roadmapper/dockerfile-java-wrapper.svg)](https://jitpack.io/#roadmapper/dockerfile-java-wrapper)

A simple Java wrapper for building a Dockerfile.

### About
This library allows you to create a Docker image context programmatically and build the Docker image with something like Spotify's [Docker client for Java](https://github.com/spotify/docker-client).

### Download
```xml
<repositories>
  <repository>
    <id>jitpack.io</id>
    <url>https://jitpack.io</url>
  </repository>
</repositories>
<dependency>
  <groupId>com.github.roadmapper</groupId>
  <artifactId>dockerfile-wrapper</artifactId>
  <version>0.1.0</version>
</dependency>
```

### Usage
```java
Dockerfile df = new Dockerfile("1.13.1", Dockerfile.Platform.LINUX, new From("centos", "6.8"));
df.addInstruction(new Run(null, true, "yum -y install epel-release", 
	"yum -y install git python python-pip", "pip install boto"));		
df.addInstruction(new HealthCheck(null, null, null, new Cmd("uptime")));
try {
	df.outputDockerfile().forEach(System.out::println);
} catch (Exception e) {
	e.printStackTrace();
}
```

####Pairing this with Spotify's client:
```java
Dockerfile df = new Dockerfile("1.13.1", Dockerfile.Platform.LINUX, new From("centos", "6.8"));
df.addInstruction(new Run(null, true, "yum -y install epel-release", 
	"yum -y install git python python-pip", "pip install boto"));		

try {
	System.out.println(df.outputDockerfile());
	Path dockerfile = Paths.get("Dockerfile");
	Files.write(dockerfile, df.outputDockerfile(), Charset.forName("UTF-8"));
	final DockerClient docker = DefaultDockerClient.fromEnv().build();
	final AtomicReference<String> imageIdFromMessage = new AtomicReference<>();
	
	// Send the context (Dockerfile and surrounding files)
	final String returnedImageId = docker.build(
	    dockerfile.getParent(), "<docker image tag>", new ProgressHandler() {
	      @Override
	      public void progress(ProgressMessage message) throws DockerException {
	        final String imageId = message.buildImageId();
	        if (imageId != null) {
	          imageIdFromMessage.set(imageId);
	        }
	      }
	    });
} catch (Exception e) {
	e.printStackTrace();
}
```
