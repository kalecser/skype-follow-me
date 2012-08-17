package org.kalecser.skype.ui;

import com.google.common.base.Optional;


public interface Needs {

	public interface ActiveRedirectListener {

		void activeRedirectDestinationChangedTo(Optional<String> activeRedirect);

		void handleIncident(String incident);

	}

	void redirectAllMessagesTo(String destination);

	void setActiveRedirectListener(ActiveRedirectListener activeRedirectListener);

	void stopRedirecting();

	void stopRedirectOnMouseActivity(boolean active);

}
