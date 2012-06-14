import org.kalecser.skype.DestinationListener;
import org.kalecser.skype.FollowMe;
import org.kalecser.skype.IncidentsHandler;
import org.kalecser.skype.SkypeImpl;
import org.kalecser.skype.ui.Needs;

import com.google.common.base.Optional;



class SkypeFollowMeScreenNeedsImpl implements Needs, IncidentsHandler {

	
	private final FollowMe subject;
	private Optional<ActiveRedirectListener> activeRedirectListener = Optional.absent();
	
	public SkypeFollowMeScreenNeedsImpl(){
		subject = new FollowMe(new SkypeImpl(), this);
	}
	
	@Override
	public void redirectAllMessagesTo(String destination) {
		subject.redirectAllMessagesTo(destination);
	}

	@Override
	public void setActiveRedirectListener(
			final ActiveRedirectListener activeRedirectListener) {
		this.activeRedirectListener = Optional.of(activeRedirectListener);
		subject.setDestinationListener(new DestinationListener() {  @Override public void redirectingMessagesTo(Optional<String> destination) {
			activeRedirectListener.activeRedirectDestinationChangedTo(destination);
		} });
	}

	@Override
	public void stopRedirecting() {
		subject.stopRedirect();		
	}

	@Override
	public void handleincident(String incident) {
		activeRedirectListener.get().handleIncident(incident);
	}

}
