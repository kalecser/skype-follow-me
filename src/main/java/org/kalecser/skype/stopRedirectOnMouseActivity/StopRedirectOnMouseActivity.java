package org.kalecser.skype.stopRedirectOnMouseActivity;

import org.kalecser.skype.FollowMe;

public class StopRedirectOnMouseActivity {

	private boolean enabled;

	StopRedirectOnMouseActivity(Mouse mouse, final FollowMe followMe) {
		mouse.setListener(new MouseListener() {  @Override public void mouseActivity() {
			if (enabled) followMe.stopRedirect();
		}});
	}

	public StopRedirectOnMouseActivity(FollowMe subject) {
		this(new MouseImpl(), subject);
	}

	public void enable() {
		enabled = true;
	}

	public void disable() {
		enabled = false;		
	}

}
