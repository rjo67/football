package org.rjo.footy.events.game;

import org.rjo.footy.events.CreateEvent;

/**
 * Event to request creating a game.
 *
 */
public class CreateGameEvent extends CreateEvent {

   private GameDetails game;

   public CreateGameEvent(GameDetails gameInfo) {
      this.game = gameInfo;
   }

   public GameDetails getGame() {
      return game;
   }
}
