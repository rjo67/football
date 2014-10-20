package org.rjo.footy.persistence;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.rjo.footy.core.domain.Game;
import org.springframework.stereotype.Repository;

@Repository
public class GameMemoryRepository implements GameRepository {

	static List<Game> HARDCODED_GAMES;
	static {
		HARDCODED_GAMES = new ArrayList<>();
		HARDCODED_GAMES.add(new Game(LocalDate.parse("2014-02-10"), "Lieth"));
		HARDCODED_GAMES.add(new Game(LocalDate.parse("2014-04-06"), "Holm"));
	}

	// stores the game info == db
	private static List<Game> games = HARDCODED_GAMES;// new ArrayList<>(50);

	@Override
	public Game[] list() {
		return games.toArray(new Game[games.size()]);
	}
}
