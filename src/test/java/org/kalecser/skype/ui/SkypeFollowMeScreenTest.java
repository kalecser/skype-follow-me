package org.kalecser.skype.ui;



import junit.framework.Assert;

import org.junit.Test;

public class SkypeFollowMeScreenTest {

	@Test
	public void onRedirect_ShouldStartRedirectingMessages(){
		NeedsMock needs = new NeedsMock();
		Operator op = new Operator(needs);
		op.redirectAllMessagesTo("Steve");
		Assert.assertEquals("Redirect all messages to: Steve", needs.getOperations());
	}

	
}
