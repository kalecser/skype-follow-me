package org.kalecser.skype.stopRedirectOnMouseActivity;

import org.apache.commons.lang.NotImplementedException;
import org.kalecser.skype.DestinationListener;
import org.kalecser.skype.FollowMe;

class FollowMeMock implements FollowMe {

	StringBuffer log = new StringBuffer();
	
	@Override
	public void redirectAllMessagesTo(String destination) {
		log.append("redirect all messages to " + destination + "\n");
	}

	@Override
	public void stopRedirect() {
		log.append("stop redirect");
	}

	@Override
	public void setDestinationListener(DestinationListener listener) {
		throw new NotImplementedException();
	}

	public String getLog() {
		return log.toString().trim();
	}

}
