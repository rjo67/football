package org.rjo.footy.events.menu;

public class GameDetails {

	private String date;
	private String opponent;

	public GameDetails(String date, String opponent) {
		this.date = date;
		this.opponent = opponent;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getOpponent() {
		return opponent;
	}

	public void setOpponent(String opponent) {
		this.opponent = opponent;
	}

}
