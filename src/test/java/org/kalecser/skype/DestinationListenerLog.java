package org.kalecser.skype;

import com.google.common.base.Optional;

class DestinationListenerLog implements DestinationListener {

	StringBuffer log = new StringBuffer();
	
	public String getLog() {
		return log.toString().trim();
	}

	@Override
	public void redirectingMessagesTo(Optional<String> destination) {
		
		if (destination.isPresent())
			log.append("redirecting messages to: " + destination.get() + "\n");		
		else
			log.append("stop redirecting\n");
	}

}
