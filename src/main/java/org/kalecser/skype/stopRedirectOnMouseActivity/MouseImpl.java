package org.kalecser.skype.stopRedirectOnMouseActivity;

import java.awt.MouseInfo;
import java.util.Timer;
import java.util.TimerTask;

class MouseImpl implements Mouse{

	private MouseListener mouseListener;
	
	MouseImpl(){
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {  @Override public void run() {
			verifyActivity();
		} }, 1000 * 1, 1000 * 1);
		
	}
	
	@Override
	public void setListener(MouseListener mouseListener) {
		this.mouseListener = mouseListener;
				
	}

	private int lastPosition = 0;
	private int count = 0;
	private void verifyActivity() {
		int current = MouseInfo.getPointerInfo().getLocation().x;
		if (lastPosition == current){
			count =0;
			return;
		}
		
		lastPosition = current;
		if (mouseListener != null) count++;
		
		if (mouseListener != null && (count % 3 == 0)) mouseListener.mouseActivity();
	}

}
