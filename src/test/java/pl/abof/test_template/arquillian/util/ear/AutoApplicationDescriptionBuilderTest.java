package pl.abof.test_template.arquillian.util.ear;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import java.io.File;
import java.io.IOException;

import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.EnterpriseArchive;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Before;
import org.junit.Test;

import com.google.common.base.Charsets;
import com.google.common.io.Files;

public class AutoApplicationDescriptionBuilderTest {

	private AutoApplicationDescriptionBuilder cutHandler; // Class Under Tests
	private EnterpriseArchive ear;

	/**
	 * You can adopt this approach to make use of
	 * {@link AutoApplicationDescriptionBuilder} : create EAR; create handler;
	 * add it to EAR; get app. desc. from handler.
	 */
	@Before
	public void setUp() {
		// Create an EAR
		ear = ShrinkWrap.create(EnterpriseArchive.class);
		// Create handler (with default name)
		cutHandler = new AutoApplicationDescriptionBuilder();
		// Add handler to EAR
		ear.addHandlers(this.cutHandler);
		// ..then get descriptor XML via `cutHandler.getAppXml()`; for that -
		// check one of tests..
	}

	@Test
	public void whenSingleJarAddedToEarThenAppDescWithSingleModule() throws IOException {
		ear.addAsModule(FIRST_EJB_ARCHIVE);
		assertEqualityOfActualAndExpectedAppDescriptionXml(SINGLE_MODULE_EXPECTED_APPLICATION_XML_CONTENT_URL);
	}

	@Test
	public void whenTwoJarsAddedToEarThenAppDescWithTwoModules() throws IOException {
		ear.addAsModule(FIRST_EJB_ARCHIVE).addAsModule(SECOND_EJB_ARCHIVE);
		assertEqualityOfActualAndExpectedAppDescriptionXml(TWO_MODULES_EXPECTED_APPLICATION_XML_CONTENT_URL);
	}

	@Test
	public void whenNoJarsAddedToEarThenAppDescWithoutModules() throws IOException {
		/* Nothing to do with EAR; free time! ;) */
		assertEqualityOfActualAndExpectedAppDescriptionXml(EMPTY_EXPECTED_APPLICATION_XML_CONTENT_URL);

	}

	/**
	 * Method for asserting equality between given hard-coded in resources
	 * application.xml and the one generated wile particular test (via
	 * {@link #cutHandler}).
	 * 
	 * @param expectedDescriptor
	 *            file representing expected XML
	 * @throws IOException
	 *             because of file system operations; sigh -_-"
	 */
	private void assertEqualityOfActualAndExpectedAppDescriptionXml(File expectedDescriptor) throws IOException {
		/* Handler should automatically build descriptor via EAR callbacks */
		String actualAppXmlContent = cutHandler.getAppXml();
		/* Read expected descriptor from file */
		String expectedAppXmlContent = getResourceContent(expectedDescriptor);
		/* Assert equality */
		assertThat(actualAppXmlContent, equalTo(expectedAppXmlContent));
	}

	/*
	 * You'll see only test UTILS below...
	 */
	private JavaArchive createEmptyArchive(String archiveName) {
		return ShrinkWrap.create(JavaArchive.class, archiveName);
	}

	private String getResourceContent(File resource) throws IOException {
		return Files.toString(resource, Charsets.UTF_8);
	}

	private final static String TEST_RESOURCES_SUBFOLDER = "auto-app-descriptor-builder-test";

	private File createResourceFile(String resourceName) {
		String testResourcePath = String.format("%s%s%s", TEST_RESOURCES_SUBFOLDER, File.separator, resourceName);
		ClassLoader classLoader = this.getClass().getClassLoader();
		File file = new File(classLoader.getResource(testResourcePath).getFile());
		return file;
	}

	/* Dummy archives */
	private final String FIRST_EJB_ARCHIVE_NAME = "firstEjb.jar";
	private final JavaArchive FIRST_EJB_ARCHIVE = createEmptyArchive(FIRST_EJB_ARCHIVE_NAME);
	private final String SECOND_EJB_ARCHIVE_NAME = "secondEjb.jar";
	private final JavaArchive SECOND_EJB_ARCHIVE = createEmptyArchive(SECOND_EJB_ARCHIVE_NAME);

	/* Expected application descriptors contents */
	private final File SINGLE_MODULE_EXPECTED_APPLICATION_XML_CONTENT_URL = createResourceFile(
			"single_module_application.xml");
	private final File TWO_MODULES_EXPECTED_APPLICATION_XML_CONTENT_URL = createResourceFile(
			"two_modules_application.xml");
	private final File EMPTY_EXPECTED_APPLICATION_XML_CONTENT_URL = createResourceFile("empty_application.xml");
}