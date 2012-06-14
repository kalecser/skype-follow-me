package org.kalecser.skype;

import static junit.framework.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class FollowMeTest {

	@Test
	public void onReceivedMessage_RedirectToContact(){
		redirectTo("Betty");
		simulateMessageFrom("hello", "Steve");
		assertEquals("Steve: hello -> Betty", sentMessages());
	}

	@Test
	public void onLoopbackmessage_doNothing(){
		redirectTo("Steve");
		simulateMessageFrom("hello", "Steve");
		assertNoMessagesWhereSent();
	}

	@Test
	public void onStartStopRedirect_ShouldNotifyListener(){
		redirectTo("Steve");
		stopRedirect();
		assertEquals(
				"redirecting messages to: Steve\n" +
				"stop redirecting", redirectionLog());
	}
	@Test
	public void onContactNotFound_ShouldRaiseIncident(){
		redirectTo("UnknownContact");
		assertEquals(
				"Contact UnknownContact not found, start redirect failed.", 
				incidentsLog());
		assertRedirectionNotStarted();
	}

	SkypeMock skype = new SkypeMock();
	DestinationListenerLog destinationLog = new DestinationListenerLog();
	IncidentsLog incidents = new IncidentsLog();
	FollowMe subject = new FollowMe(skype, incidents);
	
	@Before
	public void before(){
		skype.setKnownContacts("Steve", "Betty");
		subject.setDestinationListener(destinationLog);
	}

	private void assertRedirectionNotStarted() {
		assertEquals(
				"", 
				redirectionLog());
	}

	private String redirectionLog() {
		return destinationLog.getLog();
	}

	private String incidentsLog() {
		return incidents.getLog();
	}

	private void stopRedirect() {
		subject.stopRedirect();		
	}

	private void assertNoMessagesWhereSent() {
		assertEquals("", sentMessages());
	}

	private String sentMessages() {
		return skype.sentMessages();
	}

	private void simulateMessageFrom(String message, String from) {
		skype.simulateMessageFrom(message, from);		
	}

	private void redirectTo(String destination) {
		subject.redirectAllMessagesTo(destination);
	}
}
