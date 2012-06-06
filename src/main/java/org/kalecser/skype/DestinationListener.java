package org.kalecser.skype;

import com.google.common.base.Optional;

public interface DestinationListener {

	void redirectingMessagesTo(Optional<String> destination);

}
