package org.kalecser.skype.ui;

import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;

@SuppressWarnings("serial")
class SkypeFollowMePanel extends JPanel {
	private JTextField destination;
	private JButton startRedirect;

	public SkypeFollowMePanel() {
		
		JLabel lblRedirectAllSkype = new JLabel("Redirect all skype messages to:");
		lblRedirectAllSkype.setFont(new Font("Dialog", Font.BOLD, 18));
		
		destination = new JTextField();
		destination.setFont(new Font("Dialog", Font.PLAIN, 18));
		destination.setColumns(10);
		
		startRedirect = new JButton("Start Redirect");
		startRedirect.setFont(new Font("Dialog", Font.BOLD, 18));
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(destination, GroupLayout.DEFAULT_SIZE, 426, Short.MAX_VALUE)
						.addComponent(lblRedirectAllSkype)
						.addComponent(startRedirect, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 426, Short.MAX_VALUE))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblRedirectAllSkype)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(destination, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(startRedirect)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		setLayout(groupLayout);

	}

	public void setStartRedirectListener(
			final StartRedirectListener startRedirectListener) {
		guardListenerNotSet();
		startRedirect.addActionListener(new ActionListener() {  @Override public void actionPerformed(ActionEvent e) {
			startRedirectListener.redirectAllMessagesTo(destination.getText());
		}});
	}

	private void guardListenerNotSet() {
		if (startRedirect.getActionListeners().length > 0) throw new IllegalStateException("Listener already set");
	}
}
