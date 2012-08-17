import org.kalecser.skype.DestinationListener;
import org.kalecser.skype.FollowMe;
import org.kalecser.skype.FollowMeImpl;
import org.kalecser.skype.IncidentsHandler;
import org.kalecser.skype.SkypeImpl;
import org.kalecser.skype.stopRedirectOnMouseActivity.StopRedirectOnMouseActivity;
import org.kalecser.skype.ui.Needs;

import com.google.common.base.Optional;



class SkypeFollowMeScreenNeedsImpl implements Needs, IncidentsHandler {

	
	private final FollowMe subject;
	private Optional<ActiveRedirectListener> activeRedirectListener = Optional.absent();
	private StopRedirectOnMouseActivity stopRedirectOnMouseActivity;
	
	public SkypeFollowMeScreenNeedsImpl(){
		subject = new FollowMeImpl(new SkypeImpl(), this);
		stopRedirectOnMouseActivity = new StopRedirectOnMouseActivity(subject);
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

	@Override
	public void stopRedirectOnMouseActivity(boolean active) {
		if (active)
			stopRedirectOnMouseActivity.enable();
		else
			stopRedirectOnMouseActivity.disable();
	}

}
