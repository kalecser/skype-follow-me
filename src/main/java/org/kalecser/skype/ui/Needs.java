package org.kalecser.skype.ui;

import com.google.common.base.Optional;


public interface Needs {

	public interface ActiveRedirectListener {

		void activeRedirectDestinationChangedTo(Optional<String> activeRedirect);

	}

	void redirectAllMessagesTo(String destination);

	void setActiveRedirectListener(ActiveRedirectListener activeRedirectListener);

	void stopRedirecting();

}
