package org.rjo.footy.core.services;

import org.rjo.footy.events.game.AllGamesEvent;
import org.rjo.footy.events.game.CreateGameEvent;
import org.rjo.footy.events.game.CreatedGameEvent;
import org.rjo.footy.events.game.RequestAllGamesEvent;

public interface GameService {

   AllGamesEvent requestAllGames(RequestAllGamesEvent requestAllGamesEvent);

   CreatedGameEvent add(CreateGameEvent gameCreateEvent);

}
