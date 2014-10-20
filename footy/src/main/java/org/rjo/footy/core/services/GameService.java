package org.rjo.footy.core.services;

import org.rjo.footy.events.menu.AllGamesEvent;
import org.rjo.footy.events.menu.RequestAllGamesEvent;

public interface GameService {

	AllGamesEvent requestAllGames(RequestAllGamesEvent requestAllGamesEvent);

}
