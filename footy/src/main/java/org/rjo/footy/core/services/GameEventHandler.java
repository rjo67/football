package org.rjo.footy.core.services;

import java.util.ArrayList;
import java.util.List;

import org.rjo.footy.core.domain.Game;
import org.rjo.footy.events.game.AllGamesEvent;
import org.rjo.footy.events.game.CreateGameEvent;
import org.rjo.footy.events.game.CreatedGameEvent;
import org.rjo.footy.events.game.GameDetails;
import org.rjo.footy.events.game.RequestAllGamesEvent;
import org.rjo.footy.persistence.GameRepository;

public class GameEventHandler implements GameService {

   private GameRepository gameRepository;

   public GameEventHandler(GameRepository gameRepository) {
      this.gameRepository = gameRepository;
   }

   @Override
   public AllGamesEvent requestAllGames(RequestAllGamesEvent requestAllGamesEvent) {

      Game[] games = gameRepository.list();
      List<GameDetails> gd = new ArrayList<>();
      for (Game game : games) {
         gd.add(game.toGameDetails());
      }
      return new AllGamesEvent(gd);
   }

   @Override
   public CreatedGameEvent add(CreateGameEvent gameCreateEvent) {
      // TODO Auto-generated method stub
      return null;
   }

}
