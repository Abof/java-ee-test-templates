package pl.abof.test_template.arquillian.util.ear;

import org.jboss.arquillian.protocol.servlet.arq514hack.descriptors.api.application.ApplicationDescriptor;
import org.jboss.arquillian.protocol.servlet.arq514hack.descriptors.impl.application.ApplicationDescriptorImpl;
import org.jboss.shrinkwrap.api.ArchiveEvent;
import org.jboss.shrinkwrap.api.ArchiveEventHandler;
import org.jboss.shrinkwrap.api.asset.ArchiveAsset;
import org.jboss.shrinkwrap.api.asset.Asset;
import org.jboss.shrinkwrap.api.asset.StringAsset;

/**
 * 
 * <b>TLDR:</b> Automatically build application.xml while you building
 * {@link org.jboss.shrinkwrap.api.spec.EnterpriseArchive}.</br></br>
 * 
 * Automatic {@link ApplicationDescriptor} builder
 * (<code>application.xml</code>) dedicated for <code>ShrinkWrap</code>
 * {@link org.jboss.shrinkwrap.api.spec.EnterpriseArchive}.
 * 
 * Basically this is handler {@link ArchiveEventHandler} of {@link ArchiveAsset}
 * adding event to archive. Due to limitation of information getting from
 * callback event - it translate each event to
 * {@link ApplicationDescriptor#ejbModule(String)} method call so <strong>it's
 * highly recommended to use it only when adding JEB modules to
 * {@link org.jboss.shrinkwrap.api.spec.EnterpriseArchive}.</strong>
 * 
 * @author Abof
 *
 */
public class AutoApplicationDescriptionBuilder implements ArchiveEventHandler {

	private ApplicationDescriptor appDescriptor;

	public AutoApplicationDescriptionBuilder(String descriptorName) {
		super();
		appDescriptor = new ApplicationDescriptorImpl(descriptorName);
	}

	protected static final String DEAFAULT_APPLICATION_DESCRIPTOR_NAME = "application.xml";

	public AutoApplicationDescriptionBuilder() {
		this(DEAFAULT_APPLICATION_DESCRIPTOR_NAME);
	}

	@Override
	public void handle(ArchiveEvent event) {
		Asset asset = event.getAsset();
		if (asset instanceof ArchiveAsset) {
			appDescriptor.ejbModule(event.getPath().get());
		}
	}

	/**
	 * Retrieve application description as a String exported from
	 * {@link ApplicationDescriptor}.
	 */
	public String getAppXml() {
		return appDescriptor.exportAsString();
	}

	public StringAsset getAppXmlAsset() {
		return new StringAsset(getAppXml());
	}

	/**
	 * Get reference to appDescriptor; it's still bounded to this handler!
	 */
	public ApplicationDescriptor getAppDescriptor() {
		return appDescriptor;
	}

}
