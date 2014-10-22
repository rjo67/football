package org.rjo.footy.events.game;

public class GameDetails {

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

}
