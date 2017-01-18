package pl.abof.test_template.arquillian.wf.cdi;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import javax.ejb.EJB;

import org.apache.log4j.Logger;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.EnterpriseArchive;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import pl.abof.test_template.arquillian.util.ear.AutoApplicationDescriptionBuilder;

@RunWith(Arquillian.class)
public class ThreePhaseEquationFactoryTest {

	@EJB
	private ThreePhaseEquationFactory cut; // ClassUnderTests

	@Deployment
	public static Archive<?> createDeployment() throws IOException {
		return getEar();
	}

	@Test
	public void PeopleInGoodMoodEqTest() {
		log.warn("TEST : PEOPLE_IN_GOOD_MOOD_FROM_YEAR");
		ThreePhaseEquation eq = cut.create(ThreePhaseEquationFactory.EquationType.PEOPLE_IN_GOOD_MOOD_FROM_YEAR);
		int actualResult = eq.perform(5);
		log.warn("17 = " + actualResult);
		
		assertEquals(17, actualResult);
	}
	
	@Test
	public void BlackHoleForceAmountEqTest() {
		log.warn("TEST: BLACK_HOLE_FORCE_AMOUNT_FROM_ITS_SIZE");
		ThreePhaseEquation eq = cut.create(ThreePhaseEquationFactory.EquationType.BLACK_HOLE_FORCE_AMOUNT_FROM_ITS_SIZE);
		int actualResult = eq.perform(0);
		log.warn("1 = " + actualResult);
		
		assertEquals(1, actualResult);
	}

	public static EnterpriseArchive getEar() throws IOException {
		JavaArchive testCdiJar = ShrinkWrap
				.create(JavaArchive.class, "test-cdi.jar")
				.addPackages(true, "pl.abof.test_template.arquillian.wf.cdi")
				.addClass(ThreePhaseEquationFactoryTest.class)
				.add(EmptyAsset.INSTANCE, "META-INF/beans.xml");
		AutoApplicationDescriptionBuilder descBuildHandler;
		EnterpriseArchive ear = ShrinkWrap
				.create(EnterpriseArchive.class)
				.addHandlers(descBuildHandler = new AutoApplicationDescriptionBuilder())
				.addAsModule(testCdiJar)
				.setApplicationXML(descBuildHandler.getAppXmlAsset());	
		return ear;

	}

	public static Logger log = Logger.getLogger(ThreePhaseEquationFactoryTest.class);
}
