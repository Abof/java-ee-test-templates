package pl.abof.test_template.arquillian.wf.jms;

import java.io.IOException;

import javax.inject.Inject;
import javax.jms.JMSException;

import org.apache.log4j.Logger;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.EnterpriseArchive;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import pl.abof.test_template.arquillian.util.ear.AutoApplicationDescriptionBuilder;

@RunWith(Arquillian.class)
public class BirthdayWishesMDBConsumerTest {

	@Deployment
	public static Archive<?> createDeployment() throws IOException {
		return getEar();
	}

	@Inject
	private TestMsgProducer producer;

	@Test
	public void test() throws JMSException {
		log.warn("\n\n[TEST START]\n\n");
		producer.sendMsg("Cześć czołem!!");
		log.warn("\n\n[TEST END!]\n\n");
	}

	public static EnterpriseArchive getEar() throws IOException {
		JavaArchive testEjbJar = ShrinkWrap
				.create(JavaArchive.class, "test-jms.jar")
				.addClass(MDBConsumer.class)
				.addClass(TestMsgProducer.class)
				.addClass(BirthdayWishesMDBConsumerTest.class);
		
		AutoApplicationDescriptionBuilder descBuildHandler;
		EnterpriseArchive ear = ShrinkWrap
				.create(EnterpriseArchive.class)
				.addHandlers(descBuildHandler = new AutoApplicationDescriptionBuilder())
				.addAsModule(testEjbJar)
				.setApplicationXML(descBuildHandler.getAppXmlAsset());	

		return ear;

	}
	
	final static Logger log = Logger.getLogger(MDBConsumer.class);
	
}
