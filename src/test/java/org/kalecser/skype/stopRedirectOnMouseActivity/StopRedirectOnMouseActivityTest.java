package org.kalecser.skype.stopRedirectOnMouseActivity;

import org.junit.Assert;
import org.junit.Test;

public class StopRedirectOnMouseActivityTest {
	
	@Test
	public void onMouseActivity_stopRedirect(){
		subject.enable();
		followMe.redirectAllMessagesTo("foobar");
		mouse.simulateMouseActivity();
		Assert.assertEquals(
				"redirect all messages to foobar\n" +
				"stop redirect", followMe.getLog());
	}
	
	@Test
	public void ifNotEnabled_doNothing(){
		subject.enable();
		followMe.redirectAllMessagesTo("foobar");
		subject.disable();
		mouse.simulateMouseActivity();
		Assert.assertEquals(
				"redirect all messages to foobar", 
				followMe.getLog());
	}

	private MouseMock mouse = new MouseMock();
	private FollowMeMock followMe = new FollowMeMock();
	private StopRedirectOnMouseActivity subject = new StopRedirectOnMouseActivity(mouse, followMe);
}
