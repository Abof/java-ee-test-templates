package com.github.abof.test.template.wildfly82_arquillian_basic;

import static org.junit.Assert.*;

import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
public class EchoServiceTest {

	@Inject
	private EchoService cut; // ClassUnderTests
	
	@Deployment
	public static JavaArchive createDeplayment() {
		return ShrinkWrap.create(JavaArchive.class, "testApp.jar")
			.addClass(EchoService.class)
			.addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
	}
	
	@Test
	public void testResponse() {
		String testSound = "Hello";
		String expectedResponseSound = "Hello...Hello...Hello...";
		String actualResponse = cut.response(testSound);
		
		assertEquals(expectedResponseSound, actualResponse);
	}

}
