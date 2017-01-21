package pl.abof.test_template.arquillian.wf.jpa;

import java.io.IOException;
import java.util.Collection;

import javax.ejb.EJB;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.log4j.Logger;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.persistence.CreateSchema;
import org.jboss.arquillian.persistence.UsingDataSet;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.EnterpriseArchive;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import pl.abof.test_template.arquillian.util.ear.AutoApplicationDescriptionBuilder;
import pl.abof.test_template.arquillian.wf.jpa.entity.Book;

@RunWith(Arquillian.class)
@CreateSchema({"book.sql"})
public class BasicCruderTest {

	@EJB
	private BasicCruder cruder;
	
	@Deployment
	public static Archive<?> createDeployment() throws IOException {
		return getEar();
	}

	@PersistenceContext
	EntityManager em;
	
	@Test
	@UsingDataSet({"authors.yml", "books.yml"})
	public void test() {
		log.warn("GETTING THEM ALL!");
		Collection<Book> themAll = cruder.getThemAll();
		themAll.forEach(b -> log.warn("  " + b));
		
		Assert.assertTrue(true);
	}

	public static EnterpriseArchive getEar() throws IOException {
		JavaArchive testJar = ShrinkWrap
				.create(JavaArchive.class, "test-jpa.jar")
				.addPackage("pl.abof.test_template.arquillian.wf.jpa.entity")
				.addClass(BasicCruder.class)
				.addClass(BasicCruderTest.class)
				.addAsManifestResource("persistence-descriptors/basic/persistence.xml", "persistence.xml");
		
		// EAR + HANDLER
		AutoApplicationDescriptionBuilder descBuildHandler;
		EnterpriseArchive ear = ShrinkWrap
				.create(EnterpriseArchive.class)
				.addHandlers(descBuildHandler = new AutoApplicationDescriptionBuilder())
				.addAsModule(testJar)
				.setApplicationXML(descBuildHandler.getAppXmlAsset());	

		return ear;

	}
	
	private Logger log = Logger.getLogger(BasicCruderTest.class);
}
