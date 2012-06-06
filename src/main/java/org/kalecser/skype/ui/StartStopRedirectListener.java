package org.kalecser.skype.ui;

interface StartStopRedirectListener {

	void redirectAllMessagesTo(String destination);

	void stopRedirecting();

}
