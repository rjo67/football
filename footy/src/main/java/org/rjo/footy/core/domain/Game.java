package org.rjo.footy.core.domain;

import java.time.LocalDate;
import java.util.UUID;

import org.rjo.footy.events.game.GameDetails;

public class Game {

   private UUID id;
   private LocalDate date;
   private String opponent;

   public Game(LocalDate date, String opponent) {
      this.id = UUID.randomUUID();
      this.date = date;
      this.opponent = opponent;
   }

   public UUID getId() {
      return id;
   }

   public LocalDate getDate() {
      return date;
   }

   public void setDate(LocalDate date) {
      this.date = date;
   }

   public String getOpponent() {
      return opponent;
   }

   public void setOpponent(String opponent) {
      this.opponent = opponent;
   }

   public GameDetails toGameDetails() {
      return new GameDetails(id.toString(), this.getDate().toString(), this.getOpponent());
   }

}
