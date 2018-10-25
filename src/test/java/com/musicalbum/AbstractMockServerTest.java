package com.musicalbum;

import static org.mockserver.integration.ClientAndServer.startClientAndServer;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.mockserver.integration.ClientAndServer;

/**
 * Extend this class if you need to do http/web service mocking
 * 
 * 
 *
 */

public abstract class AbstractMockServerTest extends AbstractTest {
	
	private static String MOCK_SERVERHOST;
	private static int MOCK_SERVER_HTTPPORT;
	private static String MOCKHTTP_URL;

	public static ClientAndServer mockServer;
	
	@BeforeClass
	public static void beforeClass() {
		 AbstractTest.beforeClass();
		 MOCK_SERVERHOST = props.getProperty("mockserver.host");
		 MOCK_SERVER_HTTPPORT = Integer.parseInt(props.getProperty("mockserver.http.port"));
	     MOCKHTTP_URL = "http://"+MOCK_SERVERHOST+":"+Integer.toString(MOCK_SERVER_HTTPPORT);
		 mockServer = startClientAndServer(MOCK_SERVER_HTTPPORT);
	}
	
	@AfterClass
	public static void stopMockServer() {
	    mockServer.stop();
	}
	
	@After
	public void resetMockServer() {
		// clear the mockserver so we are in a clean state for the next test
		mockServer.reset();
	}
	
	public static int getMockHttpPort() {
		return MOCK_SERVER_HTTPPORT;
	}
	
	public static String getMockServerHostname() {
		return MOCK_SERVERHOST;
	}
	
	public static String getMockHttpUrl() {
		return MOCKHTTP_URL;
	}
	
	
	
}
