package org.kalecser.skype.ui;

import java.awt.BorderLayout;
import java.awt.Dialog.ModalExclusionType;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.JFrame;

import com.google.common.base.Optional;


public class SkypeFollowMeScreen {

	public static final String TITLE = "Skype follow me";

	private final JFrame frame = new JFrame(TITLE);
	private final SkypeFollowMePanel panel = new SkypeFollowMePanel();
	private final Needs needs;
	
	public SkypeFollowMeScreen(Needs needs) {
		this.needs = needs;
		bindToPanel();
		bindToNeeds();
	}

	public void show() {
		frame.setLayout(new BorderLayout());
		frame.add(panel, BorderLayout.CENTER);
		frame.pack();
		frame.setModalExclusionType(ModalExclusionType.TOOLKIT_EXCLUDE);		
		frame.setVisible(true);
	}

	public void dispose() {
		frame.dispose();
	}

	private void bindToPanel() {
		panel.setStartStopRedirectListener(new StartStopRedirectListener(){ 
			public void redirectAllMessagesTo(String destination){
				SkypeFollowMeScreen.this.redirectAllMessagesTo(destination);
			}
	
			@Override
			public void stopRedirecting() {
				SkypeFollowMeScreen.this.stopRedirecting();
			}
		});
		
		closeWhenPanelIsHidden();
		
	}

	private void closeWhenPanelIsHidden() {
		panel.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentHidden(ComponentEvent e) {
				dispose();
			}
		});
	}

	private void bindToNeeds() {
		needs.setActiveRedirectListener(new Needs.ActiveRedirectListener(){ public void activeRedirectDestinationChangedTo(Optional<String> destination){
			SkypeFollowMeScreen.this.activeRedirectDestinationChangedTo(destination);
		}});
	}

	private void activeRedirectDestinationChangedTo(
			Optional<String> destination) {
		panel.setDestination(destination);
	}

	private void redirectAllMessagesTo(String destination) {
		needs.redirectAllMessagesTo(destination);
	}
	
	private void stopRedirecting() {
		needs.stopRedirecting();
	}

}
