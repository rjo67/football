package org.rjo.footy.persistence;

import java.util.UUID;

import org.rjo.footy.core.domain.Game;

public interface GameRepository {

   // boolean save(Game game);

   Game[] list();

   Game loadGame(UUID key);

   Game addGame(Game game);

   // boolean dumptoFile(String filename) throws IOException;
   //
   // boolean loadFromFile(String filename) throws IOException;
}
