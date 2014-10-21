package org.rjo.footy.events.game;

import org.rjo.footy.events.CreateEvent;
import org.rjo.footy.web.domain.GameInfo;

public class CreateGameEvent extends CreateEvent {

   private GameInfo gameInfo;

   public CreateGameEvent(GameInfo gameInfo) {
      this.gameInfo = gameInfo;
   }

   public GameInfo getGameInfo() {
      return gameInfo;
   }
}
