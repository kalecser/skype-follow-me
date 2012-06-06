import org.kalecser.skype.DestinationListener;
import org.kalecser.skype.FollowMe;
import org.kalecser.skype.ui.Needs;

import com.google.common.base.Optional;



class SkypeFollowMeScreenNeedsImpl implements Needs {

	
	FollowMe subject = new FollowMe();
	
	@Override
	public void redirectAllMessagesTo(String destination) {
		subject.redirectAllMessagesTo(destination);
	}

	@Override
	public void setActiveRedirectListener(
			final ActiveRedirectListener activeRedirectListener) {
		subject.setDestinationListener(new DestinationListener() {  @Override public void redirectingMessagesTo(Optional<String> destination) {
			activeRedirectListener.activeRedirectDestinationChangedTo(destination);
		} });
	}

	@Override
	public void stopRedirecting() {
		subject.stopRedirect();		
	}

}
