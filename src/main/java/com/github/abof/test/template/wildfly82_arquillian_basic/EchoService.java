package com.github.abof.test.template.wildfly82_arquillian_basic;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Stream.generate;

import javax.ejb.Stateless;

@Stateless
public class EchoService {
	
	private static final int NO_OF_ECHOES = 3;
	private static final String RESONANCE_SOUND = "...";
	
	public String response(String sound) {
		return generate(() -> sound.concat(RESONANCE_SOUND)).limit(NO_OF_ECHOES).collect(joining());
	}

}