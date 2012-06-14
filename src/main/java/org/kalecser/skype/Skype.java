package org.kalecser.skype;

interface Skype {

	void listenReceivedMessages(MesssageReceived messsageReceived);

	void sendMessageTo(String message, String destination);

	boolean isKnownUser(String user);

}
