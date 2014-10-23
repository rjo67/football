package org.rjo.footy.events.game;

import org.rjo.footy.core.domain.Game;
import org.rjo.footy.events.ReadEvent;

/**
 * Details of a game.
 *
 */
public class GameDetails extends ReadEvent {

   private String id;
   private String date;
   private String opponent;

   public GameDetails() {
   }

   public GameDetails(String id, String date, String opponent) {
      this.id = id;
      this.date = date;
      this.opponent = opponent;
   }

   public void setId(String id) {
      this.id = id;
   }

   public String getId() {
      return id;
   }

   public String getDate() {
      return date;
   }

   public void setDate(String date) {
      this.date = date;
   }

   public String getOpponent() {
      return opponent;
   }

   public void setOpponent(String opponent) {
      this.opponent = opponent;
   }

   public static GameDetails fromGame(Game game) {
      GameDetails gd = new GameDetails();
      if (game == null) {
         gd.entityFound = false;
      } else {
         gd.setId(game.getId().toString());
         gd.setDate(game.getDate().toString());
         gd.setOpponent(game.getOpponent());
      }
      return gd;
   }

   public static GameDetails notFound(Game game) {
      GameDetails gd = new GameDetails();
      gd.entityFound = false;
      return gd;
   }

}
