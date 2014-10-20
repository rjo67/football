package org.rjo.footy.core.domain;

import java.time.LocalDate;

import org.rjo.footy.events.menu.GameDetails;

public class Game {

	private LocalDate date;
	private String opponent;

	public Game(LocalDate date, String opponent) {
		this.date = date;
		this.opponent = opponent;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public String getOpponent() {
		return opponent;
	}

	public void setOpponent(String opponent) {
		this.opponent = opponent;
	}

	public GameDetails toGameDetails() {
		return new GameDetails(this.getDate().toString(), this.getOpponent());
	}

}
