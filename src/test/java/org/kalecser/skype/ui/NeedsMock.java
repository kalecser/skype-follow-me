package org.kalecser.skype.ui;

class NeedsMock implements Needs {

	StringBuffer operations = new StringBuffer();
	
	public String getOperations() {
		return operations.toString();
	}

	@Override
	public void redirectAllMessagesTo(String destination) {
		operations.append("Redirect all messages to: " + destination);
	}

}
