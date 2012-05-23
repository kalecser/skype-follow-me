import org.kalecser.skype.FollowMe;

import com.skype.SkypeException;


public class Main {

	
	public static void main(String[] args) throws SkypeException, InterruptedException {
		final String destination = "kalecser";
		
		new Thread(){
			public synchronized void run() {
				FollowMe subject = new FollowMe();
				subject.redirectAllMessagesTo(destination);
				waitWithoutInterruptions();
			}

			private void waitWithoutInterruptions() {
				try {
					wait();
				} catch (InterruptedException e) {throw new IllegalStateException(e);}
			};
		}.start();
		
	}
	
}
