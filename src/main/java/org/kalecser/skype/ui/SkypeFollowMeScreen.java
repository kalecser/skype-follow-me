package org.kalecser.skype.ui;

import java.awt.BorderLayout;
import java.awt.Dialog.ModalExclusionType;

import javax.swing.JFrame;


public class SkypeFollowMeScreen {

	public static final String TITLE = "Skype follow me";

	final SkypeFollowMePanel panel = new SkypeFollowMePanel();
	private final Needs needs;
	
	public SkypeFollowMeScreen(Needs needs) {
		this.needs = needs;
		bindToPanel();
	}

	private void bindToPanel() {
		panel.setStartRedirectListener(new StartRedirectListener(){ public void redirectAllMessagesTo(String destination){
			SkypeFollowMeScreen.this.redirectAllMessagesTo(destination);
		}});
	}

	private void redirectAllMessagesTo(String destination) {
		needs.redirectAllMessagesTo(destination);
	}

	public void show() {
		JFrame frame = new JFrame(TITLE);
		frame.setLayout(new BorderLayout());
		frame.add(panel, BorderLayout.CENTER);
		frame.pack();
		frame.setModalExclusionType(ModalExclusionType.TOOLKIT_EXCLUDE);		
		frame.setVisible(true);
	}

}
