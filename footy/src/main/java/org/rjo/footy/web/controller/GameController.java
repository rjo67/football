package org.rjo.footy.web.controller;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.UUID;

import javax.validation.Valid;

import org.rjo.footy.core.services.GameService;
import org.rjo.footy.events.game.CreateGameEvent;
import org.rjo.footy.events.game.CreatedGameEvent;
import org.rjo.footy.web.domain.GameInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/addgame")
public class GameController {

   private static final Logger LOG = LoggerFactory.getLogger(GameController.class);

   @Autowired
   private GameService gameService;

   @RequestMapping(method = RequestMethod.GET)
   public String addGame() {
      return "/addgame";
   }

   @RequestMapping(method = RequestMethod.POST)
   public String addGameSubmit(@Valid @ModelAttribute("gameInfo") GameInfo gameInfo, BindingResult bindingResult) {
      // should do this with javax.validation
      try {
         LocalDate.parse(gameInfo.getDate());
      } catch (DateTimeParseException x) {
         bindingResult.addError(new FieldError("gameVO", "date", "could not parse date"));
      }
      if (bindingResult.hasErrors()) {
         return "/addgame";
      }
      // call business logic
      CreatedGameEvent event = gameService.add(new CreateGameEvent(gameInfo));

      // react to errors by setting a "global" error
      if (!event.isSuccessfull()) {
         bindingResult.addError(new ObjectError("gameVO", "could not add game"));
         return "/addgame";
      }
      UUID key = event.getGameKey();
      // everything ok
      LOG.debug("game added ok; redirecting");
      return "redirect:/game/" + key.toString();
   }

   @ModelAttribute("gameInfo")
   private GameInfo getGameInfo() {
      return new GameInfo();
   }
}