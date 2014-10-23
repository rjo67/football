package org.rjo.footy.events.game;

import java.util.UUID;

import org.rjo.footy.events.RequestReadEvent;

/**
 * Event to request details of a game, identified by its id.
 *
 */
public class RequestGameDetailsEvent extends RequestReadEvent {

   private UUID key;

   public RequestGameDetailsEvent(UUID key) {
      this.key = key;
   }

   public UUID getKey() {
      return key;
   }

}
