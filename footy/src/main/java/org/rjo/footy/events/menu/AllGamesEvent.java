package org.rjo.footy.events.menu;

import java.util.List;

import org.rjo.footy.events.ReadEvent;

public class AllGamesEvent extends ReadEvent {
	private List<GameDetails> gameDetails;

	public AllGamesEvent(List<GameDetails> gameDetails) {
		this.gameDetails = gameDetails;
	}

	public List<GameDetails> getGameDetails() {
		return gameDetails;
	}
}
