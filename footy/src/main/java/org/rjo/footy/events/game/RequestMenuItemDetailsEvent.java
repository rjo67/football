package org.rjo.footy.events.game;

import org.rjo.footy.events.RequestReadEvent;

public class RequestMenuItemDetailsEvent extends RequestReadEvent {
	private String id;

	public RequestMenuItemDetailsEvent(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}
}
