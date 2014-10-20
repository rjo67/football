package org.rjo.footy.events.menu;

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
