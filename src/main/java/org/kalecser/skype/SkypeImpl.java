package org.kalecser.skype;

import static com.skype.Skype.addChatMessageListener;
import static com.skype.Skype.chat;

import org.apache.commons.lang.UnhandledException;

import com.google.common.base.Optional;
import com.skype.ChatMessage;
import com.skype.ChatMessageAdapter;
import com.skype.SkypeException;

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
			chat(to).send(message);
		} catch (SkypeException e) {
			throw new UnhandledException(e);
		}
	}

	private void listenToNewMessages() throws SkypeException {
		addChatMessageListener(new ChatMessageAdapter() {@Override public void chatMessageReceived(ChatMessage m) throws SkypeException {
			newMessageFrom(m.getContent(), m.getSender().getFullName());
		}});
	}
	
	protected void newMessageFrom(String message, String from) {
		if (!messageListener.isPresent()) return;
		messageListener.get().onMessageReceivedFrom(message, from);
	}

}
