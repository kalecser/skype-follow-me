import org.kalecser.skype.FollowMe;

import com.skype.SkypeException;


public class Main {

	
	public static void main(String[] args) throws SkypeException, InterruptedException {
		FollowMe subject = new FollowMe();
		subject.redirectAllMessagesTo("briareos");		
	}
	
}
