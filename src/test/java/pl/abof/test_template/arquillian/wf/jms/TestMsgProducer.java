package pl.abof.test_template.arquillian.wf.jms;

import javax.annotation.Resource;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jms.Destination;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.JMSProducer;
import javax.jms.Queue;
import javax.jms.TextMessage;

import javax.jms.JMSDestinationDefinition;
import javax.jms.JMSDestinationDefinitions;

import org.apache.log4j.Logger;

@JMSDestinationDefinitions(value = {
    @JMSDestinationDefinition(
        name = "java:/queue/BirthdayWishesQueue",
        interfaceName = "javax.jms.Queue"
    )
})
@Stateless
@LocalBean
public class TestMsgProducer {

	@Inject
	private JMSContext context;

	@Resource(lookup="java:/queue/BirthdayWishesQueue") //ok lookup="java:/DBirthdayWishesQueue"
	private Destination destination;
	
	public void sendMsg(String msgText) throws JMSException {
		
		log.warn("[CREATING JMS MESSAGE!]");
		TextMessage msg = context.createTextMessage();
		msg.setText(msgText);
		
		log.warn("[CREATING PRODUCER!]");
		JMSProducer producer = context.createProducer();
		
		log.warn("[SENDINGJMS MESSAGE!]");
		producer.send(destination, msg);
		
		log.warn("[END!]");
	}
	
	final static Logger log = Logger.getLogger(MDBConsumer.class);
	
}
