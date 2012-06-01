import org.kalecser.skype.FollowMe;

import com.skype.SkypeException;


public class Main {

	
	public static void main(String[] args) throws SkypeException, InterruptedException {
		
		if (args.length != 1) System.out.println("Usage java -jar skype-follow-me.jar <destination>");
		
		final String destination = args[0];
		
		FollowMe subject = new FollowMe();
		subject.redirectAllMessagesTo(destination);
		keepRunning(subject);
		
	}

	private static void keepRunning(FollowMe subject)
			throws InterruptedException {
		synchronized(subject) {subject.wait();};
	}
	
}
