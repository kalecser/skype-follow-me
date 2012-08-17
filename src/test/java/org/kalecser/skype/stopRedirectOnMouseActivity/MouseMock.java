package org.kalecser.skype.stopRedirectOnMouseActivity;


class MouseMock implements Mouse {

	private MouseListener listener;

	public void simulateMouseActivity() {
		listener.mouseActivity();
	}

	@Override
	public void setListener(MouseListener mouseListener) {
		this.listener = mouseListener;
	}

}
