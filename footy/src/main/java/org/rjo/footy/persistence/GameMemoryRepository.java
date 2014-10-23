package org.rjo.footy.persistence;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.rjo.footy.core.domain.Game;

/**
 * Stores the game info as an arraylist in memory.
 */
public class GameMemoryRepository implements GameRepository {

   private static List<Game> games = new ArrayList<>(50);

   public GameMemoryRepository() {
   }

   public GameMemoryRepository(List<Game> defaultGames) {
      games = defaultGames;
   }

   @Override
   public Game[] list() {
      return games.toArray(new Game[games.size()]);
   }

   @Override
   public Game loadGame(UUID key) {
      for (Game game : games) {
         if (game.getId().equals(key)) {
            return game;
         }
      }
      return null;
   }

   @Override
   public Game addGame(Game game) {
      games.add(game);
      return game;
   }
}
