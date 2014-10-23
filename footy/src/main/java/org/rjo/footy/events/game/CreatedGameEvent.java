package org.rjo.footy.events.game;

import java.util.UUID;

import org.rjo.footy.events.CreatedEvent;

/**
 * Event used as answer to a creategame event.
 *
 */
public class CreatedGameEvent extends CreatedEvent {

   private UUID gameKey;
   private GameDetails gameInfo;

   public CreatedGameEvent(final UUID gameKey, GameDetails gameInfo) {
      this.gameKey = gameKey;
      this.gameInfo = gameInfo;
   }

   public UUID getGameKey() {
      return gameKey;
   }

   public GameDetails getGameInfo() {
      return gameInfo;
   }
}
