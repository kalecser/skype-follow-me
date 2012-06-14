

import org.kalecser.skype.IncidentsHandler;


class ThrowExceptionOnIncidents implements IncidentsHandler {

	@Override
	public void handleincident(String incident) {
		throw new IllegalStateException(incident);
	}

}
