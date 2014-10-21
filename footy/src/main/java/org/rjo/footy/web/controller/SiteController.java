package org.rjo.footy.web.controller;

import org.rjo.footy.core.services.GameService;
import org.rjo.footy.events.game.AllGamesEvent;
import org.rjo.footy.events.game.GameDetails;
import org.rjo.footy.events.game.RequestAllGamesEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class SiteController {

   private static final Logger LOG = LoggerFactory.getLogger(SiteController.class);

   @Autowired
   private GameService gameService;

   @RequestMapping(method = RequestMethod.GET)
   public String getGames(Model model) {
      LOG.debug("loading games to home view");
      model.addAttribute("games", gameService.requestAllGames(new RequestAllGamesEvent()));
      return "/home";
   }

   private String prettyPrint(AllGamesEvent requestAllGames) {
      StringBuffer sb = new StringBuffer();
      String delim = "";
      for (GameDetails gameDetails : requestAllGames.getGameDetails()) {
         sb.append(delim).append(gameDetails.getOpponent());
         delim = ",";
      }

      return sb.toString();
   }

}
