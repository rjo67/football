package org.rjo.footy.events.game;

import java.util.UUID;

import org.rjo.footy.events.CreatedEvent;
import org.rjo.footy.web.domain.GameInfo;

public class CreatedGameEvent extends CreatedEvent {

   private UUID gameKey;
   private GameInfo gameInfo;
   private boolean successfull;

   public CreatedGameEvent(final boolean successfull, final UUID gameKey, GameInfo gameInfo) {
      this.successfull = successfull;
      this.gameKey = gameKey;
      this.gameInfo = gameInfo;
   }

   public boolean isSuccessfull() {
      return successfull;
   }

   public UUID getGameKey() {
      return gameKey;
   }

   public GameInfo getGameInfo() {
      return gameInfo;
   }
}
