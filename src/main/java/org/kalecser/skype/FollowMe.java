package org.kalecser.skype;

import com.google.common.base.Optional;

public class FollowMe {

	private final Skype skype;
	private final IncidentsHandler incidentsHandler;
	private Optional<String> destination = Optional.absent();
	private DestinationListener listener;
	
	public FollowMe(Skype skype, IncidentsHandler incidentsHandler) {
		this.skype = skype;
		this.incidentsHandler = incidentsHandler;
		skype.listenReceivedMessages(new MesssageReceived(){ @Override public void onMessageReceivedFrom(String message, String from){
			FollowMe.this.onMessageReceivedFrom(message, from);
		}});
	}

	public void redirectAllMessagesTo(String destination) {
		if (!skype.isKnownUser(destination))
			incidentsHandler.handleincident("Contact " + destination + " not found, start redirect failed.");
		else
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
