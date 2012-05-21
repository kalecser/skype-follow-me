import org.kalecser.skype.FollowMe;

import com.skype.Skype;
import com.skype.SkypeException;


public class Main {

	
	public static void main(String[] args) throws SkypeException, InterruptedException {
		System.out.println(Skype.isRunning());
		new Thread(){
			public synchronized void run() {
				FollowMe subject = new FollowMe();
				subject.redirectAllMessagesTo("gabrielsan");
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
