package org.rjo.footy.persistence;

import java.util.ArrayList;
import java.util.List;

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
}
