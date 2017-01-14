package pl.abof.test_template.arquillian.wf.basic;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Stream.generate;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 * Simple stateless service/bean for basic Arquillian test. Wanna see it in action -
 * check {@link EchoServiceTest}
 * 
 * @author Abof
 *
 */
@Stateless
@LocalBean
public class EchoService {

	private static final int NO_OF_ECHOES = 3;
	private static final String RESONANCE_SOUND = "...";

	public String response(String sound) {
		return generate(() -> sound.concat(RESONANCE_SOUND)).limit(NO_OF_ECHOES).collect(joining());
	}

}