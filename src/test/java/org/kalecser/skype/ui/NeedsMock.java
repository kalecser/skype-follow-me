package org.kalecser.skype.ui;

import com.google.common.base.Optional;

class NeedsMock implements Needs {

	StringBuffer operations = new StringBuffer();
	private Optional<String> destination = Optional.absent();
	private ActiveRedirectListener activeRedirectListener;
	
	public String getOperations() {
		return operations.toString();
	}

	@Override
	public void redirectAllMessagesTo(String destination) {
		operations.append("Redirect all messages to: " + destination);
		simulateActiveRedirectTo(destination);
	}
	
	@Override
	public void stopRedirecting() {
		operations.append("Stop redirecting");
		this.destination = Optional.absent();
		notifyListener();
	}

	public void simulateActiveRedirectTo(String destination) {
		this.destination = Optional.of(destination);
		notifyListener();
	}

	@Override
	public void setActiveRedirectListener(
			ActiveRedirectListener activeRedirectListener) {
		this.activeRedirectListener = activeRedirectListener;
		notifyListener();
				
	}

	private void notifyListener() {
		this.activeRedirectListener.activeRedirectDestinationChangedTo(destination);
	}

	public void simulateIncident(final String incident) {
		new Thread(){
			@Override
			public void run() {
				activeRedirectListener.handleIncident(incident);
			}
		}.start();
	}

}
