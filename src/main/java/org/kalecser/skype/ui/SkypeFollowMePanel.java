package org.kalecser.skype.ui;

import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;

import com.google.common.base.Optional;

@SuppressWarnings("serial")
class SkypeFollowMePanel extends JPanel {
	private JTextField destination;
	private JButton startStopRedirect;
	private JLabel status;
	private StartStopRedirectListener startStopRedirectListener;

	public SkypeFollowMePanel() {
		
		status = new JLabel("Redirect all skype messages to:");
		status.setFont(new Font("Dialog", Font.BOLD, 18));
		
		destination = new JTextField();
		destination.setFont(new Font("Dialog", Font.PLAIN, 18));
		destination.setColumns(10);
		
		startStopRedirect = new JButton("Start Redirect");
		startStopRedirect.setFocusable(false);
		startStopRedirect.setFont(new Font("Dialog", Font.BOLD, 18));
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(destination, GroupLayout.DEFAULT_SIZE, 700, Short.MAX_VALUE)
						.addComponent(status)
						.addComponent(startStopRedirect, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 426, Short.MAX_VALUE))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(status)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(destination, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(startStopRedirect)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		setLayout(groupLayout);
		
		escapeClosesWindow();

	}

	public void setStartStopRedirectListener(
			final StartStopRedirectListener startStopRedirectListener) {
		this.startStopRedirectListener = startStopRedirectListener;
		guardListenerNotSet();
		startStopRedirect.addActionListener(produceStartRedirectListener());
		destination.addActionListener(new ActionListener() {  @Override public void actionPerformed(ActionEvent e) {
			startStopRedirecting();
		} });
	}

	public void setDestination(Optional<String> destination) {
		if (destination.isPresent()) 
			updateStatusForDestination(destination.get());
		else{
			this.startStopRedirect.setText("Start Redirect");
			status.setText("Redirect all skype messages to:");
			status.setForeground(Color.BLACK);
			startStopRedirect.setForeground(Color.BLACK);
		}
	}

	private void escapeClosesWindow() {
		destination.addKeyListener(new KeyAdapter() { @Override public void keyPressed(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_ESCAPE)
				SkypeFollowMePanel.this.setVisible(false);
		} });
	}

	private void updateStatusForDestination(String destinationNotNull) {
		this.destination.setText(destinationNotNull);
		this.startStopRedirect.setText("Stop redirecting");
		status.setText("Redirecting all messages to: " + destinationNotNull);
		status.setForeground(Color.BLUE);
		startStopRedirect.setForeground(Color.BLUE);
	}

	private ActionListener produceStartRedirectListener() {
		return new ActionListener() {  @Override public void actionPerformed(ActionEvent e) {
			startStopRedirecting();
		}};
	}

	private void guardListenerNotSet() {
		if (startStopRedirect.getActionListeners().length > 0) throw new IllegalStateException("Listener already set");
	}

	private void startStopRedirecting() {
		if (startStopRedirect.getText().startsWith("Stop")){
			startStopRedirectListener.stopRedirecting();
			return;
		}
		
		startStopRedirectListener.redirectAllMessagesTo(destination.getText());
	}
}
