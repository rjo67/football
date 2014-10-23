package org.rjo.footy.core.services;

import org.rjo.footy.events.game.AllGamesEvent;
import org.rjo.footy.events.game.CreateGameEvent;
import org.rjo.footy.events.game.CreatedGameEvent;
import org.rjo.footy.events.game.GameDetails;
import org.rjo.footy.events.game.RequestAllGamesEvent;
import org.rjo.footy.events.game.RequestGameDetailsEvent;

public interface GameService {

   AllGamesEvent requestAllGames(RequestAllGamesEvent requestAllGamesEvent);

   /**
    * adds a game.
    */
   CreatedGameEvent add(CreateGameEvent gameCreateEvent);

   /**
    * Retrieves game details for a particular game.
    */
   GameDetails requestGameDetails(RequestGameDetailsEvent gameDetailsEvent);

}
