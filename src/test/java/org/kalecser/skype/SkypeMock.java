package org.kalecser.skype;

import org.kalecser.skype.Skype;

import com.google.common.base.Optional;

class SkypeMock implements Skype {

	private StringBuilder sentMessages = new StringBuilder();
	Optional<MesssageReceived> messageListener = Optional.absent();

	public void simulateMessageFrom(String message, String from) {
		if (!messageListener.isPresent()) return;
		messageListener.get().onMessageReceivedFrom(message, from);
	}

	public String sentMessages() {
		return sentMessages.toString();
	}

	@Override
	public void listenReceivedMessages(MesssageReceived messsageReceivedListener) {
		this.messageListener = Optional.of(messsageReceivedListener);
	}

	@Override
	public void sendMessageTo(String message, String to) {
		sentMessages.append(message + " -> " + to);		
	}

}
