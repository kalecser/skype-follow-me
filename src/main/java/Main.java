import org.kalecser.skype.FollowMe;
import org.kalecser.skype.FollowMeImpl;
import org.kalecser.skype.SkypeImpl;

import com.skype.SkypeException;


public class Main {

	
	public static void main(String[] args) throws SkypeException, InterruptedException {
		
		if (args.length != 1) System.out.println("Usage java -jar skype-follow-me.jar <destination>");
		final String destination = args[0];
		FollowMe subject = redirectAllMessagesTo(destination);
		System.out.println("Redirecting all Skype message to: " + destination);
		keepRunning(subject);
		
	}

	private static FollowMe redirectAllMessagesTo(final String destination) {
		FollowMe subject = new FollowMeImpl(new SkypeImpl(), new ThrowExceptionOnIncidents());
		subject.redirectAllMessagesTo(destination);
		return subject;
	}

	private static void keepRunning(FollowMe subject)
			throws InterruptedException {
		synchronized(subject) {subject.wait();};
	}
	
}
