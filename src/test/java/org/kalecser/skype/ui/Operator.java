package org.kalecser.skype.ui;

import java.lang.reflect.InvocationTargetException;

import javax.swing.SwingUtilities;

import org.apache.commons.lang.UnhandledException;
import org.netbeans.jemmy.operators.JButtonOperator;
import org.netbeans.jemmy.operators.JFrameOperator;
import org.netbeans.jemmy.operators.JLabelOperator;
import org.netbeans.jemmy.operators.JTextFieldOperator;

class Operator{
	private final JFrameOperator screen;
	private final JTextFieldOperator destination;
	private final JButtonOperator startRedirectButton;
	private final SkypeFollowMeScreen subject;

	Operator(NeedsMock needs) {
		subject = new SkypeFollowMeScreen(needs);
		subject.show();
		screen = new JFrameOperator(SkypeFollowMeScreen.TITLE);
		destination = new JTextFieldOperator(screen);
		startRedirectButton = new JButtonOperator(screen);
	}
	
	void redirectAllMessagesTo(String text) {
		destination.typeText(text);
		startRedirectButton.doClick();
		waitForEventDispatch();
	}
	
	void stopRedirecting() {
		new JButtonOperator(screen, "Stop redirecting").doClick();
		waitForEventDispatch();		
	}
	
	void dispose() {
		subject.dispose();
	}

	void assertUserMessage(String string) {
		new JLabelOperator(screen, string);		
	}

	private void waitForEventDispatch() {
		try {
			SwingUtilities.invokeAndWait(new Runnable() {  @Override public void run() {}});
		} catch (InterruptedException e) {
			throw new UnhandledException(e);
		} catch (InvocationTargetException e) {
			throw new UnhandledException(e);
		}
	}

}