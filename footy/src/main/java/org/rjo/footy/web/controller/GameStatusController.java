package org.rjo.footy.web.controller;

import java.util.UUID;

import org.rjo.footy.core.services.GameService;
import org.rjo.footy.events.game.GameDetails;
import org.rjo.footy.events.game.RequestGameDetailsEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Controller for URLs of the form /game/{id}, where id is the key for a game.
 */
@Controller
@RequestMapping("/game/{id}")
public class GameStatusController {
   private static final Logger LOG = LoggerFactory.getLogger(GameStatusController.class);

   @Autowired
   private GameService gameService;

   @RequestMapping(method = RequestMethod.GET)
   public String show(@ModelAttribute("gameDetails") GameDetails gameDetails) {
      LOG.debug("retrieving game for id: " + gameDetails.getId());
      return "/game";
   }

   @ModelAttribute("gameDetails")
   private GameDetails getGameDetails(@PathVariable("id") String gameId) {
      GameDetails gameDetails = gameService.requestGameDetails(new RequestGameDetailsEvent(UUID.fromString(gameId)));
      return gameDetails;
   }
}
