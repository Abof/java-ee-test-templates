package pl.abof.test_template.arquillian.wf.basic;

import static org.junit.Assert.*;

import java.io.IOException;

import javax.ejb.EJB;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.EnterpriseArchive;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import pl.abof.test_template.arquillian.util.ear.AutoApplicationDescriptionBuilder;
import pl.abof.test_template.arquillian.wf.basic.EchoService;


@RunWith(Arquillian.class)
public class EchoServiceTest {

	@EJB
	private EchoService cut; // ClassUnderTests

	@Deployment
	public static Archive<?> createDeployment() throws IOException {
		return getEar();
	}

	@Test
	public void testResponse() {
		String testSound = "Hello";
		String expectedResponseSound = "Hello...Hello...Hello...";
		String actualResponse = cut.response(testSound);

		assertEquals(expectedResponseSound, actualResponse);
	}

	public static EnterpriseArchive getEar() throws IOException {
		JavaArchive testEjbJar = ShrinkWrap
				.create(JavaArchive.class, "test-ejb.jar")
				.addClass(EchoService.class)
				.addClass(EchoServiceTest.class);
		
		// EAR + HANDLER
		AutoApplicationDescriptionBuilder descBuildHandler;
		EnterpriseArchive ear = ShrinkWrap
				.create(EnterpriseArchive.class)
				.addHandlers(descBuildHandler = new AutoApplicationDescriptionBuilder())
				.addAsModule(testEjbJar)
				.setApplicationXML(descBuildHandler.getAppXmlAsset());	

		return ear;

	}

}