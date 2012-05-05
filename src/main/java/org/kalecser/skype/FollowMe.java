package org.kalecser.skype;

import com.google.common.base.Optional;

public class FollowMe {

	private final Skype skype;
	private Optional<String> destination = Optional.absent();

	public FollowMe() {
		this(new SkypeImpl());
	}
	
	public FollowMe(Skype skype) {
		this.skype = skype;
		skype.listenReceivedMessages(new MesssageReceived(){ @Override public void onMessageReceivedFrom(String message, String from){
			FollowMe.this.onMessageReceivedFrom(message, from);
		}});
	}

	public void redirectAllMessagesTo(String destination) {
		this.destination = Optional.of(destination);
	}
	
	protected void onMessageReceivedFrom(String message, String from) {
		if (!destination.isPresent())
			return;
		skype.sendMessageTo(from + ": " + message, destination.get());
	}
}
