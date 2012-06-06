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
		assertEquals("", sentMessages());
	}
	
	@Test
	public void onStartStopRedirect_ShouldNotifyListener(){
		redirectTo("Steve");
		stopRedirect();
		assertEquals(
				"redirecting messages to: Steve\n" +
				"stop redirecting", redirectionLog());
	}
	
	SkypeMock skype = new SkypeMock();
	DestinationListenerLog log = new DestinationListenerLog();
	FollowMe subject = new FollowMe(skype);
	
	@Before
	public void before(){
		subject.setDestinationListener(log);
	}

	private String redirectionLog() {
		return log.getLog();
	}

	private void stopRedirect() {
		subject.stopRedirect();		
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
