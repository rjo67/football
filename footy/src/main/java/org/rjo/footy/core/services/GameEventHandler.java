package org.rjo.footy.core.services;

import java.util.ArrayList;
import java.util.List;

import org.rjo.footy.core.domain.Game;
import org.rjo.footy.events.menu.AllGamesEvent;
import org.rjo.footy.events.menu.GameDetails;
import org.rjo.footy.events.menu.RequestAllGamesEvent;
import org.rjo.footy.persistence.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GameEventHandler implements GameService {

	@Autowired
	private GameRepository gameRepository;

	@Override
	public AllGamesEvent requestAllGames(
			RequestAllGamesEvent requestAllGamesEvent) {

		Game[] games = gameRepository.list();
		List<GameDetails> gd = new ArrayList<>();
		for (Game game : games) {
			gd.add(game.toGameDetails());
		}
		return new AllGamesEvent(gd);
	}

}
