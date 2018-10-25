package com.musicalbum;

import java.util.Properties;
import java.util.TimeZone;

import org.junit.BeforeClass;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

/**
 * Extend this class if you need to access the properties files in your tests
 * 
 * @author rbogdanoff
 *
 */
public abstract class AbstractTest {
	
	public static Properties props;
	public static String MOCK_SERVERHOST;
	public static int MOCK_SERVER_HTTPPORT;
	public static String MOCKHTTP_URL;
	
	@BeforeClass
	public static void beforeClass() {
	  // make sure TimeZone is UTC.  We normally do this via system property 'user.timezone' from command line
	  // but if test is run with eclispe, we have to set it here
	  TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
	  try {
		Properties main = null;
		// this is a hack will need to fix later but Jenkins is failing so I have to fix right now!
		// try to load application.properties first, if that fails load application-jenkins.properties
		try {
			main = PropertiesLoaderUtils.loadProperties(new ClassPathResource("/application.properties"));
		} catch ( Exception e ) {
			main = PropertiesLoaderUtils.loadProperties(new ClassPathResource("/application-jenkins.properties"));
		}
		
		Properties test = PropertiesLoaderUtils.loadProperties(new ClassPathResource("/test.application.properties"));
		props = new Properties();
		props.putAll(main);
		props.putAll(test);
	  } catch (Exception e) { e.printStackTrace(); }
	}
	
	
}
