package org.kalecser.skype;

public interface FollowMe {

	public abstract void redirectAllMessagesTo(String destination);

	public abstract void stopRedirect();

	public abstract void setDestinationListener(DestinationListener listener);

}