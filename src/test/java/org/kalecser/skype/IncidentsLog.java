package org.kalecser.skype;

class IncidentsLog implements IncidentsHandler {

	StringBuffer incidents = new StringBuffer();
	
	String getLog(){
		return incidents.toString().trim();
	}

	@Override
	public void handleincident(String incident) {
		incidents.append(incident + "\n");
	}
}
