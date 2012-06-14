package org.kalecser.skype;

import static com.skype.Skype.addChatMessageListener;
import static com.skype.Skype.chat;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.UnhandledException;

import com.google.common.base.Optional;
import com.skype.ChatMessage;
import com.skype.ChatMessageAdapter;
import com.skype.Friend;
import com.skype.SkypeException;
import com.skype.User;

public class SkypeImpl implements Skype {

	Optional<MesssageReceived> messageListener = Optional.absent();
	
	public SkypeImpl(){
		try {
			listenToNewMessages();
		} catch (SkypeException e) {
			throw new UnhandledException(e);
		}
	}

	@Override
	public void listenReceivedMessages(MesssageReceived messsageReceived) {
		messageListener = Optional.of(messsageReceived);
	}

	@Override
	public void sendMessageTo(String message, String to) {
		try {
			chat(resolveSkypeIdOf(to)).send(message);
		} catch (SkypeException e) {
			throw new UnhandledException(e);
		}
	}

	@Override
	public boolean isKnownUser(String user) {
		return internalIsKnownUser(user);
	}

	private boolean internalIsKnownUser(String user) {
		try {
			for (Friend f : com.skype.Skype.getContactList().getAllFriends()){
				if (f.getFullName().equals(user) || f.getId().equals(user))
					return true;
			}
		} catch (SkypeException e) {
			throw new UnhandledException(e);
		}
		return false;
	}

	private String resolveSkypeIdOf(String to) throws SkypeException {
		
		for (Friend f : com.skype.Skype.getContactList().getAllFriends()){
			if (f.getFullName().equals(to) || f.getId().equals(to))
				return f.getId();
		}
		throw new IllegalStateException("User " + to + " not found");
	}

	private void listenToNewMessages() throws SkypeException {
		addChatMessageListener(new ChatMessageAdapter() {@Override public void chatMessageReceived(ChatMessage m) throws SkypeException {
			newMessageFrom(m.getContent(), getName(m.getSender()));
		}});
	}
	
	protected void newMessageFrom(String message, String from) {
		if (!messageListener.isPresent()) return;
		messageListener.get().onMessageReceivedFrom(message, from);
	}

	private String getName(User sender) throws SkypeException {
		String fullName = sender.getFullName();
		if (StringUtils.isEmpty(fullName))
			return sender.getId();
		return fullName;
	}

}
