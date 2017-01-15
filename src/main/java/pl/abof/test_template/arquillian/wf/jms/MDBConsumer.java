package pl.abof.test_template.arquillian.wf.jms;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.apache.log4j.Logger;

@MessageDriven(activationConfig = {
	@ActivationConfigProperty(propertyName="destinationType", propertyValue="javax.jms.Queue"),
	@ActivationConfigProperty(propertyName="destinationLookup", propertyValue="java:/queue/BirthdayWishesQueue")
})
public class MDBConsumer implements MessageListener {

	@Override
	public void onMessage(Message msg) {
		
		TextMessage tMsg = (TextMessage) msg;
		
		try {
			log.warn("\n\n{KONSUMENT} Some message here! WOW! " + tMsg.getJMSType() + " : " + tMsg.getText() + "\n\n");
		} catch (JMSException e) {
			log.error("There was some serious JMSException!", e);
		}finally {
			log.info("BirthdayWishesMDBConsumer just handled msg...");
		}
		
		
	}
	
	final static Logger log = Logger.getLogger(MDBConsumer.class);

}
