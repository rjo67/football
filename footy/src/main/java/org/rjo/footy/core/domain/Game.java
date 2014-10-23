package org.rjo.footy.core.domain;

import java.time.LocalDate;
import java.util.UUID;

import org.rjo.footy.events.game.GameDetails;
import org.springframework.beans.BeanUtils;

public class Game {

   private UUID id;
   private LocalDate date;
   private String opponent;

   Game() {
   }

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
      GameDetails gd = new GameDetails();
      BeanUtils.copyProperties(this, gd);
      return gd;
   }

   public static Game fromGameDetails(GameDetails gameInfo) {
      Game g = new Game();
      BeanUtils.copyProperties(gameInfo, g);
      return g;
   }

}
