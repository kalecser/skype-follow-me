package org.kalecser.skype.ui;



import junit.framework.Assert;

import org.junit.After;
import org.junit.Test;

public class SkypeFollowMeScreenTest {

	@Test
	public void onRedirect_ShouldStartRedirectingMessages(){
		op.redirectAllMessagesTo("Steve");
		op.assertUserMessage("Redirecting all messages to: Steve");
		Assert.assertEquals("Redirect all messages to: Steve", needs.getOperations());
	}
	
	@Test
	public void onStopRedirect_ShouldStopRedirecting(){
		needs.simulateActiveRedirectTo("Steve");
		op.stopRedirecting();
		op.assertUserMessage("Redirect all Skype messages to:");
		Assert.assertEquals(
				"Stop redirecting", needs.getOperations());
		op.redirectAllMessagesTo("Molly");
	}
	
	@Test
	public void onIncident_ShouldDisplayErrorMessage(){
		needs.simulateIncident("Error");
		op.assertErrorMessage("Error");
	}
	
	@Test
	public void testStopRedirectOnMouseActivity(){
		needs.simulateActiveRedirectTo("Steve");
		op.enableStopRedirectOnMouseActivity();
		op.disableStopRedirectOnMouseActivity();
		Assert.assertEquals(
				"enable stop redirect on mouse activity\n" +
				"disable stop redirect on mouse activity", needs.getOperations());
	}
	
	@After
	public void after(){
		op.dispose();
	}

	private NeedsMock needs = new NeedsMock();
	private Operator op = new Operator(needs);
	
}
