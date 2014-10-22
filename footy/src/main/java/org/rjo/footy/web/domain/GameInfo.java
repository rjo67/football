package org.rjo.footy.web.domain;

import org.hibernate.validator.constraints.NotBlank;

/**
 * Spring binds the 'post' variables to these fields.
 * 
 * This object only exists in the web domain.
 *
 */
public class GameInfo {

   private String id;

   @NotBlank
   private String date;
   @NotBlank
   private String opponent;

   public String getId() {
      return id;
   }

   public void setId(String id) {
      this.id = id;
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
