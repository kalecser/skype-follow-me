import org.kalecser.skype.FollowMe;

import com.skype.Skype;
import com.skype.SkypeException;


public class Main {

	
	public static void main(String[] args) throws SkypeException, InterruptedException {
		final String destination = "me";
		
		System.out.println(Skype.isRunning());
		FollowMe subject = new FollowMe();
		subject.redirectAllMessagesTo(destination);
		
		
		keepRunning(subject);
		
	}

	private static void keepRunning(FollowMe subject)
			throws InterruptedException {
		synchronized(subject) {subject.wait();};
	}
	
}
