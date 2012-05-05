package org.kalecser.skype;

import static junit.framework.Assert.assertEquals;

import org.junit.Test;

public class FollowMeTest {

	@Test
	public void onReceivedMessage_RedirectToContact(){
		FollowMe subject = new FollowMe(skype);
		subject.redirectAllMessagesTo("Betty");
		skype.simulateMessageFrom("hello", "Steve");
		assertEquals("Steve: hello -> Betty", skype.sentMessages());
	}

	SkypeMock skype = new SkypeMock();
}
