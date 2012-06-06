package org.kalecser.skype;

import com.google.common.base.Optional;

public class FollowMe {

	private final Skype skype;
	private Optional<String> destination = Optional.absent();
	private DestinationListener listener;

	public FollowMe() {
		this(new SkypeImpl());
	}
	
	FollowMe(Skype skype) {
		this.skype = skype;
		skype.listenReceivedMessages(new MesssageReceived(){ @Override public void onMessageReceivedFrom(String message, String from){
			FollowMe.this.onMessageReceivedFrom(message, from);
		}});
	}

	public void redirectAllMessagesTo(String destination) {
		setDestination(destination);
	}

	public void stopRedirect() {
		setDestination(null);
	}
	
	protected void onMessageReceivedFrom(String message, String from) {
		if (!isFowardEnabled()) return;
		if (loopback(from)) return;
		skype.sendMessageTo(from + ": " + message, destination.get());
	}
	
	private void setDestination(String destination) {
		this.destination = Optional.fromNullable(destination);
		listener.redirectingMessagesTo(this.destination);
	}

	private boolean isFowardEnabled() {
		return destination.isPresent();
	}

	private boolean loopback(String from) {
		return from.equals(destination.get());
	}

	public void setDestinationListener(DestinationListener listener) {
		if (this.listener != null) throw new IllegalStateException();
		this.listener = listener;		
	}
}
