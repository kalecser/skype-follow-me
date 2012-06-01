package org.kalecser.skype;

import static junit.framework.Assert.assertEquals;

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

	private String sentMessages() {
		return skype.sentMessages();
	}

	private void simulateMessageFrom(String message, String from) {
		skype.simulateMessageFrom(message, from);		
	}

	private void redirectTo(String destination) {
		subject.redirectAllMessagesTo(destination);
	}

	SkypeMock skype = new SkypeMock();
	FollowMe subject = new FollowMe(skype);
}
